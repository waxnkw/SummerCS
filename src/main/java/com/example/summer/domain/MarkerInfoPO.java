package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarkerInfoPO  implements PO{
    private static final long serialVersionUID = 2105371645265956969L;
    private String username;
    private int credit;
    private boolean currentImg;
    @Override
    public String getID(){
        return this.username;
    }
}
