package com.example.summer.debug;

import com.example.summer.configInfo.DataAbsolutePath;
import com.example.summer.service.impl.WorkerServiceImpl;

public class JoinProject {
    public static void  join(String username, String projectId, int claimNumber){
        DataAbsolutePath.DEBUG();
        WorkerServiceImpl workerService  = new WorkerServiceImpl();
        workerService.joinProject(projectId,username, claimNumber);
    }

    public static void main(String [] args){
        JoinProject.join("zhangao","2018-04-28_18-58-47-869", 10);
    }
}
