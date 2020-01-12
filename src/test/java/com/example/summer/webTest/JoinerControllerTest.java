package com.example.summer.webTest;

import com.example.summer.model.SingleUserImageModel;
import com.example.summer.web.WorkerController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JoinerControllerTest {

    @Test
    public void testQueryImagesByUserIdAndProjectId(){
//        ArrayList<SingleUserImageModel> images
//                = new WorkerController().queryImagesByUserNameAndProjectId("zhangao","human");
//        for (SingleUserImageModel model: images){
//            System.out.println(model.getFileName());
//            Assert.assertEquals("human",model.getProjectId());
//        }
    }
}
