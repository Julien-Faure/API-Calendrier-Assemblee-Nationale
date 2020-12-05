package fr.assemblee.entities;

import java.util.Collections;
import java.util.List;

public class Calendar {
    private final List<Entry> entries;

    public Calendar(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return Collections.unmodifiableList(entries);
    }
}
