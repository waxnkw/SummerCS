package com.example.summer.service;

import com.example.summer.model.ImageModel;
import com.example.summer.model.ProjectInfoModel;
import com.example.summer.model.SingleUserImageModel;
import com.example.summer.model.WorkerProjectInfoModel;

import java.util.ArrayList;

public interface WorkerService {
    /**
     *  1.检测是否可加入（已加入则不可以加入）:
     *      1得到项目      2查重
     *  2.分配项目内的图片,项目内图片添加该user标记：
     *      1.得到项目图片信息合集 2.选择前claimNumber张可以选择(已加入人数未达uplimits图片)标记该人 3.保存图片信息
     *  3.项目中加入该人：
     *      1.项目中标记该marker 2.保存项目信息 2.返回项目Id
     * @param projectId  项目Id
     * @param username  用户名
     * @param claimNumber 认领的图片数量
     * @return 项目Id
     * */
    public String joinProject(String projectId, String username, Integer claimNumber);

    /**
     * 1.得到当前图片
     * 2.见上面2
     * 3.返回图片列表
     * @param projectId  项目Id
     * @param username  用户名
     * @param claimNumber 认领的图片数量
     * @return 需要标记的图片
     * */
    public boolean askMoreImages(String projectId, String username, Integer claimNumber);

    /**
     * 保存一张图片信息
     * @param image 需要保存的图片信息
     * @return 是否保存成功
     * */
    public boolean saveImage(SingleUserImageModel image);

    /**
     * 通过用户名查询一个用户的
     * @param username 用户的Id
     * @param projectId 项目Id
     * @return 该用户认领的该项目下的所有图片
     * */
    public ArrayList<SingleUserImageModel> queryImagesByUserIdAndProjectId(String username, String projectId, int begin, int num);

    /**
     * @param imageId 图片的Id
     * @param username 用户名称
     * @return 该Id图片的信息
     * */
    public SingleUserImageModel queryImageByImageIdAndUsername(String imageId, String username);

    public WorkerProjectInfoModel queryWorkerProjectInfoByUsernameAndProjectId(String username, String projectId);

    /**
     * 查看可以加入的项目
     * @return 可以加入的项目简略信息清单
     * */
    public  ArrayList<ProjectInfoModel> queryCanbeJoinedProjectInfos();

    /**
     *结算当前标记批次的图片
     * @param projectId 项目的Id
     * @param username 用户名
     * @return  本批次的总得分
     * */
    public int closeBatchImgs(String projectId, String username);

    /**
     * 根据项目Id和用户名得到一个用户加入的项目清单
     *@param username 用户名
     * @return 项目的简略信息清单
     * */
    public ArrayList<ProjectInfoModel> listJoinedProjectInfosByProjectIdAndUserName( String username);
}
