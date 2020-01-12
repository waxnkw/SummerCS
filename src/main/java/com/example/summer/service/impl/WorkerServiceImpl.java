package com.example.summer.service.impl;

import com.example.summer.model.*;
import com.example.summer.service.WorkerService;
import com.example.summer.utility.json.JsonStringUitility;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class WorkerServiceImpl implements WorkerService {

    @Override
    public String joinProject(String projectId, String username, Integer claimNumber) {
        //before use this func, use ProjectService.getCanBeAssignedImagesNum(String projectId,String username)to judge if there are enough images for the user to mark
        ProjectModel model=ProjectModel.queryProjectById(projectId);
        if(model.canBeJoinedByTheUser(username)) {
            model.addNewMarker(username);
            askMoreImages(projectId, username, claimNumber);
            return projectId;
        }
        return null;
    }
    @Override
    public boolean askMoreImages(String projectId, String username, Integer claimNumber) {
        //before use this func, use ProjectService.getCanBeAssignedImagesNum(String projectId,String username)to judge if there are enough images for the user to mark
        //System.out.println(projectId+" "+username+" "+claimNumber);
        return ImageListModel.allocateNewMarker(projectId,username,claimNumber);
    }

    @Override
    public boolean saveImage(SingleUserImageModel image) {
        return image.update();
    }

    @Override
    public ArrayList<SingleUserImageModel> queryImagesByUserIdAndProjectId(String username, String projectId, int begin, int num) {
        return SingleUserImageModel.querySingleUserImagesByUserIdAndProjectId(projectId, username, begin, num);
    }

    @Override
    public SingleUserImageModel queryImageByImageIdAndUsername(String imageId, String username) {
        return SingleUserImageModel.querySingleUserImageByUserIdAndImageId(username,imageId);
    }

    @Override
    public WorkerProjectInfoModel queryWorkerProjectInfoByUsernameAndProjectId(String username, String projectId) {
        return WorkerProjectInfoModel.generate(projectId,username);
    }

    @Override
    public ArrayList<ProjectInfoModel> queryCanbeJoinedProjectInfos() {
        return ProjectInfoModel.listCanbeJoinedProjectInfos();
    }

    @Override
    public int closeBatchImgs(String projectId, String username) {
        ProjectModel project = ProjectModel.queryProjectById(projectId);
        System.out.println((SimpleProjectModel)project);
        return project.closeCurImages(username);
    }

    @Override
    public ArrayList<ProjectInfoModel> listJoinedProjectInfosByProjectIdAndUserName(String username) {
        ArrayList<ProjectInfoModel> projects = ProjectInfoModel.listNowJoinedProjectInfos(username);
//        System.out.println("WorkerServiceImpl: " + JsonStringUitility.toString(projects));
        if(projects!=null){
            return projects;
        }
        return null;
    }
}
