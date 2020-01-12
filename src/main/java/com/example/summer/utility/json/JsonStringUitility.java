package com.example.summer.utility.json;

import com.example.summer.domain.PO;
import com.example.summer.utility.dataUtility.ReadAndWritePOsUtility;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.File;
import java.util.*;

public class JsonStringUitility {
    public  static  String toString(Object o){
        String result ="";
        if (o.getClass().equals(ArrayList.class)){
            for (Object o1 : (ArrayList<Object>) (o)) {
                result+=  JSONArray.fromObject(o1).toString()+System.lineSeparator();
            }
            return result;
        }
        return JSONObject.fromObject(o).toString();
    }
    public static void outJsonStringOfSer(String path){
        File file = new File(path);
        if (file.isDirectory()){outJsonOfStringOfDir(path);}
        else if(file.isFile()){outJsonOfSingleFile(path);}
    }

    private static void outJsonOfSingleFile(String path){
        PO po = ReadAndWritePOsUtility.readPO(path);
        System.out.println(toString(po));
    }

    private static void outJsonOfStringOfDir(String path){
        ArrayList<PO> pos =  ReadAndWritePOsUtility.readPOs(path);
        pos.forEach(e->System.out.println(toString(e)));
    }
}
