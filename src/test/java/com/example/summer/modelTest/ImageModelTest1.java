package com.example.summer.modelTest;

import com.example.summer.model.ImageModel;
import com.example.summer.model.MarkModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ImageModelTest1 {
    ImageModel imageModel1 = new ImageModel();
    ArrayList<ImageModel> imageList = new ArrayList<>();

    @Before
    public void setUp(){
        ArrayList<MarkModel> marks = new ArrayList<>();
        marks.add(MarkModel.builder().username("zhangao").build());
        marks.add(MarkModel.builder().username("wny").build());
        imageModel1.setMarks(marks);
    }

    @Test
    public void testMarkedByMarker(){
        Assert.assertEquals(true, imageModel1.markedByMarker("zhangao"));
        Assert.assertEquals(false, imageModel1.markedByMarker("zbb"));
    }

}
