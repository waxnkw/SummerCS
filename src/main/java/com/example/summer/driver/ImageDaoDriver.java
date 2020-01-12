package com.example.summer.driver;

import com.example.summer.dao.ImageDao;
import com.example.summer.daoImpl.ImageDaoImpl;
import com.example.summer.domain.ImageFlowPO;
import com.example.summer.domain.ImagePO;
import com.example.summer.domain.MarkerInfoPO;
import com.example.summer.stub.ImageFlowPOStub;
import com.example.summer.stub.ImagePOStub;

import java.util.ArrayList;

public class ImageDaoDriver {
    private static final String FAKE_FILE_PATH = "src/main/resources/static/img/";
    private ImageDao dao = new ImageDaoImpl();

    public void createProject(String projectId){
        ArrayList<ImagePO> imagePOs = createImagePOs();
        ArrayList<ImageFlowPO> imageFlowPOs = createImageFlowPOs();
       // dao.saveProjectImages(projectId,imagePOs, imageFlowPOs);
    }

    private  ArrayList<ImageFlowPO> createImageFlowPOs(){
        ArrayList<ImageFlowPO> imageFlows = new ArrayList<>();
        ImageFlowPO po1 = new ImageFlowPOStub().imageFlowPOStub(FAKE_FILE_PATH+"human3.jpg");
        imageFlows.add(po1);
        ImageFlowPO po2 = new ImageFlowPOStub().imageFlowPOStub(FAKE_FILE_PATH+"human4.jpg");
        imageFlows.add(po2);
        ImageFlowPO po3 = new ImageFlowPOStub().imageFlowPOStub(FAKE_FILE_PATH+"human5.jpg");
        imageFlows.add(po3);
        return imageFlows;
    }

    private ArrayList<ImagePO> createImagePOs(){
        ArrayList<ImagePO> images = new ArrayList<>();
        ImagePO po1 = new ImagePOStub().imagePOStub();
        initImagePO(po1);
        images.add(po1);
        ImagePO po2 = new ImagePOStub().imagePOStub();
        initImagePO(po2);
        images.add(po2);
        ImagePO po3 = new ImagePOStub().imagePOStub();
        initImagePO(po3);
        images.add(po3);

        return images;
    }

    private void initImagePO(ImagePO po){
        //po.setMarkers(null);
        //po.setMarks(null);
        po.setSplit("train");
        po.setUpUserLimit(5);
        po.setProjectId("human");
    }

    public static void main(String [] args){
        new ImageDaoDriver().createProject("human");
    }
}
