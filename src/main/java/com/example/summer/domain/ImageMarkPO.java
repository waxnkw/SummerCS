package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageMarkPO implements  PO{
    private static final long serialVersionUID = -4933947635112672315L;
    private String id;
    public String getID(){
        return id;
    }
}
