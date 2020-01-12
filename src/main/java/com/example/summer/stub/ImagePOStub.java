package com.example.summer.stub;

import com.example.summer.domain.*;

import java.util.ArrayList;

public class ImagePOStub {
    public ImagePO imagePOStub(){
        ImagePO  po = new ImagePO();
        po.setFileName("pic1");
  //      po.setImageId("20170386111");
   //     po.setProjectId("project2015");
        po.setSplit("train");
        po.setUpUserLimit(100);

        po.setMarkers(markersStub());

        ArrayList<MarkPO> markPOArrayList = new ArrayList<>();
        markPOArrayList.add(markPOStub1());
        markPOArrayList.add(markPOStub2());
       // markPOArrayList.add(markPOStub());
        po.setMarks(markPOArrayList);

        return  po;
    }

    public ArrayList<MarkerInfoPO> markersStub(){
        ArrayList<MarkerInfoPO> markers = new ArrayList<>();
        MarkerInfoPO markerInfoPO1 = new MarkerInfoPO();
        markerInfoPO1.setUsername("beibei");
        MarkerInfoPO markerInfoPO2 = new MarkerInfoPO();
        markerInfoPO2.setUsername("boogie");
        markers.add(markerInfoPO1);
        markers.add(markerInfoPO2);
        return markers;
    }

    public MarkPO markPOStub1(){
        MarkPO markPO = new MarkPO();
        markPO.setMarkId("mark1");
        markPO.setUsername("boogie");

        //markPO.setSentence(sentencePOStub());
        //markPO.setSingleRecMark(rectangleMarkPOStub());

        //markPO.setDotMarks(dotMarkPOSStub());

        //markPO.setRecGroupMarks(recGroupMarksPOSListStub());
        return markPO;
    }
    public MarkPO markPOStub2(){
        MarkPO markPO = new MarkPO();
        markPO.setMarkId("mark2");
        markPO.setUsername("beibei");

        //markPO.setSentence(sentencePOStub());
        //markPO.setSingleRecMark(rectangleMarkPOStub());

        //markPO.setDotMarks(dotMarkPOSStub());

        //markPO.setRecGroupMarks(recGroupMarksPOSListStub());
        return markPO;
    }

    private SentencePO sentencePOStub(){
        SentencePO sentencePO = new SentencePO();
        sentencePO.setRawStr("我 是 人");
        ArrayList<String> tokens = new ArrayList<String>();
        for (String str: sentencePO.getRawStr().split(" ")){
            tokens.add(str);
        }
        sentencePO.setTokens(tokens);
        sentencePO.setSentenceId("sentencePO");
        return  sentencePO;
    }

    private RectangleMarkPO rectangleMarkPOStub(){
        RectangleMarkPO rectangleMarkPO = new RectangleMarkPO();
//        rectangleMarkPO.setHeight(100);
//        rectangleMarkPO.setWidth(56);
//        rectangleMarkPO.setTag("singleRecMark");
//        rectangleMarkPO.setStartDot(new PositionPO(1,2));
//        rectangleMarkPO.setRecId("rec1");
        return  rectangleMarkPO;
    }

    private ArrayList<DotMarkPO> dotMarkPOSStub(){
        ArrayList<DotMarkPO> dotMarkPOS = new ArrayList<>();

        DotMarkPO dotMarkPO1 = new DotMarkPO();
        dotMarkPO1.setDotId("dot1");
        ArrayList<PositionPO> dots1 = new ArrayList<>();
        dots1.add(new PositionPO(1,5));
        dots1.add(new PositionPO(1,3));
        dotMarkPO1.setPositions(dots1);
        dotMarkPOS.add(dotMarkPO1);

        DotMarkPO dotMarkPO2 = new DotMarkPO();
        ArrayList<PositionPO> dots2 = new ArrayList<>();
        dots2.add(new PositionPO(1,5));
        dots2.add(new PositionPO(1,3));
        dotMarkPO1.setPositions(dots2);
        dotMarkPOS.add(dotMarkPO2);
        return dotMarkPOS;
    }

    private  ArrayList<RecGroupMarksPO> recGroupMarksPOSListStub(){
        ArrayList<RecGroupMarksPO> recGroupMarksPOS = new ArrayList<>();
        recGroupMarksPOS.add(recGroupMarksPOStub());
        recGroupMarksPOS.add(recGroupMarksPOStub());
        return  recGroupMarksPOS;
    }

    private RecGroupMarksPO recGroupMarksPOStub(){
        RecGroupMarksPO recGroupMarksPO1 = new RecGroupMarksPO();
        recGroupMarksPO1.setGroupId(1);
        recGroupMarksPO1.setTag("asd");


        ArrayList<RectangleMarkPO> rectangleMarkPOArrayList = new ArrayList<>();
        rectangleMarkPOArrayList.add(rectangleMarkPOStub());
        rectangleMarkPOArrayList.add(rectangleMarkPOStub());

        recGroupMarksPO1.setRectangleMarks(rectangleMarkPOArrayList);
        return recGroupMarksPO1;
    }

}
