package com.example.summer.modelTest;

import com.example.summer.model.ImageListModel;
import com.example.summer.model.ImageModel;
import com.example.summer.model.MarkModel;
import com.example.summer.model.MarkerInfoModel;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ImageListModelTest1 {
    private ImageListModel imageListModel1 = new ImageListModel();

    @Before
    public void setUp(){
        imageListModel1.setImageList(getImages());

    }

    private ArrayList<ImageModel> getImages(){
        ArrayList<ImageModel> imageModelArrayList = new ArrayList<>();
        imageModelArrayList.add(ImageModel.builder().markers(getMarkers()).marks(getMarks()).build());
        imageModelArrayList.add(ImageModel.builder().markers(getMarkers()).marks(getMarks()).build());
        imageModelArrayList.add(ImageModel.builder().markers(getMarkers()).marks(getMarks()).build());
        return imageModelArrayList;
    }

    private   ArrayList<MarkModel> getMarks(){
        ArrayList<MarkModel> markModelArrayList = new ArrayList<>();
        markModelArrayList.add(MarkModel.builder().username("wny").build());
        return markModelArrayList;
    }

    private  ArrayList<MarkerInfoModel> getMarkers(){
        ArrayList<MarkerInfoModel> markerInfoModels = new ArrayList<>();
        markerInfoModels.add(MarkerInfoModel.builder().username("zhangao").currentImg(true).build());
        markerInfoModels.add(MarkerInfoModel.builder().username("wny").currentImg(true).build());
        return markerInfoModels;
    }

    private   String jsonFy(Object o){
        return JSONObject.fromObject(o).toString();
    }

    @Test
    public void testCloseImgs(){
        imageListModel1.closeImages("zhangao");
        System.out.println(jsonFy(imageListModel1));
    }

    @Test
    public void testCalcTotalCreditOfImgs(){
        int credit1 = imageListModel1.calcTotalCreditOfImgs(1,"wny");
        int credit2 = imageListModel1.calcTotalCreditOfImgs(1,"zbb");
        Assert.assertEquals(3, credit1);
        Assert.assertEquals(0, credit2);
    }
}
