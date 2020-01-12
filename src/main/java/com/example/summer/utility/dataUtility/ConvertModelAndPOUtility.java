package com.example.summer.utility.dataUtility;

import com.example.summer.domain.EmailPO;
import com.example.summer.domain.ProjectPO;
import com.example.summer.domain.UserPO;
import com.example.summer.model.EmailModel;
import com.example.summer.model.LauncherProjectInfoModel;
import com.example.summer.model.ProjectModel;
import com.example.summer.model.UserModel;

/*
 * @program: summerCS_Phase_I
 * @description: Model和PO之间的相互转化
 * @author: Mr.Wang
 * @create: 2018/3/21
 */
public class ConvertModelAndPOUtility {


    /*
     * @Description: EmailPO->EmailModel
     * @Param: EmailPO
     * @return: com.example.summer.model.EmailModel
     * @Author: Mr.Wang
     * @Date: 2018/3/21
     */
    public EmailModel convertEmailPOToModel(EmailPO po){
        if(po==null){
            return null;
        }else{
            EmailModel model=new EmailModel();
            model.setAddress(po.getAddress());
            return model;
        }
    }

    /*
     * @Description: UserPO->UserModel
     * @Param: [UserPO]
     * @return: com.example.summer.model.UserModel
     * @Author: Mr.Wang
     * @Date: 2018/3/21
     */
    public UserModel convertUserPOToModel(UserPO po){
        if(po==null){
            return null;
        }else{
            UserModel userModel=new UserModel();
            userModel.setId(po.getID());
            userModel.setUsername(po.getUsername());
            userModel.setPassword(po.getPassword());
            userModel.setEmail(convertEmailPOToModel(po.getEmail()));
            return userModel;
        }
    }

    /*
     * @Description: ProjectPO->ProjectModel
     * @Param: ProjectPO
     * @return: com.example.summer.model.ProjectModel
     * @Author: Mr.Wang
     * @Date: 2018/3/21
     */
    public ProjectModel convertProjectPOToModel(ProjectPO po){
        if(po==null){
            return null;
        }else{
            ProjectModel projectModel=new ProjectModel();
            projectModel.setProjectId(po.getProjectId());
            projectModel.setProjectName(po.getProjectName());
            projectModel.setDescription(po.getDescription());
            //projectModel.setJoinerNames();
            //projectModel.setLauncherName(po.getLauncherName());
            return projectModel;
        }
    }

    /*
     * @Description: ProjectPO->LauncherProjectInfoModel
     * @Param: [ProjectPO]
     * @return: com.example.summer.model.LauncherProjectInfoModel
     * @Author: Mr.Wang
     * @Date: 2018/3/21
     */
    public LauncherProjectInfoModel convertProjectPOToInfoModel(ProjectPO po){
        if(po==null){
            return null;
        }else{
            LauncherProjectInfoModel infoModel=new LauncherProjectInfoModel();
            infoModel.setProjectId(po.getProjectId());
            infoModel.setProjectName(po.getProjectName());
            return infoModel;
        }
    }

    /*
     * @Description: ProjectModel->ProjectPO
     * @Param: [ProjectModel]
     * @return: com.example.summer.domain.ProjectPO
     * @Author: Mr.Wang
     * @Date: 2018/3/23
     */
    public ProjectPO convertProjectModelToPO(ProjectModel model){
        if(model==null){
            return null;
        }else{
            ProjectPO projectPO=new ProjectPO();
            projectPO.setDescription(model.getDescription());
            projectPO.setProjectName(model.getProjectName());
            projectPO.setProjectId(model.getProjectId());
            //projectPO.setRequirements();
            //projectPO.setCanBeJoined();
            //projectPO.setLauncherName(model.getLauncherName());
            //projectPO.setMarkers();
            return projectPO;
        }
    }
}
