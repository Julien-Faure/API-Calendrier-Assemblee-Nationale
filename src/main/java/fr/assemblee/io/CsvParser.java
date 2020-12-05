package fr.assemblee.io;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvParser {

    private static final Logger LOGGER = Logger.getLogger(CsvParser.class);

    private String delimiter;

    public CsvParser(String delimiter) {
        this.delimiter = delimiter;
    }

    public List<List<String>> parse(String csvFilePath) throws IOException {
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }

        return records;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}
