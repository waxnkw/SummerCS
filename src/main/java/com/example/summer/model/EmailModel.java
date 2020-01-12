package com.example.summer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @program: summerCS_Phase_I
 * @description: email的model部分
 * @author: 161250193
 * @create: 2018/3/15
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel implements  Model{
    private String address;

}
