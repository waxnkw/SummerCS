package com.example.summer.serviceTest;

import com.example.summer.model.SingleUserImageModel;
import com.example.summer.service.WorkerService;
import com.example.summer.service.impl.WorkerServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class WorkerServiceTest {
    @Autowired
    private WorkerService workerService;

    @Test
    public void testQueryImagesByUserIdAndProjectId(){
        ArrayList<SingleUserImageModel> images
                = new WorkerServiceImpl().queryImagesByUserIdAndProjectId("zhangao","human",1,2);
        for (SingleUserImageModel model: images){
            System.out.println(model.getFileName());
            Assert.assertEquals("human",model.getProjectId());
        }
    }
}
