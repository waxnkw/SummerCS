package com.example.summer.configInfo;


import com.example.summer.ApplicationConfig;

public class DataAbsolutePath {
    //public static final String DATA_PATH = "C:/home/admin/summerCS_Phase_I/data/";
    //public static final String DATA_PATH = "D:/idea/java/summerCS_Phase_I/data/";
    //public static final String DATA_PATH = "/static/data/";

    public static String DATA_PATH = ApplicationConfig.class.getResource("/").getPath()+"static/data/";
    //public static final String DATA_PATH = "D:/idea/java/summerCS_Phase_II/build/libs/exploded/summerCS_Phase_II-0.0.1-SNAPSHOT.war/WEB-INF/classes/static/data/";

    public static void DEBUG(){
        DATA_PATH = "build/libs/exploded/summerCS_Phase_II-0.0.1-SNAPSHOT.war/WEB-INF/classes/static/data/";
    }
}
