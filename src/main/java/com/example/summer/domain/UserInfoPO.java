package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoPO implements PO{
    private static final long serialVersionUID = -2556167166581075079L;
    private String username;
    @Override
    public String getID() {
        return username;
    }
}
