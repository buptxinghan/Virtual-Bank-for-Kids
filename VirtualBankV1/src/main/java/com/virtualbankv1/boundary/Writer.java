package com.virtualbankv1.boundary;

import java.io.*;
import java.util.*;

public class Writer {
    public void writeCSV(String filePath, List<String[]> data) {
        try {
            FileWriter fw = new FileWriter(filePath,true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String[] record : data) {
                bw.newLine();
                bw.write(String.join(",", record));
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
