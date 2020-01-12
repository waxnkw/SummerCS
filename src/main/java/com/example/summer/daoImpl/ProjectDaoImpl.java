package com.example.summer.daoImpl;

import com.example.summer.configInfo.DataAbsolutePath;
import com.example.summer.dao.ProjectDao;
import com.example.summer.domain.MarkerInfoPO;
import com.example.summer.domain.PO;
import com.example.summer.domain.ProjectPO;
import com.example.summer.domain.UserInfoPO;
import com.example.summer.utility.dataUtility.InitTimeUtility;
import com.example.summer.utility.dataUtility.ReadAndWritePOsUtility;
import com.example.summer.utility.json.JsonStringUitility;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Repository
public class ProjectDaoImpl implements ProjectDao{
    private static final String LAST_FILE_PATH= DataAbsolutePath.DATA_PATH+"Project/";
    @Override
    public ArrayList<ProjectPO> listAllProjects() {
        ArrayList<PO> pos=ReadAndWritePOsUtility.readPOs(LAST_FILE_PATH);
        ArrayList<ProjectPO> projectPOs=new ArrayList<ProjectPO>();
        if(pos.size()==0){
            return null;
        }
        for(int i=0;i<=pos.size()-1;i++){
                PO po=pos.get(i);
                if(po instanceof ProjectPO){
                    ProjectPO projectPo=(ProjectPO)po;
                    projectPOs.add(projectPo);
                    //read all records first
                }else{
                    return null;
                    //this means the file path is false so the content of the file is not of class projectPO
                    //it will not happen as normal
                }
            }
        return projectPOs;
        }

    @Override
    public ArrayList<ProjectPO> listAllAccessibleProjects() {
        ArrayList<PO> pos=ReadAndWritePOsUtility.readPOs(LAST_FILE_PATH);
        ArrayList<ProjectPO> projectPOs=new ArrayList<ProjectPO>();
        if(pos.size()==0){
            return null;
        }
        for(int i=0;i<=pos.size()-1;i++){
                PO po=pos.get(i);
                if(po instanceof ProjectPO){
                    ProjectPO projectPo=(ProjectPO)po;
                    if(projectPo.isCanBeJoined()) {
                        projectPOs.add(projectPo);
                    }
                    //read all records first
                }else{
                    return null;
                    //this means the file path is false so the content of the file is not of class projectPO
                    //it will not happen as normal
                }
            }
            if(projectPOs.size()==0){
                return null;
            }else {
                return projectPOs;
            }

    }

    @Override
    public ProjectPO queryProjectById(String projectId) {
        ArrayList<PO> pos=ReadAndWritePOsUtility.readPOs(LAST_FILE_PATH);
        ProjectPO projectPO=null;
        for (int i = 0; i <= pos.size() - 1; i++) {
                PO po=pos.get(i);
                String id=po.getID();
                if(id.equals(projectId)){
                    if(po instanceof ProjectPO){
                        projectPO=(ProjectPO)po;
                        break;
                        }else{
                        return null;
                        //this means the file path is false so the content of the file is not of class projectPO
                        //it will not happen as normal
                    }
                }
            }
        return projectPO;
    }

    @Override
    public ArrayList<ProjectPO> listLaunchedProjectsByUserName(String userName) {
        ArrayList<PO> pos=ReadAndWritePOsUtility.readPOs(LAST_FILE_PATH);
        ArrayList<ProjectPO> projectPOsOfTheLauncher=new ArrayList<ProjectPO>();
        boolean exist=false;
            for(int i=0;i<=pos.size()-1;i++){
                PO po=pos.get(i);
                if(po instanceof ProjectPO){
                   ProjectPO  projectPO=(ProjectPO)po;
                   String launcherName=projectPO.getLauncher().getUsername();
                   if(launcherName.equals(userName)) {
                       exist=true;
                       projectPOsOfTheLauncher.add(projectPO);
                   }
                }else{
                        return null;
                        //this means the file path is false so the content of the file is not of class projectPO
                        //it will not happen as normal
                    }
                }
        if(exist){
            return projectPOsOfTheLauncher;
        }
            return null;
            }

    @Override
    public ArrayList<ProjectPO> listJoinedProjectsByUserName(String userName) {
        ArrayList<PO> pos = ReadAndWritePOsUtility.readPOs(LAST_FILE_PATH);
        ArrayList<ProjectPO> projectPOsOfTheUser = new ArrayList<ProjectPO>();
        boolean exist = false;
        for (int i = 0; i <= pos.size() - 1; i++) {
            PO po = pos.get(i);
            if (po instanceof ProjectPO) {
                ProjectPO projectPO = (ProjectPO) po;
                ArrayList<UserInfoPO> markerInfoPOs = projectPO.getMarkers();
                if ((markerInfoPOs != null) && (markerInfoPOs.size() != 0)) {
                    for (int j = 0; j <= markerInfoPOs.size() - 1; j++) {
                        UserInfoPO markerInfoPO = markerInfoPOs.get(j);
                        String markerName = markerInfoPO.getUsername();
                        if (markerName.equals(userName)) {
                            projectPOsOfTheUser.add(projectPO);
                            exist = true;
                            break;
                        }
                    }
                }
            } else {
                return null;
                //this means the file path is false so the content of the file is not of class ProjectPO
                //it will not happen as normal
            }
        }
        if(exist){
            return projectPOsOfTheUser;
        }
            return null;

    }


    private String initProjectID(){
        String id= InitTimeUtility.getCurrentTime();
        //use time to be id in the condithon that there will not be more than one project to save at the same time!!!
        return id;
    }



    @Override
    public String saveProject(ProjectPO projectPO) {
        //I need to set ID by myself here
        String projectID=initProjectID();
        projectPO.setProjectId(projectID);
        ArrayList<PO> pos= ReadAndWritePOsUtility.readPOs(LAST_FILE_PATH);
        ArrayList<ProjectPO> projectPOs=new ArrayList<ProjectPO>();
        for(int i=0;i<=pos.size()-1;i++){
                PO po=pos.get(i);
                if(po instanceof ProjectPO){
                    ProjectPO projectPo=(ProjectPO)po;
                    projectPOs.add(projectPo);
                    //read all records first
                }else{
                    return null;
                    //this means the file path is false so the content of the file is not of class projectPO
                    //it will not happen as normal
                }
            }
            projectPOs.add(projectPO);
            //then add the target record--projectPO
        ReadAndWritePOsUtility.writePOs(projectPOs,LAST_FILE_PATH);
        System.out.println(JsonStringUitility.toString(projectPO));
        return projectPO.getID();
    }

    @Override
    public boolean updateProject(ProjectPO projectPO) {
        ArrayList<PO> pos= ReadAndWritePOsUtility.readPOs(LAST_FILE_PATH);
        ArrayList<ProjectPO> projectPOs=new ArrayList<ProjectPO>();
        boolean exist=false;
        for(int i=0;i<=pos.size()-1;i++) {
              PO po = pos.get(i);
              if (po instanceof ProjectPO) {
                  ProjectPO projectPo = (ProjectPO) po;
                  String id = projectPo.getID();
                  if (id.equals(projectPO.getID())) {
                      exist = true;
                      projectPOs.add(projectPO);
                  } else {
                      projectPOs.add(projectPo);
                  }
                  //read all records first,and select the target object and replace it
              } else {
                  return false;
                  //this means the file path is false so the content of the file is not of class projectPO
                  //it will not happen as normal
              }
          }

            if(exist){
                ReadAndWritePOsUtility.writePOs(projectPOs,LAST_FILE_PATH);
                return true;
            }
                return false;
        }

    @Override
    public boolean deleteProject(String projectId) {
        ArrayList<PO> pos=ReadAndWritePOsUtility.readPOs(LAST_FILE_PATH);
        boolean exist=false;
        for(int i=0;i<=pos.size()-1;i++){
            PO po=pos.get(i);
            if(po instanceof ProjectPO){
                ProjectPO projectPO=(ProjectPO)po;
                String id=projectPO.getID();
                if(id.equals(projectId)){
                    exist=true;
                    pos.remove(i);
                    break;
                }else{

                }
            }else{
                return false;
                //this means the file path is false so the content of the file is not of class projectPO
                //it will not happen as normal
            }
        }
        if(exist){
            ReadAndWritePOsUtility.writePOs(pos,LAST_FILE_PATH);
            return true;
        }
            return false;
            //this means there is no projectPO object of the projectID
    }
}
