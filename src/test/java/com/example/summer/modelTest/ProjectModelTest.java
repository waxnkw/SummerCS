package com.example.summer.modelTest;

import com.example.summer.model.ProjectModel;
import com.example.summer.model.UserInfoModel;
import com.example.summer.stub.SimpleProjectStub;
import com.example.summer.utility.enums.ProjectTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
 * @program: summerCS_Phase_II
 * @description: dozer测试用model
 * @author: Mr.Wang
 * @create: 2018/4/11
 */

public class ProjectModelTest {
    ProjectModel model;
    ProjectModel savedModel;
    String id;
    @Before
    public void initData(){
        model= SimpleProjectStub.generateProjectModel();
        id = model.save();
        savedModel=ProjectModel.queryProjectById(id);
    }

    @Test
    public void test1(){
        ProjectModel project= new ProjectModel();
        project.setProjectType(ProjectTypeEnum.SIMPLE);
        System.out.println(JSONObject.fromObject(project).toString());
    }

    @Test
    public void testSave(){
        //model.save();
        Assert.assertEquals("测试项目",savedModel.getProjectName());
    }

    @Test
    public void testAddNewMarker(){
        savedModel.addNewMarker("pika");
        ProjectModel project=ProjectModel.queryProjectById(id);
        ArrayList<UserInfoModel> users=project.getMarkers();
        Assert.assertEquals("pika",users.get(2).getUsername());
    }

    @Test
    public void testCanBeJoinedByTheUser(){
        ProjectModel project=ProjectModel.queryProjectById(id);
        Assert.assertEquals(true,project.canBeJoinedByTheUser("bei"));
    }
}
