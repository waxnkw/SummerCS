package com.example.summer.service;

import com.example.summer.model.ProjectStatisticModel;

public interface AdminService {

    /**
     * 得到当前的用户数目
     * */
    int queryUserNum();

    /**
     * 得到当前的项目统计信息
     * @return 项目的统计信息
     * */
    ProjectStatisticModel queryProjectStatisticData();
}
