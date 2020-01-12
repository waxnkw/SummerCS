package com.example.summer.stub;

import com.example.summer.domain.ProjectPO;
import com.example.summer.model.ProjectModel;
import com.example.summer.model.SimpleProjectModel;
import com.example.summer.model.UserInfoModel;
import com.example.summer.service.LauncherService;
import com.example.summer.utility.enums.ProjectTypeEnum;

import java.util.ArrayList;

/**
 * @program: summerCS_Phase_II
 * @description:
 * @author: Mr.Wang
 * @create: 2018/4/14
 **/
public class SimpleProjectStub {

    public static ProjectModel generateProjectModel(){
        ProjectModel projectModel = new SimpleProjectModel();
        projectModel.setProjectName("测试项目");
        projectModel.setDescription("只是一个例子");
        projectModel.setRequirements("需要高素质人员");
        UserInfoModel userInfo = new UserInfoModel("测试者1");
        projectModel.setLauncher(userInfo);
        ArrayList<UserInfoModel> markers = new ArrayList<>();
        markers.add(new UserInfoModel("beibei"));
        markers.add(new UserInfoModel("boogie"));
        projectModel.setMarkers(markers);
        projectModel.setTotalNumOfImgs(10);
        projectModel.setProjectType(ProjectTypeEnum.SIMPLE);
        projectModel.setCanBeJoined(true);
        return projectModel;
    }
}
