package com.example.summer.convertorUtilityTest;

import com.example.summer.domain.ProjectPO;
import com.example.summer.domain.SimpleProjectPO;
import org.junit.Test;

public class ProjectTest {
    @Test
    public void test(){
        ProjectPO po = new SimpleProjectPO();
        System.out.println(po instanceof SimpleProjectPO);
    }
}
