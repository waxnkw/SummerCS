package com.example.summer.utility.convertorUtility;

import com.example.summer.domain.ImagePO;
import com.example.summer.model.SingleUserImageModel;

import java.util.ArrayList;

public class SingleUserImageListModelPOConvertor {
    public static ArrayList<SingleUserImageModel> poToModel(ArrayList<ImagePO> pos, String username){
        if (pos==null){return  null;}
        ArrayList<SingleUserImageModel> models = new ArrayList<>();
        pos.forEach(e-> models.add(new SingleUserImageModel(e,username)));
        return models;
    }
}
