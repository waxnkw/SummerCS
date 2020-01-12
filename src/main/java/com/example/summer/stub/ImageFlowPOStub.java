package com.example.summer.stub;

import com.example.summer.domain.ImageFlowPO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageFlowPOStub {
    public ImageFlowPO imageFlowPOStub(String path){
        File file = new File(path);
        System.out.println(file.exists());
        ImageFlowPO po = new ImageFlowPO();
        byte [] imageFlow = readImageFlow(path);
        po.setImageBytes(imageFlow);
        return po;
    }

    public byte[] readImageFlow(String path){
        byte [] imageFlow = new byte[1024*1024*3];

        try {
            File imageFile = new File(path);
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            fileInputStream.read(imageFlow);
            fileInputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return imageFlow;
    }
}
