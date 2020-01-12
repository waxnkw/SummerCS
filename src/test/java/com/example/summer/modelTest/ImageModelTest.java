package com.example.summer.modelTest;


import com.example.summer.model.ImageModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImageModelTest {
    ImageModel image;
    @Before
    public void initData(){
        image=ImageModel.queryImageModelByImageId("2018-04-15_20-31-47--0");
    }

    @Test
    public void testCanAddNewMarker(){
        Assert.assertTrue(image.canAddNewMarker());
    }
    @Test
    public void testMarkerAlreadyIn(){
        Assert.assertTrue(image.markerAlreadyIn("beibei"));
        Assert.assertTrue(image.markerAlreadyIn("bei"));
    }
}
