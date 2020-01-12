package com.example.summer.utility.convertorUtility;

import com.example.summer.domain.SimpleProjectPO;
import com.example.summer.model.SimpleProjectModel;

public class ProjectMapClassUtility {
    public static Class<? extends Object> getDestinationClass(Object o){
        if (o instanceof SimpleProjectModel){ return SimpleProjectPO.class;}
        if (o instanceof SimpleProjectPO){return SimpleProjectModel.class;}
        return null;
    }
}
