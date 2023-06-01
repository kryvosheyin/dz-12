package utils;

import org.example.Man;
import org.example.Person;
import org.example.Woman;

import java.util.List;

public class PeopleList {

    public List<CSVReader.DataRecord> menRecords = CSVReader.readCsvFile(CSVReader.MAN_LIST_FILE_PATH);
    public List<CSVReader.DataRecord> womenRecords = CSVReader.readCsvFile(CSVReader.WOMAN_LIST_FILE_PATH);
    public List<CSVReader.DataRecord> marriedManRecords = CSVReader.readCsvFile(CSVReader.MARRIED_MEN_LIST_FILE_PATH);

//    public Man[] menObjArr = getListOfMen(menRecords, new Man[menRecords.size()]);
//
//    public Woman[] womenObjArr = getListOfWomen(womenRecords, new Woman[womenRecords.size()]);


    public static Man[] getListOfMen(List<CSVReader.DataRecord> record, Man[] arr) {
        for (int i = 0; i < record.size(); i++) {
            arr[i] = new Man(record.get(i).getFirstName(), record.get(i).getLastName(), record.get(i).getAge(), record.get(i).isMarried());
        }
        return arr;
    }

    public static Woman[] getListOfWomen(List<CSVReader.DataRecord> record, Woman[] arr) {
        for (int i = 0; i < record.size(); i++) {
            arr[i] = new Woman(record.get(i).getFirstName(), record.get(i).getLastName(), record.get(i).getAge(), record.get(i).isMarried());
        }
        return arr;
    }
}