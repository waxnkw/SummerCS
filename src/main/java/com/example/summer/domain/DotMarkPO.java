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
public class DotMarkPO extends ImageMarkPO {


    private static final long serialVersionUID = 3553917174954473259L;
    private String dotId;
    private String tag;
    private ArrayList<PositionPO> positions;

    @Override
    public String getID() {
        return dotId;
    }

}
