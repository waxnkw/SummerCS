package com.example.summer.utility.dataUtility;

import lombok.Synchronized;

import java.io.File;

public class DeleteFileUtility {
    @Synchronized
    public static void delete(String path){
        File file = new File(path);
        if (!file.exists()){return ;}
        file.delete();
    }
}
