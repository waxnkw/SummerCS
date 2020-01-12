package com.example.summer.model;

import com.example.summer.domain.RectangleMarkPO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RectangleMarkModel {

    //rectangle的Id
    @JsonIgnore//不给前端 TODO 待定
    private String recId;
    //tag描述
    private String tag;
    //左上角点的坐标
    private PositionModel startDot;
    //宽度
    private int width;
    //长度
    private int height;

    /***************************************************************************
     *                                                                         *
     * Constructor                                                      *
     *                                                                         *
     **************************************************************************/
    public RectangleMarkModel(RectangleMarkPO po){
        this.height = po.getHeight();
        this.recId = po.getRecId();
        if(po.getStartDot()!=null){this.startDot = new PositionModel(po.getStartDot().getX(),po.getStartDot().getY());}
        this.tag = po.getTag();
        this.width = po.getWidth();
    }
}
