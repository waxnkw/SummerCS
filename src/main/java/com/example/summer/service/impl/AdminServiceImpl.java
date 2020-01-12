package com.example.summer.service.impl;

import com.example.summer.model.ProjectStatisticModel;
import com.example.summer.model.UserModel;
import com.example.summer.service.AdminService;

public class AdminServiceImpl implements AdminService{
    @Override
    public int queryUserNum() {
        return UserModel.listAllUsers().size();
    }

    @Override
    public ProjectStatisticModel queryProjectStatisticData() {
        return ProjectStatisticModel.generate();
    }
}
