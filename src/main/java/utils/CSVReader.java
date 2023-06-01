package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static final String MAN_LIST_FILE_PATH = new PropertiesReader("paths.properties").getProperty("man_list");
    public static final String WOMAN_LIST_FILE_PATH = new PropertiesReader("paths.properties").getProperty("women_list");
    public static final String MARRIED_MEN_LIST_FILE_PATH = new PropertiesReader("paths.properties").getProperty("married_man_list");


    public static class DataRecord {
        String firstName;
        String lastName;
        int age;
        boolean isMarried;

        DataRecord(String firstName, String lastName, int age, boolean isMarried) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.isMarried = isMarried;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAge() {
            return age;
        }

        public boolean isMarried() {
            return isMarried;
        }
    }

    public static List<DataRecord> readCsvFile(String filePath) {
        List<DataRecord> records = new ArrayList<>();
        Path pathToFile = Paths.get(filePath);

        try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");

                String firstName = attributes[0];
                String lastName = attributes[1];
                int age = Integer.parseInt(attributes[2]);
                boolean isMarried = Boolean.parseBoolean(attributes[3]);

                DataRecord record = new DataRecord(firstName, lastName, age, isMarried);
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;

    }

}
