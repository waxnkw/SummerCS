package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProjectRankPO implements PO{

    private static final long serialVersionUID = 3939510176850898890L;
    private String projectId;
    private LinkedHashMap<String, Integer> rankMap;

    @Override
    public String getID() {
        return projectId;
    }

}
