package fr.assemblee;

import fr.assemblee.dao.CalendarDao;
import fr.assemblee.dao.CalendarDaoImpl;

public abstract class Application {
    private static CalendarDao calendarDao;

    public static void init() throws Exception {
        calendarDao = new CalendarDaoImpl("db/db.csv");
    }

    public static CalendarDao getCalendarDao() {
        return calendarDao;
    }
}
