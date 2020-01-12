package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RectangleMarkPO extends ImageMarkPO {
    private static final long serialVersionUID = -4891160458021053400L;
    //rectangle的Id
    private String recId;
    //tag描述
    private String tag;
    //左上角点的坐标
    private PositionPO startDot;
    //宽度
    private int width;
    //长度
    private int height;


    @Override
    public String getID() {
        return this.recId;
    }

}
