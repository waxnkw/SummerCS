package com.example.summer.modelTest;

import com.example.summer.model.SingleUserImageModel;
import com.example.summer.model.WorkerProjectInfoModel;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class WorkerProjectInfoModelTest {
    @Test
    public void testGenerate(){
        /*WorkerProjectInfoModel model=WorkerProjectInfoModel.generate("2018-04-15_20-30-49","boogie");
        Assert.assertEquals("2018-04-15_20-30-49",model.getProjectId());
        Assert.assertEquals("测试项目",model.getProjectName());
        Assert.assertEquals("boogie",model.getUsername());
        Assert.assertEquals(2,model.getTotalNumOfImgs());
        Assert.assertEquals("只是一个例子",model.getDescription());
        Assert.assertEquals(2,model.getDoneNumOfImgs());
*/
        //ArrayList<SingleUserImageModel> imgModels=SingleUserImageModel.querySingleUserImagesByUserIdAndProjectId("2018-04-15_20-30-49","boogie");
   //     Assert.assertEquals("2018-04-15_20-31-47--0.jpg",imgModels.get(0).getFileName());
  //      Assert.assertEquals("boogie",imgModels.get(0).getMarker().getUsername());
        //Assert.assertEquals("mark1",imgModels.get(0).getMark().getMarkId());
        //Assert.assertEquals("mark1",imgModels.get(1).getMark().getMarkId());

    }

    @Test
    public void testGenerate1(){

    }
}
