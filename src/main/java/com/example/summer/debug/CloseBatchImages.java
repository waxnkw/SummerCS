package com.example.summer.debug;

import com.example.summer.configInfo.DataAbsolutePath;
import com.example.summer.service.impl.WorkerServiceImpl;

public class CloseBatchImages {
    private static WorkerServiceImpl workerService = new WorkerServiceImpl();
    public static  void closeBatchImages(String projectId, String username){
        DataAbsolutePath.DEBUG();
        workerService.closeBatchImgs(projectId, username);
    }


    public static void main(String [] args){
        CloseBatchImages.closeBatchImages("2018-04-28_18-58-47-869","zhangao");
    }
}
