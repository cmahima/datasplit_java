package org.datasplit.util;
import org.apache.commons.math3.util.FastMath;
import org.datasplit.domain.Data;

import java.util.Arrays;

public class TrainTestSplit {
    final private Data train;
    final private Data test;


    public TrainTestSplit(Data data, double train_ratio) {
        final int m = data.numRows();

        // validate the ratio...
        if (train_ratio <= 0.0 || train_ratio >= 1.0) {
            throw new IllegalArgumentException("train ratio must be a positive value between 0.0 and 1.0");
        } else if (m < 2) {
            throw new IllegalArgumentException("too few rows to split");
        }

        final int train_rows = FastMath.max((int) FastMath.floor((double) m * train_ratio), 1); // want to make sure at least 1...

        // build the split...
        Data shuffled = data.shuffle();
        this.train = shuffled.slice(0, train_rows);
        this.test = shuffled.slice(train_rows, m);
    }

    /**
     * Return a copy of the training set
     *
     * @return
     */
    public Data getTrain() {
        return train.display();
    }

    /**
     * Return a copy of the test set
     *
     * @return
     */
    public Data getTest() {
        return test.display();
    }
}


