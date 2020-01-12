package com.example.summer.service.impl;

import com.example.summer.model.ProjectRankModel;
import com.example.summer.model.WorkerRankInfoModel;
import com.example.summer.service.RankService;

import java.util.ArrayList;

public class RankServiceImpl implements RankService {
    @Override
    public boolean createProjectRank(String projectId) {
        return new ProjectRankModel().save(projectId);
    }

    @Override
    public boolean addRanker(String projectId,String username) {

        return ProjectRankModel.generate(projectId).addNewMarker(username);
    }

    @Override
    public int queryRankInProject(String projectId, String username) {
        ProjectRankModel projectRankModel  = ProjectRankModel.generate(projectId);
        return projectRankModel.getRankByUsername(username);
    }

    @Override
    public int queryCreditInProject(String projectId, String username) {
        ProjectRankModel projectRankModel  = ProjectRankModel.generate(projectId);
        return projectRankModel.getCreditByUsername(username);
    }

    @Override
    public ArrayList<WorkerRankInfoModel> queryRankListInProject(String projectId, int begin, int num) {
        ProjectRankModel projectRankModel  = ProjectRankModel.generate(projectId);
        return projectRankModel.getRankList(begin, num);
    }
}
