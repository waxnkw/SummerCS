package com.example.summer.webTest;

import com.example.summer.model.ProjectStatisticModel;
import com.example.summer.utility.json.JsonStringUitility;
import com.example.summer.web.AdminController;

import org.junit.Test;

public class AdminControllerTest {
    private AdminController adminController = new AdminController();
    @Test
    public  void test1(){
        int x= adminController.queryUserNum();
        System.out.println(x);
    }

    @Test
    public void test2(){
        ProjectStatisticModel projectStatisticModel =  adminController.queryProjectStatisticData();
        System.out.println(JsonStringUitility.toString(projectStatisticModel));
    }
}
