package com.example.summer.daoTest;

import com.example.summer.dao.ProjectRankDao;
import com.example.summer.daoImpl.ProjectRankDaoImpl;
import com.example.summer.domain.ProjectRankPO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;

public class ProjectRankDaoTest {


    @Test
    public void testSave(){

        ProjectRankDao dao=new ProjectRankDaoImpl();
          ProjectRankPO po1=new ProjectRankPO();
          po1.setProjectId("1111");
          ProjectRankPO po2=new ProjectRankPO();
          po2.setProjectId("2222");
          dao.save(po1);
          dao.save(po2);
          }

    @Test
    public void testQuery(){
        ProjectRankDao dao=new ProjectRankDaoImpl();
        ProjectRankPO po1=dao.queryProjectRankByProjectId("1111");
        Assert.assertEquals("1111",po1.getProjectId());
        Assert.assertEquals(1,po1.getRankMap().size());
        Assert.assertEquals("[key]",po1.getRankMap().keySet().toString());
        /*
        ProjectRankPO po2=dao.queryProjectRankByProjectId("2222");
        Assert.assertEquals("2222",po2.getProjectId());
        */
    }

    @Test
    public void testUpdate(){
        ProjectRankDao dao=new ProjectRankDaoImpl();
        ProjectRankPO po1=dao.queryProjectRankByProjectId("1111");
        LinkedHashMap<String, Integer> rankMap=new LinkedHashMap<String, Integer>();
        rankMap.put("key",1111);
        po1.setRankMap(rankMap);
        dao.update(po1);
    }

}
