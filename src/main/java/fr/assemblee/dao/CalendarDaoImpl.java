package fr.assemblee.dao;

import fr.assemblee.entities.Calendar;
import fr.assemblee.entities.Entry;
import fr.assemblee.io.CsvParser;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarDaoImpl implements CalendarDao, LoadableInMemory {

    static final Logger LOGGER = Logger.getLogger(CalendarDaoImpl.class);

    public static final String DELIMITER = "\",\"";

    private final File dbFile;
    private Calendar calendar;

    public CalendarDaoImpl(String dbPath) {
        dbFile = new File(dbPath);
    }

    @Override
    public Calendar getCalendar() throws IOException {
        if (calendar == null) loadInMemory();
        return calendar;
    }

    @Override
    public void loadInMemory() throws IOException {
        CsvParser csvParser = new CsvParser(DELIMITER);
        List<List<String>> records = csvParser.parse(dbFile.getPath());
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < records.size(); i++) {
            List<String> line = records.get(i);
            checkLineFormat(line);
            try {
                Entry entry = getEntry(line);
                entries.add(entry);
            } catch (ParseException e) {
                LOGGER.error("Parsing error for the folowing line number " + i);
            }
        }

        calendar = new Calendar(entries);
    }

    private void checkLineFormat(List<String> line) throws IOException {
        if (line.size() != 3) throw new IOException("Incorrect database content detected, bad number of columns.");
    }


    private Entry getEntry(List<String> line) throws ParseException {
        Date date = new SimpleDateFormat("\"yyyy-MM-dd-HH:mm").parse(line.get(0) + "-" + line.get(1));
        return new Entry(date, line.get(2).replace("\"",""));
    }
}
