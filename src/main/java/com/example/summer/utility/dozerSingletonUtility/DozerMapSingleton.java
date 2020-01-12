package com.example.summer.utility.dozerSingletonUtility;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DozerMapSingleton {
    @Autowired
    private static Mapper instance;

    private DozerMapSingleton() {
    }

    public static synchronized Mapper getInstance() {
        //System.out.println(instance);
        if (instance == null) {
            instance =new DozerBeanMapper();
        }
        return instance;
    }
}
