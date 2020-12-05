package fr.assemblee.dao;

import fr.assemblee.entities.Calendar;

import java.io.IOException;

public interface CalendarDao {
    Calendar getCalendar() throws IOException;
}
