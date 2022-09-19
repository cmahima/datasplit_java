package org.datasplit.util;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.datasplit.domain.Data;


public class LoadDataSet {
    public final static Data load(String filepath) throws IOException {
        
        return new Data(
                getData(filepath),
                getLabels(filepath)
                
        );
    }

     final static double[][] getData(String filepath) throws IOException {
         BufferedReader b = new BufferedReader(new FileReader(filepath));
         String str=null;
         ArrayList<String> lines = new ArrayList<String>();
         while((str = b.readLine()) != null){
             lines.add(str);
         }
         String[] strArr = lines.toArray(new String[lines.size()]);
         b.close();

         // GET DIMENSIONS: number of rows
         int nRows = strArr.length;
         // GET DIMENSIONS: number of elements in the first line
         int nCols = (strArr[0].length()-strArr[0].replace(",", "").length())+1;

         // INITIALIZE LONG 2D ARRAY (MATRIX)
         double[][] data = new double[nRows][nCols];

         // SPLIT EACH STRING OF ROW INTO SUBSTRING AND PARSE TO LONG FORMAT
         String[] split = new String[nCols];
         for (int r=0; r<nRows; r++){
             split = strArr[r].split(",");
             for (int c=0; c<nCols; c++) {
                 data[r][c] = Double.parseDouble(split[c]);
             }
         }
         b.close();

         return data;

    }
    final static int [] getLabels(String filepath) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader(filepath));
        String str = null;
        ArrayList<String> lines = new ArrayList<String>();
        while ((str = b.readLine()) != null) {
            lines.add(str);
        }
        String[] strArr = lines.toArray(new String[lines.size()]);
        b.close();

        // GET DIMENSIONS: number of rows
        int nRows = strArr.length;
        // GET DIMENSIONS: number of elements in the first line
        int nCols = (strArr[0].length() - strArr[0].replace(",", "").length()) + 1;

        // INITIALIZE LONG 2D ARRAY (MATRIX)
        int[] labels = new int[nRows];

        // SPLIT EACH STRING OF ROW INTO SUBSTRING AND PARSE TO LONG FORMAT
        String[] split = new String[nCols];

        for (int r = 0; r < nRows; r++) {
            split = strArr[r].split(",");
            labels[r] = Integer.parseInt(split[nCols-1]);

        }
        b.close();

        return labels;
    }

    }

