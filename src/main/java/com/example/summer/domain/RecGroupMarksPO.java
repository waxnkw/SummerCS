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
public class RecGroupMarksPO implements PO{

    private static final long serialVersionUID = 8153645351518899234L;
    private int groupId;
    private String tag;
    private ArrayList<RectangleMarkPO> rectangleMarks;

    @Override
    public String getID() {
        return this.groupId+"";
    }
}
