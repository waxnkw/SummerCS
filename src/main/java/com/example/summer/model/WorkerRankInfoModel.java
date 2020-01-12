package com.example.summer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 返回给前端的该用户的项目信息
 * @author zhangao
 * @date 2018/4/11
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerRankInfoModel {
    private String name;
    private int credit;
}
