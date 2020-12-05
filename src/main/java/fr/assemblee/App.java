package fr.assemblee;


import io.vertx.core.Vertx;
import org.apache.log4j.Logger;


public class App
{
    private static Logger LOGGER = Logger.getLogger(App.class);

    public static void main( String[] args )
    {
        LOGGER.info("Running..");
        try {
            Application.init();
            LOGGER.info("Init OK");
            final Vertx vertx = Vertx.factory.vertx();
            vertx.deployVerticle(new AssembleeApi());
        } catch (Exception e) {
            LOGGER.error("Oh no...", e);
        }
    }
}
