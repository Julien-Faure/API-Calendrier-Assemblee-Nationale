package fr.assemblee;

import io.vertx.ext.web.RoutingContext;

public interface ApiElement {
    String getUrl();
    void execute(RoutingContext context);
}
