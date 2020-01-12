package com.example.summer.dataUtilityTest;

import com.example.summer.utility.dataUtility.ZipFileUtility;
import org.junit.Test;

public class ZipFileUtilityTest {
    private String zipPath ="D:/idea/java/summerCS_Phase_II/src/test/resources/zipFileTest/Pictures.zip";
    private String tarPath ="D:/idea/java/summerCS_Phase_II/src/test/resources/zipFileTest/";

    @Test
    public void testUnzipFile(){
        ZipFileUtility.unZipFiles(zipPath, tarPath);
    }
}
