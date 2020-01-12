package com.example.summer.model;

import lombok.*;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageListModel {
    private ArrayList<ImageModel> imageList;


    /***************************************************************************
     *                                                                         *
     * static methods                                                   *
     *                                                                         *
     *************************************************************************/
    /**
     * 通过项目ID和用户姓名获得这个项目中该用户可以标记的图片数量
     * @author bb
     * @param projectId 项目Id
     * @param username 用户姓名
     * @return int
     */
    public static int calcCanBeAssignedImagesNum(String projectId, String username){
        ArrayList<ImageModel> imageModels=ImageListModel.generate(projectId).getImageList();
        int count=0;
        if(imageModels==null|| imageModels.size()==0){
            return 0;
        }
        for(int i=0;i<=imageModels.size()-1;i++){
            ImageModel imageModel=imageModels.get(i);
            if(imageModel.canAddNewMarker()&&(!imageModel.markerAlreadyIn(username))){
                count++;
            }
        }
        return count;
    }

    /**
     * 通过项目ID和用户姓名以及标记数量将一定数量的图片增加该标记者
     * @author bb
     * @param projectId 项目Id
     * @param username 用户姓名
     * @return int
     */
    public static boolean allocateNewMarker(String projectId, String username, int claimNumber){
        MarkerInfoModel markerInfoModel=new MarkerInfoModel();
        markerInfoModel.setUsername(username);
        markerInfoModel.setCurrentImg(true);//设定为true
        ArrayList<ImageModel> imageModels=ImageListModel.generate(projectId).getImageList();
        int count=0;
        if(imageModels==null|| imageModels.size()==0){
            return false;
        }
        for(int i=0;i<=imageModels.size()-1;i++){
            ImageModel imageModel=imageModels.get(i);
            if(imageModel.canAddNewMarker()&&(imageModel.markerAlreadyIn(username)==false)){
                //the image can add the marker
                ArrayList<MarkerInfoModel> markersOfTheImage=imageModel.getMarkers();
                if (markersOfTheImage==null){markersOfTheImage=new ArrayList<>();}
                markersOfTheImage.add(markerInfoModel);
                imageModel.setMarkers(markersOfTheImage);
                imageModel.update();
                //add the marker in the image and update the image
                count++;
                if(count==claimNumber){ return true; }
            }
        }
        return false;
    }
    /**
     * 通过项目ID获得所有图片
     * @author bb
     * @param projectId 项目Id
     * @return ImageListModel
     */
    public static ImageListModel generate(String projectId){
        ImageListModel model=new ImageListModel();
        model.imageList=ImageModel.listImagesByProjectId(projectId);
        return model;
    }

    public static ImageListModel generate(String proejectId, String username){
        ImageListModel imageListModel = new ImageListModel();
        imageListModel.setImageList(ImageModel.listCurrentImagesByUserIdAndPorjectId(proejectId, username));
        return imageListModel;
    }

    /***************************************************************************
     *                                                                         *
     * non static methods                                                   *
     *                                                                         *
     *************************************************************************/
    /**
     * tbt
     * 关闭该用户current的所有图片,置为current false
     * @param username 用户名
     * @return 是否关闭成功
     * */
    public boolean closeImages(String username){
        for(ImageModel image: imageList){
            image.closeByMarkerName(username);//内部更新update
        }
        return true;
    }

    /**
     * tbt
     * 计算该用户当前批次内所有图片标记的得分
     * @param eachCredit 每张图片的分数
     * @param username 用户名
     * @return 用户的总得分
     * */
    public int calcTotalCreditOfImgs(int eachCredit, String username){
        int totalCredit = 0;
        for (ImageModel image: imageList){
            if(image.markedByMarker(username)){totalCredit+=eachCredit;}
        }
        return totalCredit;
    }

}
