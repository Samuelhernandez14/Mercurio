package com.saviasaludeps.siifa.generico;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * @version 2.0
 * @author SystemTech Integral
 */
public class ContextManager {

    private static ContextManager contextManager = null;

    private ContextManager() {
    }

    public static ContextManager getInstance() {
        if (contextManager == null) {
            contextManager = new ContextManager();
        }
        return contextManager;
    }

    public StringBuffer getQuery(String rutaQuery) throws SQLException {
        InputStream in = null;
        if (rutaQuery.charAt(0) == '/') {
            rutaQuery = rutaQuery.substring(1);
        }
        in = getClass().getResourceAsStream("/com/saviasaludeps/recuperar/sql/" + rutaQuery);
        StringBuffer retQuery = new StringBuffer();
        int caracter;
        try {
            while ((caracter = in.read()) != -1) {
                retQuery.append((char) caracter);
            }
        } catch (IOException e) {
            throw new SQLException(e.toString());
        }
        return retQuery;
    }

    public StringBuffer getQuery(String rutaQuery, ParamsSQL params) throws SQLException {
        InputStream in = null;
        if (rutaQuery.charAt(0) == '/') {
            rutaQuery = rutaQuery.substring(1);
        }
        in = getClass().getResourceAsStream("/com/saviasaludeps/recuperar/sql/" + rutaQuery);
        StringBuffer retQuery = new StringBuffer();
        int caracter;
        boolean hayParam = false;
        boolean terminaParam = false;
        String nomParam = "";
        try {
            while ((caracter = in.read()) != -1) {
                if (!hayParam && (char) caracter == '&') {
                    hayParam = true;
                } else if (hayParam && ((char) caracter == ' '
                        || (char) caracter == ')'
                        || (char) caracter == '('
                        || (char) caracter == ','
                        || (char) caracter == '+'
                        || (char) caracter == '-'
                        || (char) caracter == '*'
                        || (char) caracter == '/'
                        || (char) caracter == '='
                        || (char) caracter == '>'
                        || (char) caracter == '<'
                        || (char) caracter == '!'
                        || (char) caracter == '|'
                        || (char) caracter == '&'
                        || (char) caracter == '`'
                        || caracter == 10
                        || caracter == 13)) {
                    terminaParam = true;
                    hayParam = false;
                }
                if (hayParam && (char) caracter != '&') {
                    nomParam += (char) caracter;
                } else if (terminaParam) {
                    retQuery.append(params.getValor(nomParam));
                    terminaParam = false;
                    nomParam = "";
                }
                if (!hayParam) {
                    retQuery.append((char) caracter);
                }
            }
            if (hayParam && nomParam.length() > 0) {
                retQuery.append(params.getValor(nomParam));
            }
        } catch (IOException e) {
            throw new SQLException(e.toString());
        }
        return retQuery;
    }
}
