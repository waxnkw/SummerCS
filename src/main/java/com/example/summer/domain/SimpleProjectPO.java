package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleProjectPO extends ProjectPO {
    private static final long serialVersionUID = -1201151968901277901L;
    private int eachCredit;
}
