package com.example.summer.modelTest;

import com.example.summer.model.ImageListModel;
import com.example.summer.model.ImageModel;
import com.example.summer.model.MarkerInfoModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ImageListModelTest {
    @Test
    public void testCalCanBeAssignedImagesNum(){
        Assert.assertEquals(0, ImageListModel.calcCanBeAssignedImagesNum("2018-04-15_20-30-49","beibei"));
        //Assert.assertEquals(2, ImageListModel.calcCanBeAssignedImagesNum("2018-04-15_20-30-49","pika"));

        Assert.assertEquals(1, ImageListModel.calcCanBeAssignedImagesNum("2018-04-15_20-30-49","pika"));
        ArrayList<MarkerInfoModel> markers= ImageModel.queryImageModelByImageId("2018-04-15_20-31-47--0").getMarkers();
        Assert.assertEquals("pika",markers.get(2).getUsername());
    }

    @Test
    public void testAllocateImages(){
        Assert.assertEquals(true,ImageListModel.allocateNewMarker("2018-04-15_20-30-49","pika",1));

    }

}
