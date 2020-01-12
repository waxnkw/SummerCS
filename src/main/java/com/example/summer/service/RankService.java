package com.example.summer.service;

import com.example.summer.model.WorkerRankInfoModel;

import java.util.ArrayList;

public interface RankService {

    /**
     * @param projectId 项目ID
     * @return 是否创建成功
     * */
    public boolean createProjectRank(String projectId);

    /**
     * 排名榜中新增一个用户
     * @param username 用户名
     * @return  是否增加成功
     * */
    public boolean addRanker(String projectId ,String username);

    /**
     * 查询用户在一个项目内排名
     * @param projectId 项目Id
     * @param username 用户名
     * @return 用户的项目内排名
     * */
    public int queryRankInProject( String projectId,  String username);

    /**
     * 查询用户在一个项目内积分
     * @param projectId 项目Id
     * @param username 用户名
     * @return 用户的项目内排名
     * */
    public int queryCreditInProject(String projectId,  String username);

    /**
     * 查询一个项目内的所有排名
     * @param begin 查询起始排名
     * @param num 查询人数
     * @param projectId 项目Id
     * @return 用户排名
     * */
    public ArrayList<WorkerRankInfoModel> queryRankListInProject(String projectId, int begin, int num);
}
