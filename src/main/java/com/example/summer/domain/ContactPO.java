package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactPO implements PO {

    private static final long serialVersionUID = -979095391950318242L;
    private String id;

    @Override
    public String getID(){
      return id;
    }
}
