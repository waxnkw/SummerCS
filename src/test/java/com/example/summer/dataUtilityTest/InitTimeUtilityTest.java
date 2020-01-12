package com.example.summer.dataUtilityTest;

import com.example.summer.utility.dataUtility.InitTimeUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitTimeUtilityTest {

    @Test
      public void testGetCurrentTime(){
          String currentTime= InitTimeUtility.getCurrentTime();
          File file=new File(currentTime);
          file.mkdirs();
      }
}
