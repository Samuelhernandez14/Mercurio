package com.saviasaludeps.siifa.objetos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saviasaludeps.siifa.generico.Log;
import com.saviasaludeps.siifa.generico.PropApl;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ConsumoREST {
    
    public static ReferenciaCums consultarReferenciaCums(int idCums) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ReferenciaCums referencia = null;
        
        try {
            // Obtener token válido
            String token = TokenManager.getInstance().getToken();
            
            // Construir URL
            String url = PropApl.getInstance().get(PropApl.URL_REST_SIIFA) + "/" + idCums;
            HttpGet httpGet = new HttpGet(url);
            
            // Configurar headers
            httpGet.setHeader("Authorization", "Bearer " + token);
            httpGet.setHeader("Content-Type", "application/json");
            
            // Ejecutar petición
            CloseableHttpResponse response = httpClient.execute(httpGet);
            
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());
                
                if (statusCode == 200) {
                    ObjectMapper mapper = new ObjectMapper();
                    referencia = mapper.readValue(responseBody, ReferenciaCums.class);
                } else if (statusCode == 404) {
                    Log.getInstance().suceso("ID No Encontrado", "ID CUMS " + idCums + " no existe en SIIFA");
                } else {
                    throw new Exception("Error consultando ID " + idCums + ". Status: " + statusCode + " Response: " + responseBody);
                }
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
        
        return referencia;
    }
}