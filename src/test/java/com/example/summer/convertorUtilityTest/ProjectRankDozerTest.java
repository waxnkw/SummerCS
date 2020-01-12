package com.example.summer.convertorUtilityTest;

import com.example.summer.domain.ProjectRankPO;
import com.example.summer.model.ProjectModel;
import com.example.summer.model.ProjectRankModel;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import com.example.summer.utility.json.JsonStringUitility;
import org.junit.Test;

import java.util.LinkedHashMap;

public class ProjectRankDozerTest {
    @Test
    public void test1(){
        LinkedHashMap<String, Integer> rankMap= new LinkedHashMap<>();
        rankMap.put("1",1);
        rankMap.put("2",2);
        ProjectRankModel model = ProjectRankModel.builder()
                .projectId("proj123")
                .rankMap(rankMap).build();
        ProjectRankPO po = DozerMapSingleton.getInstance().map(model,ProjectRankPO.class);
        System.out.println(JsonStringUitility.toString(po));
        ProjectRankModel model1 = DozerMapSingleton.getInstance().map(po, ProjectRankModel.class);
        System.out.println(JsonStringUitility.toString(model1));
    }
}
