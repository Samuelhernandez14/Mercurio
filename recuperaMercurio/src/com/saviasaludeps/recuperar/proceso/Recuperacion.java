package com.saviasaludeps.recuperar.proceso;

import com.saviasaludeps.recuperar.generico.ConnectionManagerMySql;
import com.saviasaludeps.recuperar.generico.Log;
import com.saviasaludeps.recuperar.generico.PropApl;
import com.saviasaludeps.recuperar.objetos.CmFeSoporte;
import com.saviasaludeps.recuperar.objetos.ConsumoWS;
import com.saviasaludeps.recuperar.objetos.FeRipsCarga;
import com.saviasaludeps.recuperar.objetos.Maestro;
import com.saviasaludeps.recuperar.objetos.Sincronizacion;
import com.saviasaludeps.recuperar.objetos.Soporte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author rpalacios
 */
public class Recuperacion {

    private static final String USUARIO_CREA = "INTEROPERABILIDAD";
    private static final String TERMINAL_CREA = "localhost";

    public static void procesamiento() {
        Log.getInstance().suceso("Inicio Proceso", "Inicio Proceso");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dfh = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Connection connMySql = null;
        try {
            Sincronizacion sincroniza = Sincronizacion.consultarUltimoExitoso();            
            Calendar calPeriodo = Calendar.getInstance();
            if (sincroniza == null) {
//                throw new Exception("No se encontró una sincronización inicial");
                calPeriodo.set(2025, 1, 13);
            }else{
                calPeriodo.setTime(sincroniza.getPeriodo());
            }
            calPeriodo.add(Calendar.DATE, 1);
            Calendar calFin = Calendar.getInstance();
            calFin.setTime(df.parse(df.format(new Date())));
            while (!calPeriodo.after(calFin)) {
                Sincronizacion nuevaSincronizacion = null;
                try {
                    connMySql = ConnectionManagerMySql.getInstance().getConnection();
                    List<FeRipsCarga> listaCargas = FeRipsCarga.consultarListaPendientesPorFecha(connMySql, calPeriodo.getTime());
                    Log.getInstance().suceso("Inicio Periodo", "Inicio Periodo '" + df.format(calPeriodo.getTime()) + "' Registros: " + listaCargas.size() + "");
//                    Log.getInstance().suceso("Inicio Periodo", "Inicio Periodo '" + fechaPeriodo + "' Anexos: " + listaCargas.size());
                    nuevaSincronizacion = new Sincronizacion(calPeriodo.getTime(), "Inicio de sincronización de adjuntos");
                    nuevaSincronizacion.setId(Sincronizacion.crear(nuevaSincronizacion, connMySql));
                    for (FeRipsCarga carga : listaCargas) {
                        try {
                            List<Soporte> listaSoportes = ConsumoWS.consultaAnexosRelacionados(carga.getEmpresaNit(), String.valueOf(carga.getId()));
                            for (Soporte soporte : listaSoportes) {
                                soporte = ConsumoWS.consultaArchivoAdjunto(soporte);
                                registroSoportes(connMySql, nuevaSincronizacion, carga, soporte);
                            }
                        } catch (Exception ex) {
                            Log.getInstance().error("Error procesando carga con radicado en Conexiones: '" + carga.getId() + "'", ex.getMessage(), ex);
                            throw ex;
                        }
                    }
                    Log.getInstance().suceso("Fin Periodo", "Fin Periodo '" + df.format(calPeriodo.getTime()) + "': " + nuevaSincronizacion.getRegistrosActualizados() + " registros");
                    nuevaSincronizacion.setDescripcion("Finalizó sincronización de adjuntos exitosamente");
                    nuevaSincronizacion.setEstado(Sincronizacion.ESTADO_FINALIZADO);
                    Sincronizacion.finalizar(nuevaSincronizacion, connMySql);
                } catch (Exception ex) {
                    Log.getInstance().error("Error procesando el periodo: '" + df.format(calPeriodo) + "'", ex.getMessage(), ex);
                    if (nuevaSincronizacion != null) {
                        nuevaSincronizacion.setDescripcion("Error en sincronización de adjuntos");
                        nuevaSincronizacion.setEstado(Sincronizacion.ESTADO_ERROR);
                        Sincronizacion.finalizar(nuevaSincronizacion, connMySql);
                    }
                    break;
                } finally {
                    if (connMySql != null) {
                        try {
                            connMySql.close();
                        } catch (SQLException ex) {

                        }
                    }
                }
                calPeriodo.add(Calendar.DATE, 1);
            }
        } catch (Exception ex) {
            Log.getInstance().error("Error Proceso", ex.getMessage(), ex);
        } finally {
            Log.getInstance().suceso("Fin Proceso", "Fin Proceso");
        }
    }

    public static void registroSoportes(Connection conn, Sincronizacion sincronizacion, FeRipsCarga carga, Soporte soporte) throws Exception {
        //consultar WS de soporte y llenar la info de Soporte
        //Descomprimir y obtener la lista de archivos
        if (soporte.getExtension().toUpperCase().equals(Soporte.EXTENSION_ZIP)) {
            List<CmFeSoporte> listaCmFeSoporte = guardarZipBase64(conn, carga, soporte);
            if (listaCmFeSoporte != null) {
                sincronizacion.incrementarConsultados(listaCmFeSoporte.size());
                for (CmFeSoporte cmFeSoporte : listaCmFeSoporte) {
                    int creado = CmFeSoporte.crear(cmFeSoporte, conn);
                    if (creado == 0) {
                        sincronizacion.incrementarActualizados();
                    }
                }
            }
        } else {
            //Convertir y guardar archivos
            CmFeSoporte cmFeSoporte = guardarBase64(conn, carga, soporte);
            sincronizacion.incrementarConsultados(1);
            //Crear registro en BD
            if (cmFeSoporte != null) {
                //Registrar en BD
                int creado = CmFeSoporte.crear(cmFeSoporte, conn);
                if (creado == 0) {
                    sincronizacion.incrementarActualizados();
                }
            }
        }
    }

    private static CmFeSoporte guardarBase64(Connection conn, FeRipsCarga carga, Soporte soporte) throws Exception {
        CmFeSoporte cmFeSoporte = castSoporteToCmFeSoporte(conn, carga, soporte, false, soporte.getNombre(), PropApl.getInstance().get(PropApl.RUTA_ALMACENAMIENTO));
        // Crear la ruta completa
        String nombreArchivoConPrefijo = CmFeSoporte.PREFIJO_SOPORTE + soporte.getNombre();
        String rutaArchivo = PropApl.getInstance().get(PropApl.RUTA_ALMACENAMIENTO) + File.separator + nombreArchivoConPrefijo;
        // Decodificar y escribir el archivo
        byte[] bytes = Base64.getDecoder().decode(soporte.getBase64());
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            fos.write(bytes);
        } catch (Exception ex) {
            cmFeSoporte = null;
            throw new Exception("Error al guardar base 64 para el anexo : '" + soporte.getIdAnexo() + "'");
        }
        return cmFeSoporte;
    }

    private static List<CmFeSoporte> guardarZipBase64(Connection conn, FeRipsCarga carga, Soporte soporte) throws Exception {
        List<CmFeSoporte> listaCmFeSoporte = new ArrayList();
        try {
            // 1. Decodificar el contenido Base64 a bytes
            byte[] zipBytes = Base64.getDecoder().decode(soporte.getBase64());
            // 2. Crear un InputStream desde los bytes
            ByteArrayInputStream bais = new ByteArrayInputStream(zipBytes);
            ZipInputStream zis = new ZipInputStream(bais);
            // 3. Directorio de salida para los archivos descomprimidos
            Path outputDir = Paths.get(PropApl.getInstance().get(PropApl.RUTA_ALMACENAMIENTO));
            Files.createDirectories(outputDir);
            // 4. Iterar sobre cada entrada del ZIP
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    // Extraer partes del path del ZIP
                    Path rutaRelativa = Paths.get(entry.getName());
                    Path rutaCarpeta = rutaRelativa.getParent();
                    String nombreArchivoOriginal = rutaRelativa.getFileName().toString();
                    // Para los casos ZIP, el nombre de respuesta es el nombre del zip, 
                    // por tal razón renombramos el nombre del soporte con su nombre original
                    soporte.setNombre(nombreArchivoOriginal);
                    String nombreArchivoConPrefijo = CmFeSoporte.PREFIJO_SOPORTE + nombreArchivoOriginal;
                    // Ruta de salida manteniendo la estructura ZIP
                    Path outputPath = (rutaCarpeta == null)
                            ? outputDir.resolve(nombreArchivoConPrefijo)
                            : outputDir.resolve(rutaCarpeta).resolve(nombreArchivoConPrefijo);
                    // Crear carpetas si no existen
                    Files.createDirectories(outputPath.getParent());
                    // Escribir el archivo
                    try (OutputStream os = Files.newOutputStream(outputPath)) {
                        byte[] buffer = new byte[4096];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            os.write(buffer, 0, len);
                        }
                    }
                    CmFeSoporte cmFeSoporte = castSoporteToCmFeSoporte(
                            conn,
                            carga,
                            soporte,
                            true,
                            nombreArchivoConPrefijo,
                            outputPath.getParent().toAbsolutePath().toString().replace(File.separatorChar, '/')
                    );
                    listaCmFeSoporte.add(cmFeSoporte);
                }
                zis.closeEntry();
            }
            zis.close();
            System.out.println("Archivos extraídos en: " + outputDir.toAbsolutePath());
        } catch (IOException e) {
            listaCmFeSoporte = new ArrayList();
            throw new Exception("Error al descomprimir base 64 para el anexo : '" + soporte.getIdAnexo() + "'");
        }
        return listaCmFeSoporte;
    }

    public static CmFeSoporte castSoporteToCmFeSoporte(Connection conn, FeRipsCarga carga, Soporte soporte, boolean esZip, String archivo, String archivoRuta) {
        CmFeSoporte cmFeSoporte = new CmFeSoporte();
        cmFeSoporte.setCarga(carga);
        cmFeSoporte.setArchivoNombre(soporte.getNombre());
        cmFeSoporte.setNitFactura(carga.getEmpresaNit());
        cmFeSoporte.setArchivoExiste(esZip);
        cmFeSoporte.setArchivoRuta(archivoRuta);
        cmFeSoporte.setArchivo(archivo);
        String codigoTipoSoporte = cmFeSoporte.getArchivoNombre().split("_")[0];
        Maestro maeTipoSoporte = Maestro.consultarMaestro(conn, codigoTipoSoporte);
        if (maeTipoSoporte == null) {
            cmFeSoporte.setMaeTipoSoporteId(0);
            cmFeSoporte.setMaeTipoSoporteCodigo(Maestro.MAE_TIPO_SOPORTE_CODIGO_DESCONOCIDO);
            cmFeSoporte.setMaeTipoSoporteValor(Maestro.MAE_TIPO_SOPORTE_VALOR_DESCONOCIDO);
        } else {
            cmFeSoporte.setMaeTipoSoporteId(maeTipoSoporte.getId());
            cmFeSoporte.setMaeTipoSoporteCodigo(maeTipoSoporte.getValor());
            cmFeSoporte.setMaeTipoSoporteValor(maeTipoSoporte.getNombre());
        }
        cmFeSoporte.setUsuarioCrea(USUARIO_CREA);
        cmFeSoporte.setFechaHoraCrea(new Date());
        cmFeSoporte.setTerminalCrea(TERMINAL_CREA);
        return cmFeSoporte;
    }
}
