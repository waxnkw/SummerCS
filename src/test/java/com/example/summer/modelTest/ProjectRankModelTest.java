package com.example.summer.modelTest;

import com.example.summer.model.ProjectRankModel;
import net.sf.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectRankModelTest {

    private ProjectRankModel projectRankModel = new ProjectRankModel();

    @Before
    public void generateProjectRankModel(){
        LinkedHashMap<String, Integer> rankMap = new LinkedHashMap<>();
        rankMap.put("zhangao",12);
        rankMap.put("wny",20);
        rankMap.put("xiaoming",3);
        rankMap.put("xiaogang",885);
        projectRankModel.setRankMap(rankMap);
        projectRankModel.setProjectId("123456");
    }

    @Test
    public void testGetSortedList(){
        String out = JSONArray.fromObject(projectRankModel.getSortedList()).toString();
        System.out.println(out);
    }

    @Test
    public void testGetRanks(){
//        ArrayList<Map.Entry<String,Integer>> list = projectRankModel.getRankList(1,2);
//        String out = JSONArray.fromObject(projectRankModel.getSortedList()).toString();
//        System.out.println(out);
//        Assert.assertEquals("wny",list.get(0).getKey());
//        Assert.assertEquals("zhangao",list.get(1).getKey());
    }

    @Test
    public void testGetRankByUserName(){
        Assert.assertEquals(1,projectRankModel.getRankByUsername("xiaogang"));
        Assert.assertEquals(2,projectRankModel.getRankByUsername("wny"));
        Assert.assertEquals(3,projectRankModel.getRankByUsername("zhangao"));
    }
}
