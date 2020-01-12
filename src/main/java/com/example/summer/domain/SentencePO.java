package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

/*
 * @program: summerCS_Phase_I
 * @description: 对图片的描述
 * @author: 161250140 wny
 * @create: 2018/3/17
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class SentencePO implements PO{

    private static final long serialVersionUID = 2885183213076601473L;
    private String sentenceId;

    private String rawStr;

    private ArrayList<String> tokens;

    @Override
    public String getID(){
        return this.sentenceId;
    }
    
}
