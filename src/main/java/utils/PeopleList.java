package utils;

import org.example.Man;
import org.example.Person;
import org.example.Woman;

import java.util.List;
import java.util.function.Function;

public class PeopleList {

    public static List<DataRecord> menRecords = CSVReader.readCsvFile(CSVReader.MAN_LIST_FILE_PATH);
    public static List<DataRecord> womenRecords = CSVReader.readCsvFile(CSVReader.WOMAN_LIST_FILE_PATH);
    public static List<DataRecord> marriedManRecords = CSVReader.readCsvFile(CSVReader.MARRIED_MEN_LIST_FILE_PATH);


    public static <T extends Person> T[] getListOfPeople(List<DataRecord> record, T[] arr, Function<DataRecord, T> personCreator) {
        for (int i = 0; i < record.size(); i++) {
            arr[i] = personCreator.apply(record.get(i));
        }
        return arr;
    }
}