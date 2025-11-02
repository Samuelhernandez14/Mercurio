package com.saviasaludeps.siifa.objetos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saviasaludeps.siifa.generico.Log;
import com.saviasaludeps.siifa.generico.PropApl;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TokenManager {
    
    private static TokenManager instance;
    private String token;
    private LocalDateTime expiration;
    
    private TokenManager() {
    }
    
    public static TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }
    
    public String getToken() throws Exception {
        if (token == null || isTokenExpired()) {
            renovarToken();
        }
        return token;
    }
    
    private boolean isTokenExpired() {
        if (expiration == null) {
            return true;
        }
        // Renovar 5 minutos antes de expirar
        return LocalDateTime.now().plusMinutes(5).isAfter(expiration);
    }
    
    private void renovarToken() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            String url = PropApl.getInstance().get(PropApl.URL_REST_SIIFA_TOKEN);
            HttpPost httpPost = new HttpPost(url);
            
            // Configurar headers
            httpPost.setHeader("Content-Type", "application/json");
            
            // Crear body JSON
            String jsonBody = String.format(
                "{\"userName\":\"%s\",\"password\":\"%s\"}",
                PropApl.getInstance().get(PropApl.SIIFA_USERNAME),
                PropApl.getInstance().get(PropApl.SIIFA_PASSWORD)
            );
            
            httpPost.setEntity(new StringEntity(jsonBody, "UTF-8"));
            
            // Ejecutar petición
            CloseableHttpResponse response = httpClient.execute(httpPost);
            
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());
                
                if (statusCode == 200) {
                    ObjectMapper mapper = new ObjectMapper();
                    TokenResponse tokenResponse = mapper.readValue(responseBody, TokenResponse.class);
                    
                    this.token = tokenResponse.getToken();
                    
                    // Parsear fecha de expiración
                    if (tokenResponse.getExpiration() != null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                        this.expiration = LocalDateTime.parse(tokenResponse.getExpiration(), formatter);
                    } else {
                        // Si no viene fecha, asumir 1 hora de validez
                        this.expiration = LocalDateTime.now().plusHours(1);
                    }
                    
                    Log.getInstance().suceso("Token Renovado", "Token obtenido exitosamente. Expira: " + this.expiration);
                } else {
                    throw new Exception("Error obteniendo token. Status: " + statusCode + " Response: " + responseBody);
                }
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }
}