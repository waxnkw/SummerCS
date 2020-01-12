package com.example.summer.web;

import com.example.summer.model.ProjectStatisticModel;
import com.example.summer.service.AdminService;
import com.example.summer.service.impl.AdminServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private AdminService adminService = new AdminServiceImpl();

    /**
     * 得到当前的用户数目
     * */
    @RequestMapping("/admin/userNum")
    public int queryUserNum(){
        return adminService.queryUserNum();
    }

    /**
     * 得到当前的项目统计信息
     * @return 项目的统计信息
     * */
    @RequestMapping("/admin/projectStatistic")
    public ProjectStatisticModel queryProjectStatisticData(){
        return adminService.queryProjectStatisticData();
    }
}
