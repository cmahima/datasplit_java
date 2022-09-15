package org.datasplit;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Data {
    private Array2DRowRealMatrix data;
    private int[] labels;
    private String[] headers;
    final static String COL_PREFIX = "V";

    public Data(double[][] data, int[] labels) {
        this.data= new Array2DRowRealMatrix(data,false);
        this.labels= labels;
        this.headers = genHeaders(this.data.getColumnDimension());
    }

    private static String[] genHeaders(int size) {
        String[] out = new String[size];
        for (int i = 0; i < size; i++)
            out[i] = COL_PREFIX + i;
        return out;
    }
    public Data(double[][] data, int[] labels, String[] headers){
        this.data= new Array2DRowRealMatrix(data,false);
        this.labels= labels;
        this.headers = headers;
    }
    public Data(Array2DRowRealMatrix data, int[] labels, String[] headers){
        this.data=data;
        this.labels= labels;
        this.headers = headers;
    }
    public int numRows() {
        return data.getRowDimension();
    }

    public Data shuffle() {
        final int m = numRows();
        boolean has_labels = null != labels; // if the labels are null, there are no labels to shuffle...

        /*
         * Generate range of indices...
         */
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for(int i = 0; i < m; i++)
            indices.add(i);

        /*
         * Shuffle indices in place...
         */
        Collections.shuffle(indices);
        final int[] newLabels = has_labels ? new int[m] : null;
        final double[][] newData = new double[m][];

        /*
         * Reorder things...
         */
        int j = 0;
        for(Integer idx: indices) {
            if(has_labels) {
                newLabels[j] = this.labels[idx];
            }

            newData[j] = this.data.getRow(idx);
            j++;
        }

        return new Data(
                newData,
                newLabels,
                this.headers

        );
    }
    public static double[][] matrixSlice(final double[][] a, final int startInc, final int endExc) {

        if(endExc > a.length)
            throw new ArrayIndexOutOfBoundsException(endExc);
        if(startInc < 0 || startInc > a.length)
            throw new ArrayIndexOutOfBoundsException(startInc);
        if(startInc > endExc)
            throw new IllegalArgumentException("start index cannot exceed end index");
        if(startInc == endExc)
            return new double[][]{};

        final double[][] out = new double[endExc - startInc][];
        for(int i = startInc, j = 0; i < endExc; i++, j++)
            out[j] = a[i];

        return out;
    }

    public static int[] vectorSlice(final int[] a, final int startInc, final int endExc) {

        if(endExc > a.length)
            throw new ArrayIndexOutOfBoundsException(endExc);
        if(startInc < 0 || startInc > a.length)
            throw new ArrayIndexOutOfBoundsException(startInc);
        if(startInc > endExc)
            throw new IllegalArgumentException("start index cannot exceed end index");
        if(startInc == endExc)
            return new int[]{};

        final int[] out = new int[endExc - startInc];
        for(int i = startInc, j = 0; i < endExc; i++, j++)
            out[j] = a[i];

        return out;
    }
    public Data slice(int start, int end){
        int[] labs = (null == labels) ? null : vectorSlice(labels, start, end);
        return new Data(
                matrixSlice(this.data.getDataRef(), start, end),
                labs,
                this.headers
        );
    }

    public Data copy() {
        return new Data(this.data, this.labels, this.headers);
    }

    public Data display(){
        String ls = System.getProperty("line.separator");
        String lsls = ls + ls;
        StringBuilder sb = new StringBuilder();

        sb.append("Headers:" + ls);
        sb.append(Arrays.toString(headers) + lsls);

        sb.append("Data:");
        sb.append((data)+ ls);

        sb.append("Labels:"+ls);
        sb.append(Arrays.toString (labels));
        System.out.println(sb);
        return new Data(
                this.data,
                this.labels,
                this.headers

        );
    }
}




