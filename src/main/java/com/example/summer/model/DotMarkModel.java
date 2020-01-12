package com.example.summer.model;

import com.example.summer.domain.DotMarkPO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DotMarkModel implements  Model{
    @JsonIgnore//id不给前端 TODO 待定
    private String dotId;
    //此组标记的tag
    private String tag;
    //点的位置
    private ArrayList<PositionModel> positions;


}
