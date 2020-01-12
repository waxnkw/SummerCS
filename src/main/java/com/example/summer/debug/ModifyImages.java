package com.example.summer.debug;

import com.example.summer.configInfo.DataAbsolutePath;
import com.example.summer.model.MarkModel;
import com.example.summer.model.RectangleMarkModel;
import com.example.summer.model.SingleUserImageModel;
import com.example.summer.utility.json.JsonStringUitility;

public class ModifyImages {

    public static void update(String imageId, String username){
        DataAbsolutePath.DEBUG();
        SingleUserImageModel singleUserImageModel = SingleUserImageModel
                .querySingleUserImageByUserIdAndImageId(imageId, username);
        System.out.println("before:"+ JsonStringUitility.toString(singleUserImageModel));
        singleUserImageModel.setMark(MarkModel.builder()
                .username("zhangao")
                .singleRecMark(RectangleMarkModel.builder().recId("5555").height(456).build())
                .build());
        singleUserImageModel.update();
        System.out.println("after:"+ JsonStringUitility.toString(SingleUserImageModel
                .querySingleUserImageByUserIdAndImageId(imageId, username)));
    }

    public static void main(String [] args){
        ModifyImages.update("2018-04-28_21-19-24-621--10","zhangao");
    }
}
