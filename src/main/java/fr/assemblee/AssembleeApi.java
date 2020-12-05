package fr.assemblee;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import org.apache.log4j.Logger;

public class AssembleeApi extends AbstractVerticle {
    private static final Logger LOGGER = Logger.getLogger(AssembleeApi.class);

    @Override
    public void start() throws Exception {
        LOGGER.info("HTTP server loading...");

        ApiElementService service = new ApiElementService();
        Router router = Router.router(vertx);

        for (ApiElement e : service.getAll())
            router.get(e.getUrl()).handler(e::execute);

        router.errorHandler(500, this::handleUnmanagedError);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080);

        LOGGER.info("HTTP server running...");
    }

    private void handleUnmanagedError(io.vertx.ext.web.RoutingContext context) {
        context.response().end("Unmanaged error, sorry master..");
        LOGGER.error("Unmanaged error for : "
                + context.request().uri()
                + " - " + context.request().remoteAddress().host());
    }
}
