package com.example.summer.dao;
import com.example.summer.domain.ProjectRankPO;
public interface ProjectRankDao {
    public  boolean save(ProjectRankPO projectRank);
    public boolean update(ProjectRankPO projectRank);
    public ProjectRankPO queryProjectRankByProjectId(String projectId);
}
