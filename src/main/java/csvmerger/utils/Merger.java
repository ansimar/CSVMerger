package csvmerger.utils;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import csvmerger.pojo.BaseExtended;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by angel on 28/11/15.
 */
public class Merger {

    Map<String, BaseExtended> extendedList = new HashMap<String, BaseExtended>();

    public Merger(){}

    public void fillExtendedList(String filename, char delimiter){
        CSVParser parser = new CSVParser(delimiter);
        String[] record = null;
        try {
            CSVReader reader = new CSVReader(new FileReader(filename));
            reader.readNext(); // HEADER
            while ((record = reader.readNext()) != null){
                if (!extendedList.containsKey(record[0])){
                    try {
                        BaseExtended baseExtended = new BaseExtended();
                        baseExtended.setBaseId(record[0]);
                        baseExtended.setBaseAmount1(record[1]);
                        baseExtended.setBaseAmount2(record[2]);
                        extendedList.put(baseExtended.getBaseId(), baseExtended);
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println(record);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createExtendedCSV(String filename){

    }

    public void outputExtendedList(){
        for(Map.Entry entry: extendedList.entrySet()){
            System.out.println(extendedList.get(entry.getKey()).toString());
        }
    }

    public void createExtendedFile(String baseFile, char delimiter, String finalFile){
        CSVParser parser = new CSVParser(delimiter);
        String[] record = null;
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(finalFile),',');
            CSVReader reader = new CSVReader(new FileReader(baseFile));
            record = reader.readNext(); // HEADER
            String[] newRecord = Arrays.copyOf(record, record.length + 2);
            Arrays.copyOf(record, record.length + 2);
            newRecord[ newRecord.length - 2] = "amount 1";
            newRecord[ newRecord.length - 1] = "amount 2";
            csvWriter.writeNext(newRecord);
            while ((record = reader.readNext()) != null){
                if (extendedList.containsKey(record[0])){
                    BaseExtended base = extendedList.get(record[0]);
                    newRecord = Arrays.copyOf(record, record.length + 2);
                    Arrays.copyOf(record, record.length + 2);
                    newRecord[newRecord.length - 2] = base.getBaseAmount1();
                    newRecord[newRecord.length - 1] = base.getBaseAmount2();
                    csvWriter.writeNext(newRecord);
                }else{
                    BaseExtended base = extendedList.get(record[0]);
                    newRecord = Arrays.copyOf(record, record.length + 2);
                    Arrays.copyOf(record, record.length + 2);
                    newRecord[newRecord.length - 2] = "";
                    newRecord[newRecord.length - 1] = "";
                    csvWriter.writeNext(newRecord);

                }
            }
            csvWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
