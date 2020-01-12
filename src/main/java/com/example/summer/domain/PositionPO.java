package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionPO implements PO{
    private static final long serialVersionUID = 4181226798932280025L;
    private int x;
    private int y;


    @Override
    public String getID() {
        return null;
    }
}
