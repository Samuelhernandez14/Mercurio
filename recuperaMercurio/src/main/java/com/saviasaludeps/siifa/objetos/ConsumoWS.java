package com.saviasaludeps.siifa.objetos;

import com.saviasaludeps.siifa.generico.Log;
import com.saviasaludeps.siifa.generico.PropApl;
import com.saviasaludeps.siifa.wsdl.adjuntos.AnexoBase64;
import com.saviasaludeps.siifa.wsdl.adjuntos.AnexoRadicadoRequest;
import com.saviasaludeps.siifa.wsdl.adjuntos.AnexoRadicadoResponse;
import com.saviasaludeps.siifa.wsdl.adjuntos.ConsultarAnexoRadOrigenInterface;
import com.saviasaludeps.siifa.wsdl.adjuntos.ConsultarAnexoRadOrigenService;
import com.saviasaludeps.siifa.wsdl.anexos.ConsultarAnexosRelacionadosInterface;
import com.saviasaludeps.siifa.wsdl.anexos.ConsultarAnexosRelacionadosRequest;
import com.saviasaludeps.siifa.wsdl.anexos.ConsultarAnexosRelacionadosResponseAlt;
import com.saviasaludeps.siifa.wsdl.anexos.ConsultarAnexosRelacionadosService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author rpalacios
 */
public class ConsumoWS {

    public static List<Soporte> consultaAnexosRelacionados(String nit, String radicado) {
        // 1. Crear instancia del servicio
        ConsultarAnexosRelacionadosService service = new ConsultarAnexosRelacionadosService();
        ConsultarAnexosRelacionadosInterface port = service.getConsultarAnexosRelacionadosSOAP();

        // 2. Setear URL del endpoint dinámicamente
        ((BindingProvider) port).getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, PropApl.getInstance().get(PropApl.URL_WS_SOAP_ANEXOS));

        // 3. Crear y enviar petición
        ConsultarAnexosRelacionadosRequest request = new ConsultarAnexosRelacionadosRequest();
        request.setNit(nit);
        request.setRadConexiones(radicado);

        ConsultarAnexosRelacionadosResponseAlt response = port.consultarAnexosRelacionados(request);

        // 4. Procesar respuesta
        String idDocumentoMercurio = response.getIdDocumentoMercurio();
        return response.getIdAnexos().stream().map(idAnexo -> {
            Soporte soporte = new Soporte();
            soporte.setIdAnexo(idAnexo);
            soporte.setIdDocumento(idDocumentoMercurio);
            return soporte;
        }).collect(Collectors.toList());
    }

    public static Soporte consultaArchivoAdjunto(Soporte soporte) {
        ConsultarAnexoRadOrigenService service = new ConsultarAnexoRadOrigenService();
        ConsultarAnexoRadOrigenInterface port = service.getConsultarAnexoRadOrigenSOAP();

        ((BindingProvider) port).getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, PropApl.getInstance().get(PropApl.URL_WS_SOAP_ADJUNTOS));

        AnexoRadicadoRequest request = new AnexoRadicadoRequest();
        request.setIdDocumentoMercurio(String.valueOf(soporte.getIdDocumento()));
        request.getIdAnexos().add(String.valueOf(soporte.getIdAnexo()));

        AnexoRadicadoResponse response = port.consultarAnexoRadicado(request);

        if (response.getAnexos() == null || response.getAnexos().isEmpty()) {
            Log.getInstance().error("Validando adjunto", "No se recibió ningún adjunto en la respuesta", new Exception());
        }

        AnexoBase64 anexo = response.getAnexos().get(0);
        soporte.setNombre(anexo.getTitulo());
        soporte.setExtension(anexo.getExtension());
        soporte.setBase64(anexo.getDocumentoBase64());
        soporte.setFechaHora(new Date());

        return soporte;
    }
}
