package com.example.summer.daoImpl;

import com.example.summer.configInfo.DataAbsolutePath;
import com.example.summer.dao.ProjectRankDao;
import com.example.summer.domain.PO;
import com.example.summer.domain.ProjectRankPO;
import com.example.summer.utility.dataUtility.ReadAndWritePOsUtility;

import java.util.ArrayList;

public class ProjectRankDaoImpl implements ProjectRankDao{

    private static final String LAST_FILE_PATH= DataAbsolutePath.DATA_PATH+"ProjectRank";

    @Override
    public boolean save(ProjectRankPO projectRankPO) {
        ArrayList<PO> pos= ReadAndWritePOsUtility.readPOs(LAST_FILE_PATH);
        ArrayList<ProjectRankPO> projectRankPOs=new ArrayList<ProjectRankPO>();
        for(int i=0;i<=pos.size()-1;i++){
            PO po=pos.get(i);
            if(po instanceof ProjectRankPO){
                ProjectRankPO projectRankPo=(ProjectRankPO)po;
                projectRankPOs.add(projectRankPo);
                //read all records first
            }else{
                return false;
                //this means the file path is false so the content of the file is not of class projectProjectPO
                //it will not happen as normal
            }
        }
        projectRankPOs.add(projectRankPO);
        //then add the target record--projectPO
        ReadAndWritePOsUtility.writePOs(projectRankPOs,LAST_FILE_PATH);
        return true;
    }

    @Override
    public boolean update(ProjectRankPO projectRankPO) {
        ArrayList<PO> pos= ReadAndWritePOsUtility.readPOs(LAST_FILE_PATH);
        ArrayList<ProjectRankPO> projectRankPOs=new ArrayList<ProjectRankPO>();
        boolean exist=false;
        for(int i=0;i<=pos.size()-1;i++) {
            PO po = pos.get(i);
            if (po instanceof ProjectRankPO) {
                ProjectRankPO projectRankPo = (ProjectRankPO) po;
                String id = projectRankPo.getID();
                if (id.equals(projectRankPO.getID())) {
                    exist = true;
                    projectRankPOs.add(projectRankPO);
                } else {
                    projectRankPOs.add(projectRankPo);
                }
                //read all records first,and select the target object and replace it
            } else {
                return false;
                //this means the file path is false so the content of the file is not of class projectPO
                //it will not happen as normal
            }
        }

        if(exist){
            ReadAndWritePOsUtility.writePOs(projectRankPOs,LAST_FILE_PATH);
            return true;
        }
        return false;
    }

    @Override
    public ProjectRankPO queryProjectRankByProjectId(String projectId) {
        ArrayList<PO> pos=ReadAndWritePOsUtility.readPOs(LAST_FILE_PATH);
        ProjectRankPO projectRankPO=null;
        for (int i = 0; i <= pos.size() - 1; i++) {
            PO po=pos.get(i);
            String id=po.getID();
            if(id.equals(projectId)){
                if(po instanceof ProjectRankPO){
                    projectRankPO=(ProjectRankPO)po;
                    break;
                }else{
                    return null;
                    //this means the file path is false so the content of the file is not of class projectPO
                    //it will not happen as normal
                }
            }
        }
        return projectRankPO;
    }
}
