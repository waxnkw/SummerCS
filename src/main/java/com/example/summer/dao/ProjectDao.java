package com.example.summer.dao;

import com.example.summer.domain.ProjectPO;

import java.util.ArrayList;

public interface ProjectDao {
    /**
     * 查询当下所有项目
     * @return 当下所有项目
     * */
    public ArrayList<ProjectPO> listAllProjects();

    /**
     * 查询当下所有未关闭项目(canBeJoined 为true)
     * @return 当下所有项目
     * */
    public ArrayList<ProjectPO> listAllAccessibleProjects();

    /**
     * 根据项目Id查询项目
     * @return 该Id的项目
     * */
    public ProjectPO queryProjectById(String projectId);

    /**
     * 根据用户名查询其发起的项目
     * @param userName  用户名
     * @return 新标记的图片
     * */
    public ArrayList<ProjectPO> listLaunchedProjectsByUserName(String userName);

    /**
     * 根据用户名查询其参加的项目
     * @param userName  用户名
     * @return 新标记的图片
     * */
    public ArrayList<ProjectPO> listJoinedProjectsByUserName(String userName);

    /**
     * 储存当前项目
     * 需要张贝贝同学自己生成：projectId
     * @return  项目Id（失败返回null）
     * */
    public String saveProject(ProjectPO po);

    /**
     * 更新已有的项目
     * 注意:不要改现有的projectId
     * 建议:只有存在该项目才能存
     * */
    public boolean updateProject(ProjectPO po);

    /**
     * 删除项目
     * */
    public boolean deleteProject(String projectId);
}
