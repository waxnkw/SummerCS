package com.example.summer.model;


import lombok.*;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarkModel implements  Model {
    //mark的Id TODO 待定
    private String markId;

    //标记人的用户名
    private String username;

    //多个部分点标注描述信息
    // eg：嘴唇的点标注   眼睛的点标注
    private ArrayList<DotMarkModel> dotMarks;

    //图片描述语句
    private SentenceModel sentence;

    private RectangleMarkModel singleRecMark;

    //框选的标注信息
    private ArrayList<RecGroupMarksModel> recGroupMarks;
    //these objects will be saved in the file of imagePO as bytes rather than a single po
    //but they need to implements PO to be serialiable even though they do not setID indeed which has no effect

}
