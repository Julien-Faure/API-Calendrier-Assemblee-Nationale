package fr.assemblee;

import fr.assemblee.dao.CalendarDao;
import fr.assemblee.entities.Calendar;
import fr.assemblee.entities.Error;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

public class CalendarContent implements ApiElement {

    static final Logger LOGGER = Logger.getLogger(CalendarContent.class);

    @Override
    public String getUrl() {
        return "/api/v1/calendrier";
    }

    @Override
    public void execute(RoutingContext context) {
        CalendarDao dao = Application.getCalendarDao();

        try {
            Calendar calendar = dao.getCalendar();
            context.response().end(Json.encodePrettily(calendar));
        } catch (IOException e) {
            LOGGER.error(context.request().remoteAddress().host()
                    + " - " + getUrl() + " : " + "Unable to access data.", e);

            context.response().end(Json.encodePrettily(new Error("Impossible d'accéder aux données de l'assemblé.", ExceptionUtils.getStackTrace(e))));
        }
    }
}
