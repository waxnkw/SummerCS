package com.example.summer.domain;

import lombok.*;

import java.util.ArrayList;

/*
 * @program: summerCS_Phase_I
 * @description: 图片
 * @author: 161250140 wny
 * @create: 2018/3/17
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImagePO implements PO {

    private static final long serialVersionUID = 7701738037831394931L;
    //id
    private String imageId;

    //用户最大上限
    private int upUserLimit;
    //标记者们
    private ArrayList<MarkerInfoPO> markers;
    //图片标记们
    private ArrayList<MarkPO> marks;

    //对应项目id
    private String projectId;
    //图片名称
    private String fileName;
    //是train还是test
    private String split;

    @Override
    public String getID(){
        return imageId;
    }
}
