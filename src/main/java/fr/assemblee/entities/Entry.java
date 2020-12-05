package fr.assemblee.entities;

import java.util.Date;

public class Entry {
    private final Date date;
    private final String subject;

    public Entry(Date date, String subject) {
        this.date = date;
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }
}
