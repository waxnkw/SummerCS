package com.example.summer.daoTest;

import com.example.summer.dao.ProjectDao;
import com.example.summer.domain.MarkerInfoPO;
import com.example.summer.domain.ProjectPO;
import com.example.summer.domain.UserInfoPO;
import com.example.summer.model.ProjectModel;
import com.example.summer.model.UserInfoModel;
import com.example.summer.utility.enums.ProjectTypeEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectDaoTest {
    @Autowired
    ProjectDao projectDao;

    ProjectPO projectPO1=new ProjectPO();

    ProjectPO projectPO2=new ProjectPO();

    ProjectPO projectPO3=new ProjectPO();

    //???:  it is strange that using @autowired to import projectPO object automatically makea a mistake:
    //mistake projectPO1 for projectPO2

    @Before
    public void initData(){
      /* projectPO1.setLauncher(new UserInfoPO("beibei"));
       projectPO1.setDescription("hug for boogie from beibei");
       projectPO1.setProjectName("brace boogie");
       projectPO1.setCanBeJoined(true);

        projectPO2.setLauncher(new UserInfoPO("beibei"));
        projectPO2.setDescription("light for boogie from beibei");
        projectPO2.setProjectName("give boogie light");
        projectPO2.setCanBeJoined(false);

        UserInfoPO markerPO1=new UserInfoPO();
        markerPO1.setUsername("beibei");
        UserInfoPO markerPO2=new UserInfoPO();
        markerPO2.setUsername("boogie");
        ArrayList<UserInfoPO> markers=new ArrayList<>();
        markers.add(markerPO1);
        markers.add(markerPO2);

        projectPO1.setMarkers(markers);
        projectPO2.setMarkers(markers);*/
        projectPO3.setProjectName("测试项目");
        projectPO3.setDescription("只是一个例子");
        projectPO3.setRequirements("需要高素质人员");
        UserInfoPO userInfoPO = new UserInfoPO("beibei");
        projectPO3.setLauncher(userInfoPO);
        ArrayList<UserInfoPO> markers = new ArrayList<>();
        markers.add(new UserInfoPO("beibei"));
        markers.add(new UserInfoPO("boogie"));
        projectPO3.setMarkers(markers);
        projectPO3.setTotalNumOfImgs(2);
        projectPO3.setProjectType(ProjectTypeEnum.SIMPLE);
        projectPO3.setCanBeJoined(true);
    }
    @Test
    public void testSaveProject(){
     //   projectDao.saveProject(projectPO1);
        projectDao.saveProject(projectPO3);
    }
    @Test
    public void testQueryAllProjects(){
        ArrayList<ProjectPO> projects=projectDao.listAllProjects();
        Assert.assertEquals(2,projects.size());
        Assert.assertEquals("2018-03-20_11-52-45",projects.get(0).getID());
        Assert.assertEquals("2018-03-20_11-53-16",projects.get(1).getID());
        Assert.assertEquals("brace boogie",projects.get(0).getProjectName());
        Assert.assertEquals("give boogie light",projects.get(1).getProjectName());
        Assert.assertEquals("hug for boogie from beibei",projects.get(0).getDescription());
        Assert.assertEquals("light for boogie from beibei",projects.get(1).getDescription());
    }
    @Test
    public void testQueryAllAccessibleProjects(){
        ArrayList<ProjectPO> projects=projectDao.listAllAccessibleProjects();
        Assert.assertEquals(1,projects.size());
        Assert.assertEquals("brace boogie",projects.get(0).getProjectName());
        Assert.assertEquals("hug for boogie from beibei",projects.get(0).getDescription());
    }
    @Test
    public void testQueryProjectById(){
        ProjectPO project=projectDao.queryProjectById("2018-03-20_11-52-45");
        Assert.assertEquals("mail to boogie",project.getProjectName());
        Assert.assertEquals("a mail to comfort boogie from beibei",project.getDescription());
    }
    @Test
    public void testQueryLaunchedProjectsByUserName(){
        ArrayList<ProjectPO> projects=projectDao.listLaunchedProjectsByUserName("beibei");
        Assert.assertEquals(2,projects.size());
        Assert.assertEquals("brace boogie",projects.get(0).getProjectName());
        Assert.assertEquals("give boogie light",projects.get(1).getProjectName());
        Assert.assertEquals("hug for boogie from beibei",projects.get(0).getDescription());
        Assert.assertEquals("light for boogie from beibei",projects.get(1).getDescription());
    }
    @Test
    public void testQueryJoinedProjectsByUserName(){
        ArrayList<ProjectPO> projects=projectDao.listJoinedProjectsByUserName("beibei");
        Assert.assertEquals(2,projects.size());
        Assert.assertEquals("brace boogie",projects.get(0).getProjectName());
        Assert.assertEquals("give boogie light",projects.get(1).getProjectName());
        Assert.assertEquals("hug for boogie from beibei",projects.get(0).getDescription());
        Assert.assertEquals("light for boogie from beibei",projects.get(1).getDescription());
    }
    @Test
    public void testUpdateProject(){
        ProjectPO project=projectDao.queryProjectById("2018-03-20_11-52-45");
        project.setProjectName("mail to boogie");
        project.setDescription("a mail to comfort boogie from beibei");
        projectDao.updateProject(project);
    }
    @Test
    public void testDeleteProject(){
        projectDao.deleteProject("2018-03-20_11-53-16");
    }


}
