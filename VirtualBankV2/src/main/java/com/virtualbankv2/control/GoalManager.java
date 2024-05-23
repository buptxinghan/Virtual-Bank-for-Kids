package com.virtualbankv2.control;

import com.virtualbankv2.boundary.GoalOverviewUI;
import java.io.*;

import static com.virtualbankv2.control.VirtualBankApplication.currentUser;

/**
 * The {@code GoalManager} class provides functionality to manage user goals in the Virtual Bank application.
 * It includes methods to remove completed goals from the goals list.
 *
 * @version 2.0
 * @since 2024-05-10
 * @author Tianzhi Li
 *
 */
public class GoalManager {

    /**
     * Removes goals that have been completed from the goals list.
     * It reads the goals from a CSV file, checks if the goals are completed, and writes the
     * incomplete goals to a temporary file, then replaces the original file with the temporary file.
     */
    public void removeGoalIfComplete() {
        String tempFile = "Goals_temp.csv";
        File oldFile = new File("src/Data/Goals.csv");
        File newFile = new File(tempFile);

        String currentLine;
        String[] data;

        try {
            FileReader fr = new FileReader(oldFile);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(newFile);
            BufferedWriter bw = new BufferedWriter(fw);

            while ((currentLine = br.readLine()) != null) {
                data = currentLine.split(",");  // Assuming CSV file uses commas as separators
                if (data[4].equals(currentUser.getUsername())) {
                    // Check if the goal is achieved
                    double currentAmount = Double.parseDouble(data[3]);
                    double targetAmount = Double.parseDouble(data[2]);
                    if (currentAmount >= targetAmount) {
                        continue; // If goal is achieved, skip this line and do not write to the new file
                    }
                }
                // Write the lines of goals that are not achieved to the new file
                bw.write(currentLine + "\n");
            }
            br.close();
            bw.close();
            oldFile.delete(); // Delete the original file
            File dump = new File("src/Data/Goals.csv");
            newFile.renameTo(dump); // Rename the temporary file to the original file name

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
