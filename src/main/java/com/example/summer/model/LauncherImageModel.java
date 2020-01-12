package com.example.summer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;

/**
 *项目发起者看的图片信息
 * 仅仅包括图片的fileName
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LauncherImageModel {
    private String fileName;

    /**
     * 批量返回一个项目内的图片
     * @author Mr.Wang
     * @param projectId projectId
     * @param begin 开始位置
     * @param num 图片数量
     * @return java.util.ArrayList<com.example.summer.model.LauncherImageModel>
     */
    public static ArrayList<LauncherImageModel> listLauncherImagesByProjectId(String projectId, int begin, int num){
        ArrayList<LauncherImageModel> launcherImageModels = new ArrayList<>();
        ArrayList<ImageModel> imageModels = ImageModel.listImageModelByProjectId(projectId, begin, num);
        if(imageModels!=null){
            imageModels.forEach(e->launcherImageModels.add(LauncherImageModel.builder()
                    .fileName(e.getFileName())
                    .build()));
            return launcherImageModels;
        }
        return null;
    }
}
