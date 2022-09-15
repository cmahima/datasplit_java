package org.datasplit;
import org.datasplit.Data;
import java.io.IOException;

public class Main {

    public static void main (String[] args) throws IOException {
        Data data;
        data = LoadDataSet.load("./iris.txt");
        TrainTestSplit split = new TrainTestSplit(data, 0.8);
        split.getTest();
        split.getTrain();

    }
}
