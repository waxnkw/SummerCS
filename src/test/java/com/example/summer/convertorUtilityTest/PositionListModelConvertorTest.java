package com.example.summer.convertorUtilityTest;

import com.example.summer.domain.PositionPO;
import com.example.summer.model.PositionModel;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionListModelConvertorTest {
    @Test
    public void testPOtoModel1(){
        ArrayList<PositionPO> pos  = new ArrayList<>();
        PositionPO po1 = new PositionPO(1,2);
        PositionPO po2 = new PositionPO(2,2);
        PositionPO po3 = new PositionPO(3,2);
        pos.add(po1);
        pos.add(po2);
        pos.add(po3);
        int i = 0;

    }

    @Test
    public void test(){
        System.out.println();
    }

}
