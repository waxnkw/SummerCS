package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailPO extends ContactPO {

    private static final long serialVersionUID = -5789739567320038607L;
    private String address;

    @Override
    public String getID() {
        return address;
    }
}
