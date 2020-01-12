package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarkPO implements PO{

    private static final long serialVersionUID = -6261988056726606873L;
    //mark的Id
    private String markId;

    //标记人的用户名
    private String username;

    //图片描述语句
    private SentencePO sentence;

    //多个部分点标注描述信息
    // eg：嘴唇的点标注   眼睛的点标注
    private ArrayList<DotMarkPO> dotMarks;

    //单标注模式
    private RectangleMarkPO singleRecMark;

    //框选的标注信息
    private ArrayList<RecGroupMarksPO> recGroupMarks;
    //these objects will be saved in the file of imagePO as bytes rather than a single po
    //but they need to implements PO to be serialiable even though they do not setID indeed which has no effect

    @Override
    public String getID() {
        return this.markId;
    }
}
