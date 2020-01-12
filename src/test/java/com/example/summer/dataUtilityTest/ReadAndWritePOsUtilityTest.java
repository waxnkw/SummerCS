
package com.example.summer.dataUtilityTest;
import com.example.summer.domain.TestPO;
import com.example.summer.utility.dataUtility.ReadAndWritePOsUtility;
import com.example.summer.domain.PO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadAndWritePOsUtilityTest {
    @Test
    public void testReadPOs(){
        ArrayList<PO> pos= ReadAndWritePOsUtility.readPOs("data/Test");
        ArrayList<TestPO> testPOs=new ArrayList<TestPO>();
        for(int i=0;i<=pos.size()-1;i++){
            PO po=pos.get(i);
            if(po instanceof TestPO){
                TestPO testPO=(TestPO)po;
                testPOs.add(testPO);
            }
        }
        for(int i=0;i<=testPOs.size()-1;i++){
            TestPO testPO=testPOs.get(i);
            System.out.println(testPO.getID());
        }
    }


    public void testWritePOs() {
      TestPO test1=new TestPO();
      TestPO test2=new TestPO();
      test1.setId("1");
      test2.setId("2");
      ArrayList<TestPO> tests=new ArrayList<TestPO>();
      tests.add(test1);
      tests.add(test2);
      ReadAndWritePOsUtility.writePOs(tests,"data/Test");



    }
}
