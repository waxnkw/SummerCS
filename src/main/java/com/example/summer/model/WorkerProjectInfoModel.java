package com.example.summer.model;

import com.example.summer.daoImpl.ProjectDaoImpl;
import com.example.summer.domain.ProjectPO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.summer.dao.ProjectDao;

import java.util.ArrayList;

/**
 * 返回给前端的该用户的项目信息
 * @author zhangao
 * @date 2018/4/11
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerProjectInfoModel {
    @JsonIgnore
    private String projectId;
    @JsonIgnore
    private String username;
    @JsonIgnore
    private static ArrayList<SingleUserImageModel> singleUserImageModels;
    //项目名称
    private String projectName;

    //项目描述
    private String description;
    //项目图片总数
    private int totalNumOfImgs;

    //该用户已完成标注图片数
    private int doneNumOfImgs;

    //是否在标注当中
    private boolean canAskMore;

    /**
     *
     * */


    public static WorkerProjectInfoModel generate(String projectId,String userName){
        ProjectDao projectDao=new ProjectDaoImpl();
        ProjectPO project=projectDao.queryProjectById(projectId);
        WorkerProjectInfoModel workerProjectInfoModel=new WorkerProjectInfoModel();
        workerProjectInfoModel.setProjectId(project.getProjectId());
        workerProjectInfoModel.setUsername(userName);
        workerProjectInfoModel.setProjectName(project.getProjectName());
        workerProjectInfoModel.setDescription(project.getDescription());
        workerProjectInfoModel.setTotalNumOfImgs(project.getTotalNumOfImgs());
        workerProjectInfoModel.setDoneNumOfImgs(calDoneNumOfImgs(projectId,userName));
        workerProjectInfoModel.setCanAskMore(calcCanAskMore(project, userName));
        return workerProjectInfoModel;
    }
    private static int calDoneNumOfImgs(String projectId,String username) {
        int size = 0;
        singleUserImageModels= SingleUserImageModel.querySingleUserImagesByUserIdAndProjectId(projectId, username);
        if (singleUserImageModels == null) {
            return size;
        }
        for (int i = 0; i <= singleUserImageModels.size() - 1; i++) {
            SingleUserImageModel singleUserImageModel = singleUserImageModels.get(i);
            if (singleUserImageModel.getMark() != null) {
                size++;
            }
        }
        return size;
    }

    public static boolean calcCanAskMore(ProjectPO po, String username){
        ArrayList<ImageModel> imageModels
                = ImageModel.listCurrentImagesByUserIdAndPorjectId(po.getProjectId(), username);
        if (imageModels==null || imageModels.size()==0){
            return true;
        }
        return false;
    }

}
