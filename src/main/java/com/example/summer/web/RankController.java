package com.example.summer.web;

import com.example.summer.model.WorkerRankInfoModel;
import com.example.summer.service.RankService;
import com.example.summer.service.impl.RankServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RankController {

    private RankService rankService = new RankServiceImpl();

    /**
     * 查询用户在一个项目内排名
     * @param projectId 项目Id
     * @param username 用户名
     * @return 用户的项目内排名
     * */
    @RequestMapping("rank/queryRankOfUser")
    public int queryRankInProject(@RequestParam("projectId") String projectId, @RequestParam("username") String username){
        return rankService.queryRankInProject(projectId, username);
    }

    /**
     * 查询一个项目内的所有排名
     * @param begin 查询起始排名
     * @param num 查询人数
     * @param projectId 项目Id
     * @return 用户排名
     * */
    @RequestMapping("/rank/list")
    public ArrayList<WorkerRankInfoModel> RankServiceImpl(@RequestParam("projectId") String projectId
            , @RequestParam("begin") int begin
            , @RequestParam("num") int num){
        return rankService.queryRankListInProject(projectId, begin, num);
    }
}
