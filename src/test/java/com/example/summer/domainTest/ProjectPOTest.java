package com.example.summer.domainTest;


/*
 * @program: summerCS_Phase_II
 * @description: dozer测试用的PO
 * @author: Mr.Wang
 * @create: 2018/4/11
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPOTest {

    private String projectName;

    private String launcherName;
}
