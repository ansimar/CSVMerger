package csvmerger;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import csvmerger.pojo.BaseExtended;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CSVMergerTest {
    private static final String HEADER = "common_header,header_1_a,header_2_a,header_3_a,header_1_b,header_2_b,header_3_b";
    private static final String ROW1 = "A1,A1,B1,C1,A2,B2,C2";
    private static final String ROW2 = "A2,D1,E1,F1,D2,E2,F2";
    private static final String ROW3 = "A3,G1,H1,I1,G2,H2,I2";

    @Test
    public void testJoiningTwoSmallCsvFiles(){
        try {
            File file1 = new File( this.getClass().getResource( "/small_file_1.txt" ).toURI() );
            File file2 = new File( this.getClass().getResource( "/small_file_2.txt" ).toURI() );
            List<Integer> listColumns = Arrays.asList(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3));
            CSVMerger merger = new CSVMerger();
            File resultFile = merger.merge(file1, file2, 0, listColumns);

            CSVReader reader = new CSVReader(new FileReader(resultFile.getPath()));
            String[] record = null;
            record = reader.readNext(); // HEADER
            assertEquals(HEADER, arrayOfStringsToString(record));
            record = reader.readNext(); // ROW 1
            assertEquals(ROW1, arrayOfStringsToString(record));
            record = reader.readNext(); // ROW 2
            assertEquals(ROW2, arrayOfStringsToString(record));
            record = reader.readNext(); // ROW 3
            assertEquals(ROW3, arrayOfStringsToString(record));
        } catch (URISyntaxException e) {
           e.printStackTrace();
            assertTrue(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            assertTrue(false);
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    public String arrayOfStringsToString(String[] strings){
        StringBuilder builder = new StringBuilder();
        for(String s : strings) {
            builder.append(s).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
