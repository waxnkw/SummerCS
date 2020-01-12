package com.example.summer.model;

import com.example.summer.domain.RecGroupMarksPO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecGroupMarksModel {
    private int groupId;

    private String tag;

    private ArrayList<RectangleMarkModel> rectangleMarks;


}
