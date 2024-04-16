package com.virtualbankv1.boundary;

import java.io.*;
import java.util.*;

public class Writer {
    public void writeCSV(String filePath, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] record : data) {
                bw.write(String.join(",", record));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
