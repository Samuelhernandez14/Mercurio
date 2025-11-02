/*
 * ParamApl.java
 *
 * Created on 20 de septiembre de 2005, 15:47
 */
package com.saviasaludeps.siifa.generico;

/**
 *
 * @author Ripalacios :: SystemTech Integral.
 */
public class ParamApl {

    /**
     * Creación: Ripalacios :: SystemTech Integral :: 11-sep-2006<br>
     * Determina el valor de un parámetro configurado para la aplicación. Estos
     * parámetros se crean en la tabla con_parametros para la empresa, la
     * aplicación o un módulo
     *
     * @param codParam Código del parámetro del cual desea consultar el valor.
     * @return String El valor del parámetro consultado, vacio si no lo
     * encuentra.
     */
//    public static String getValor(String codParam) throws SQLException{
//        String valor = null;
//        if (codParam==null || codParam.equals(""))
//            valor="";
//        else{
//            valor="";
//            Connection conn = null;
//            CallableStatement cstmt = null;
//            try{
//                try{
//                    conn = ConnectionManager.getInstance().getConnection();
//                    cstmt = conn.prepareCall("{? = call pk_conseres.param(?)}");
//                    cstmt.registerOutParameter(1, Types.VARCHAR);
//                    cstmt.setString(2, codParam);
//                    cstmt.execute();
//                    if (cstmt.getString(1)==null || cstmt.getString(1).equals(""))
//                        valor="";
//                    else
//                        valor = cstmt.getString(1);
//                }catch (Exception e){
//                    System.err.println("Error leyendo parámetro '"+codParam+"':"+e.getMessage());
//                }finally{
//                    if ( cstmt!=null)cstmt.close();
//                    if ( conn!=null)conn.close();
//                }
//            }catch (Exception e){
//                System.err.println("Error leyendo parámetro '"+codParam+"':"+e.getMessage());
//            }
//        }
//        return valor;
//    }
}
