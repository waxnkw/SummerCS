package com.example.summer.convertorUtilityTest;

import com.example.summer.domain.MarkerInfoPO;
import com.example.summer.domain.ProjectPO;
import com.example.summer.domain.UserInfoPO;
import com.example.summer.model.ProjectModel;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import com.example.summer.utility.enums.ProjectTypeEnum;
import net.sf.json.JSONObject;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.junit.Test;

import java.util.ArrayList;

public class ProejectDozerTest {
    @Test
    public void test1(){
        ProjectPO po = new ProjectPO();
        po.setLauncher(new UserInfoPO("12"));
        po.setCanBeJoined(false);
        po.setDescription("description");
        po.setProjectName("testproj");
       // po.setProjectType(ProjectTypeEnum.SIMPLE);
        po.setRequirements("requirements");
        po.setTotalNumOfImgs(100);
        ArrayList<UserInfoPO> markers = new ArrayList<>();
        markers.add(new UserInfoPO("name"));
        po.setMarkers(markers);

        ProjectModel model = DozerMapSingleton.getInstance().map(po, ProjectModel.class);
        System.out.println(JSONObject.fromObject(model).toString());
    }
}
