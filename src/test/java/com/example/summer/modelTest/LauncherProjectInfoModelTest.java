package com.example.summer.modelTest;

import com.example.summer.configInfo.DataAbsolutePath;
import com.example.summer.model.LauncherProjectInfoModel;
import com.example.summer.model.ProjectModel;
import com.example.summer.model.UserInfoModel;
import com.example.summer.stub.SimpleProjectStub;
import com.example.summer.utility.dataUtility.DeleteFileUtility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LauncherProjectInfoModelTest {

    private String projectId;
    @Before
    public void setUp() throws Exception {
        DataAbsolutePath.DEBUG();
        ProjectModel projectModel = SimpleProjectStub.generateProjectModel();
        ArrayList<UserInfoModel> userInfoModels = new ArrayList<>();
        userInfoModels.add(new UserInfoModel("zhangao"));
        projectModel.setMarkers(userInfoModels);
       // projectModel.setLauncher(new UserInfoModel("zhangao"));
        projectId = projectModel.save();
    }

    @Test
    public void testGenerate() {
        LauncherProjectInfoModel launcherProjectInfoModel = LauncherProjectInfoModel.generate(projectId, "zhangao");
        assertNotNull(launcherProjectInfoModel);
        assertEquals("只是一个例子", launcherProjectInfoModel.getDescription());
    }

    @Test
    public void testGenerate1(){
        LauncherProjectInfoModel launcherProjectInfoModel = LauncherProjectInfoModel.generate(projectId, "zhangao");
        assertEquals(launcherProjectInfoModel.isUserIn(), true);
    }

    @After
    public void tearDown(){
        DeleteFileUtility.delete(DataAbsolutePath.DATA_PATH+projectId);
    }
}