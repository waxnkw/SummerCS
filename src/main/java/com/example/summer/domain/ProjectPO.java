package com.example.summer.domain;

//import com.example.summer.utility.enums.ProjectTypeEnum;
import com.example.summer.utility.enums.ProjectTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/*
 * @program: summerCS_Phase_I
 * @description: 项目对象，包含图片集、名称、描述、要求
 * @author: 161250140 wny
 * @create: 2018/3/15
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProjectPO implements PO{

    private static final long serialVersionUID = -6218842993231654275L;
    private String projectId;

    private String projectName;

    private String description;

    private String requirements;

    private UserInfoPO launcher;

    private ArrayList<UserInfoPO> markers;

    private boolean canBeJoined;

    private int totalNumOfImgs;

    private ProjectTypeEnum projectType;

    private int upUserLimit;
    //private DateTime createDate;

    @Override
    public String getID(){
        return projectId;
    }

}
