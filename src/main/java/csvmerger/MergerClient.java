package csvmerger;

import csvmerger.utils.Merger;

/**
 * Created by angel on 28/11/15.
 */
public class MergerClient {

    public static void main(String[] args){
        Merger merger = new Merger();

        /* MINIMUM set of data
        merger.fillExtendedList("/home/angel/IdeaProjects/CSVMerger/src/main/resources/test_file_reduced.csv", ',');

        //merger.outputExtendedList();
        merger.createExtendedFile("/home/angel/IdeaProjects/CSVMerger/src/main/resources/base_file.csv",
                ',',
                "/home/angel/IdeaProjects/CSVMerger/src/main/resources/result_reduced.csv");
        */

        /* NORMAL set of data
        merger.fillExtendedList("/home/angel/IdeaProjects/CSVMerger/src/main/resources/test_file.csv", ',');
        //merger.outputExtendedList();
        merger.createExtendedFile("/home/angel/IdeaProjects/CSVMerger/src/main/resources/base_file.csv",
                ',',
                "/home/angel/IdeaProjects/CSVMerger/src/main/resources/result.csv");
        */

        // HUGE set of data
        merger.fillExtendedList("/home/angel/IdeaProjects/CSVMerger/src/main/resources/test_many_columns.csv", ',');
        //merger.outputExtendedList();
        merger.createExtendedFile("/home/angel/IdeaProjects/CSVMerger/src/main/resources/base_many_columns.csv",
                ',',
                "/home/angel/IdeaProjects/CSVMerger/src/main/resources/result_many_columns.csv");

    }
}
