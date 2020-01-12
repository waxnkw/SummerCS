package com.example.summer.model;

import com.example.summer.dao.ProjectDao;
import com.example.summer.daoImpl.ProjectDaoImpl;
import com.example.summer.domain.ProjectPO;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LauncherProjectInfoModel implements Model{
    @JsonIgnore
    private String projectId;
    private String username;
    @JsonIgnore
    private ArrayList<SingleUserImageModel> singleUserImageModels;
    //项目名称
    private String projectName;

    //项目描述
    private String description;
    //项目图片总数
    private int totalNumOfImgs;

    //所有用户已完成总的标注图片数
    private int doneNumOfImgs;

    //发起者是否在参与者当中
    private boolean userIn;

    /**
     * 根据项目ID查询某一个项目的信息
     * @param launcherId projectId
     * @return com.example.summer.model.LauncherProjectInfoModel
     * @author Mr.Wang
     */
    public static LauncherProjectInfoModel generate(String launcherId, String username){
        ProjectDao projectDao = new ProjectDaoImpl();
        ProjectPO po = projectDao.queryProjectById(launcherId);

        if(po==null){ return null; }

        ProjectModel model = new ProjectModel();
        ArrayList<UserInfoModel> userInfoModels = new ArrayList<>();
        if (po.getMarkers() == null){po.setMarkers(new ArrayList<>());}
        po.getMarkers().stream().forEach(userInfoPO
                -> userInfoModels
                .add(DozerMapSingleton.getInstance().map(userInfoPO, UserInfoModel.class)));
        model.setMarkers(userInfoModels);

        return LauncherProjectInfoModel.builder()
                .projectId(launcherId)
                .username(po.getLauncher().getUsername())
                .projectName(po.getProjectName())
                .description(po.getDescription())
                .totalNumOfImgs(calcTotalNumOfImages(launcherId))
                .doneNumOfImgs(calcDoneNumOfImages(launcherId))
                .userIn(model.isUserAlreadyIn(username))
                .build();
    }

    /**
     * @author Mr.Wang
     * @param projectModel projectModel
     * @return com.example.summer.model.LauncherProjectInfoModel
     */
    public static LauncherProjectInfoModel convert(ProjectModel projectModel){
        if(projectModel!=null){
            return LauncherProjectInfoModel.builder()
                    .projectId(projectModel.getProjectId())
                    .username(projectModel.getLauncher().getUsername())
                    .projectName(projectModel.getProjectName())
                    .description(projectModel.getDescription())
                    .totalNumOfImgs(calcTotalNumOfImages(projectModel.getProjectId()))
                    .doneNumOfImgs(calcDoneNumOfImages(projectModel.getProjectId()))
                    .build();
        }
         return null;
    }

    public static int calcTotalNumOfImages(String launcherId){
        ArrayList<ImageModel> imageList = ImageModel.listImagesByProjectId(launcherId);
        if(imageList==null){return 0;}
        return ImageModel.listImagesByProjectId(launcherId).size();
    }

    /**
     * 计算已完成标记的图片的数量
     * @author Mr.Wang
     * @param launcherId projectId
     * @return int
     */
    private static int calcDoneNumOfImages(String launcherId){
        int size = 0;
        if(ImageModel.listImagesByProjectId(launcherId)!=null){
            ArrayList<ImageModel> images = ImageModel.listImagesByProjectId(launcherId);
            for(ImageModel imageModel:images){
                if(imageModel.getMarks()!=null){
                    size++;
                }
            }
        }
        return size;
    }
}
