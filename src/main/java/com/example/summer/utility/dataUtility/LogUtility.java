package com.example.summer.utility.dataUtility;

import java.io.*;

public class LogUtility {
    public static void write(String content){
        File file =new File("D:/idea/java/SummerCSHomeworkLog.txt");
        try {
            OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(file) ,"utf8");
            osw.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
