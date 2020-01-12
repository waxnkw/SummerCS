package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserPO implements PO{
    private static final long serialVersionUID = 4865318226513858323L;
    //先写一个类试试

    private String username;

    private EmailPO email;

    private int totalCredit;

    //暂定String
    private String password;

    @Override
    public String getID(){
        return username;
    }


}
