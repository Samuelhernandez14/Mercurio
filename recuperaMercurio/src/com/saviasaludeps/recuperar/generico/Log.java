package com.saviasaludeps.recuperar.generico;

/**
 *
 * @author Nacho
 */
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    private static Log instance = null;

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void error(String nombre, String mensaje, Exception e) {
        Logger logger = Logger.getLogger(nombre);
        FileHandler fh = null;
        try {
            fh = new FileHandler(PropApl.getInstance().get(PropApl.RUTA_LOGS) + FH.darFormatoFApl(new GregorianCalendar()) + "_error.txt", true);
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            // the following statement is used to log any messages
            logger.log(Level.WARNING, mensaje, e);
        } catch (SecurityException ex) {
        } catch (IOException ex) {
        } finally {
            if (fh != null) {
                try {
                    fh.close();
                } catch (Exception exe) {
                }
            }
        }
    }

    public void suceso(String nombre, String suceso) {
        Logger logger = Logger.getLogger(nombre);
        FileHandler fh = null;
        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler(PropApl.getInstance().get(PropApl.RUTA_LOGS) + FH.darFormatoFApl(new GregorianCalendar()) + "_suceso.txt", true);
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            // the following statement is used to log any messages
            logger.log(Level.INFO, "({0}) {1}", new Object[]{nombre, suceso});
        } catch (SecurityException | IOException ex) {
        } finally {
            if (fh != null) {
                try {
                    fh.close();
                } catch (Exception exe) {
                }
            }
        }
    }

    public void noEnviado(String suceso) {
        Logger logger = Logger.getLogger("NO ENVIADO");
        FileHandler fh = null;
        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler(PropApl.getInstance().get(PropApl.RUTA_LOGS) + FH.darFormatoFApl(new GregorianCalendar()) + "_no_enviado.txt", true);
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            // the following statement is used to log any messages
            logger.log(Level.INFO, "({0}) {1}", new Object[]{"NO ENVIADO", suceso});
        } catch (SecurityException | IOException ex) {
        } finally {
            if (fh != null) {
                try {
                    fh.close();
                } catch (Exception exe) {
                }
            }
        }
    }
}
