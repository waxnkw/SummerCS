package com.example.summer.stub;

import com.example.summer.model.ImageModel;
import com.example.summer.model.ProjectInfoModel;
import com.example.summer.model.SingleUserImageModel;
import com.example.summer.model.WorkerProjectInfoModel;
import com.example.summer.service.WorkerService;

import java.util.ArrayList;


public class WorkerServiceStub implements WorkerService {
    @Override
    public String joinProject(String projectId, String username, Integer claimNumber) {
        return null;
    }

    @Override
    public boolean askMoreImages(String projectId, String username, Integer claimNumber) {
        return false;
    }

    @Override
    public boolean saveImage(SingleUserImageModel image) {
        return false;
    }

    @Override
    public ArrayList<SingleUserImageModel> queryImagesByUserIdAndProjectId(String username, String projectId, int begin, int num) {
        return null;
    }

    public boolean saveImage(ImageModel image) {
        return false;
    }


    public ArrayList<SingleUserImageModel> queryImagesByUserIdAndProjectId(String username, String projectId) {
        return null;
    }

    @Override
    public SingleUserImageModel queryImageByImageIdAndUsername(String imageId, String username) {
        return null;
    }

    @Override
    public WorkerProjectInfoModel queryWorkerProjectInfoByUsernameAndProjectId(String username, String projectId) {
        return null;
    }

    @Override
    public ArrayList<ProjectInfoModel> queryCanbeJoinedProjectInfos() {
        return null;
    }

    @Override
    public int closeBatchImgs(String projectId, String username) {
        return 0;
    }

    @Override
    public ArrayList<ProjectInfoModel> listJoinedProjectInfosByProjectIdAndUserName(String username) {
        return null;
    }
//    @Override
//    public String joinProject(String projectId, String username, Integer claimNumber) {
//        return null;
//    }
//
//    @Override
//    public ArrayList<ImageModel> askMoreImages(String projectId, String username, Integer claimNumber) {
//        return null;
//    }
//
//    @Override
//    public boolean saveImage(ImageModel image) {
//        return false;
//    }
//
//    @Override
//    public ArrayList<ImageModel> listImagesByUserIdAndProjectId(String username, String projectId) {
//
//        ArrayList<ImageModel> imageModels = new ArrayList<>();
//        ImagePO po1 = new ImagePOStub().imagePOStub();
//        ImagePO po2 = new ImagePOStub().imagePOStub();
//        po1.setFileName("2018-03-20_12-32-08--0.jpg");
//        po2.setFileName("2018-03-20_12-32-08--1.jpg");
//        imageModels.add(new ImageModel(po1));
//        imageModels.add(new ImageModel(po2));
//        return imageModels;
//    }

}
