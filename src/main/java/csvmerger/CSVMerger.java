package csvmerger;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVMerger {
    private static final String RESULT_FILE_SUFFIX = ".merged";
    private static final char DELIMITER = ',';
    private Map<String, String[]> newValuesList;
    private String[] newHeaders;

    /*
            * This method merge content of some columns of file2 to file1 in a new file, joining this columns
            * by the value of a specific column.
            * @param file1 : target file where will be merged columns.
            * @param file2 : source file where is stored data that we want to merge.
            * @param joinColumn: index of column used for the joining. Starting from 0.
            * @param listColumns: columns that will be added to new file.
            * */
    public File merge(File file1, File file2, int joinColumn, List<Integer> listColumns) {
        File resultFile = new File(file1.getName() + RESULT_FILE_SUFFIX);
        Map<String, String[]> newValuesList = createMapWithValuesToAdd(file2, joinColumn, listColumns);
        return createExtendedFile(file1.getPath(), joinColumn);
    }

    private Map<String, String[]> createMapWithValuesToAdd(File file2, int joinColumn, List<Integer> listColumns){
        String[] record;
        newValuesList = new HashMap<String, String[]>();
        newHeaders = new String[listColumns.size()];
        try {
            CSVReader reader = new CSVReader(new FileReader(file2.getPath()));
            record = reader.readNext(); // HEADER
            for(int i = 0; i <= listColumns.size() - 1; i++){
                newHeaders[i] = record[listColumns.get(i)];
            }
            while ((record = reader.readNext()) != null){
                if (!newValuesList.containsKey(record[joinColumn])){
                    try {
                        String[] newValues= new String[listColumns.size()];
                        for(int i = 0; i <= listColumns.size() - 1; i++){
                            newValues[i] = record[listColumns.get(i)];
                        }
                        newValuesList.put(record[joinColumn], newValues);
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newValuesList;
    }

    private File createExtendedFile(String filename,int joinColumn){
        String[] record = null;
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(filename + RESULT_FILE_SUFFIX),',');
            CSVReader reader = new CSVReader(new FileReader(filename));
            record = reader.readNext(); // HEADER
            int newLength = record.length + newHeaders.length;
            String[] newRecord = Arrays.copyOf(record, newLength);
            int j = 0;
            for(int i = record.length ; i <= newLength - 1; i++){
                newRecord[i] = newHeaders[j];
                j++;
            }
            csvWriter.writeNext(newRecord);
            String[] newColumnValues;
            while ((record = reader.readNext()) != null){
                if (newValuesList.containsKey(record[joinColumn])){
                    newRecord = Arrays.copyOf(record, newLength);
                    j = 0;
                    newColumnValues = newValuesList.get(record[joinColumn]);
                    for(int i = record.length ; i <= newLength - 1; i++){
                        newRecord[i] = newColumnValues[j];
                        j++;
                    }
                    csvWriter.writeNext(newRecord);
                }else{
                    newRecord = Arrays.copyOf(record, newLength);
                    j = 0;
                    for(int i = record.length ; i <= newLength - 1; i++){
                        newRecord[i] = "";
                        j++;
                    }
                    csvWriter.writeNext(newRecord);
                }
            }
            csvWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(filename + RESULT_FILE_SUFFIX);
    }
}
