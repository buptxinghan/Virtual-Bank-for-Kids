package com.virtualbankv2.control;

import com.virtualbankv2.boundary.GoalOverviewUI;
import java.io.*;

import static com.virtualbankv2.control.VirtualBankApplication.currentUser;

public class GoalManager {
    public  void removeGoalIfComplete() {
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
                data = currentLine.split(",");  // 假设CSV文件使用逗号分隔
                if (data[4].equals(currentUser.getUsername())) {
                    // 检查目标是否达成
                    double currentAmount = Double.parseDouble(data[3]);
                    double targetAmount = Double.parseDouble(data[2]);
                    if (currentAmount >= targetAmount) {
                        continue; // 如果目标达成，跳过这一行，不写入新文件
                    }
                }
                // 将未达成目标的行写入新文件
                bw.write(currentLine + "\n");
            }
            br.close();
            bw.close();
            oldFile.delete(); // 删除原文件
            File dump = new File("src/Data/Goals.csv");
            newFile.renameTo(dump); // 重命名临时文件为原文件名

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
