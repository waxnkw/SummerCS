package com.example.summer.modelTest;

import com.example.summer.model.ProjectInfoModel;
import com.example.summer.model.ProjectModel;
import com.example.summer.stub.SimpleProjectStub;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectInfoModelTest {

    @Before
    public void initData(){
        ProjectModel model = SimpleProjectStub.generateProjectModel();
        model.setCanBeJoined(true);
        model.save();
    }

    @Test
    public void listCanbeJoinedProjectInfos() {
        int num = 0;
        if(ProjectInfoModel.listCanbeJoinedProjectInfos()!=null){
            num = ProjectInfoModel.listCanbeJoinedProjectInfos().size();
        }
        assertNotEquals(0, num);
    }

    @Test
    public void listLaunchedProjectInfosByUserName() {
        assertNotNull(ProjectInfoModel.listLaunchedProjectInfosByUserName("测试者1"));
    }

    @Test
    public void listJoinedProjectInfos(){
        assertNotNull(ProjectInfoModel.listJoinedProjectInfos("beibei"));
    }
}