package com.example.summer.utility.dataUtility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveImageFlowUtility {
    public static void save(String pathName,byte[] bytes) {
        if ((pathName != null) && (bytes != null)) {
            try {
                File imageFile = new File(pathName);
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(bytes);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
