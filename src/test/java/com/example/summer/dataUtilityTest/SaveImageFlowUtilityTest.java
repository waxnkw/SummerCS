package com.example.summer.dataUtilityTest;
import com.example.summer.utility.dataUtility.SaveImageFlowUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaveImageFlowUtilityTest {
    @Test
    public void testSave(){
        try {
            File imageFile = new File("boogie.jpg");
            FileInputStream fis = new FileInputStream(imageFile);
            byte[] buffer=new byte[10240*10];//100k
            fis.read(buffer);
            SaveImageFlowUtility.save("boogie2.jpg",buffer);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
