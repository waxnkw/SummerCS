package com.example.summer.modelTest;

import com.example.summer.model.ProjectModel;
import com.example.summer.model.UserInfoModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProjectModelTest1 {
    private ProjectModel projectModel = new ProjectModel();

    @Before
    public void setUp(){
        ArrayList<UserInfoModel> userInfoModels = new ArrayList<>();
        userInfoModels.add(new UserInfoModel("zhangao"));
        projectModel.setMarkers(userInfoModels);
    }

    @Test
    public void testIsUserAlreadyIn(){
        String username = "zhangao";
        Assert.assertEquals(projectModel.isUserAlreadyIn(username), true);
        username = "123";
        Assert.assertEquals(projectModel.isUserAlreadyIn(username), false);
        projectModel.setMarkers(null);
        Assert.assertEquals(projectModel.isUserAlreadyIn(username), false);
    }
}
