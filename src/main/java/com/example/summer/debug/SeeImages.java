package com.example.summer.debug;

import com.example.summer.configInfo.DataAbsolutePath;
import com.example.summer.utility.json.JsonStringUitility;

public class SeeImages {
    public static void main(String [] args){
        //ReadAndWritePOsUtility.readPOs();
        DataAbsolutePath.DEBUG();
        JsonStringUitility.outJsonStringOfSer(DataAbsolutePath.DATA_PATH+"Image");
        //JsonStringUitility.outJsonStringOfSer("D:\\idea\\java\\summerCS_Phase_II\\build\\libs\\exploded\\summerCS_Phase_II-0.0.1-SNAPSHOT.war\\WEB-INF\\classes\\static\\data\\ProjectRank");
    }
}
