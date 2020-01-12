package com.example.summer.model;

import com.example.summer.domain.MarkerInfoPO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarkerInfoModel {
    private String username;
    private int credit;
    private boolean currentImg;


}
