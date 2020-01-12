package com.example.summer.web;

import com.example.summer.model.ProjectInfoModel;
import com.example.summer.model.SingleUserImageModel;
import com.example.summer.model.WorkerProjectInfoModel;
import com.example.summer.service.RankService;
import com.example.summer.service.WorkerService;

import com.example.summer.service.impl.RankServiceImpl;
import com.example.summer.utility.json.JsonStringUitility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class WorkerController {

    @Autowired
    private WorkerService workerService;
    private RankService rankService = new RankServiceImpl();
    /**
     * 加入项目
     * @param projectId  项目Id
     * @param username  用户名
     * @return 加入的项目Id
     * */
    @RequestMapping("/worker/join")
    public String joinProject(@RequestParam("projectId") String projectId
            , @RequestParam("username") String username
            , @RequestParam("claimNumber") Integer claimNumber){
        //workerService.askMoreImages(projectId, username, claimNumber);//分配任务
        rankService.addRanker(projectId, username);
        return  workerService.joinProject(projectId, username, claimNumber);
    }

    /**
     * 索要更多的图片
     * @param projectId  项目Id
     * @param username  用户名
     * @return 新标记的图片
     * */
    @RequestMapping("/worker/ask")
    public boolean askMoreImages(@RequestParam("projectId") String projectId
            ,@RequestParam("username") String username
            ,@RequestParam("claimNumber") Integer claimNumber){
        return workerService.askMoreImages(projectId, username, claimNumber);
    }

    /**
     * 保存一张图片信息
     * 更新个人图片的信息
     * @param image 需要保存的图片信息
     * @return 是否保存成功
     * */
    @RequestMapping(value = "/worker/saveImage", method = RequestMethod.POST )
    public boolean saveImage(@RequestBody SingleUserImageModel image){
        System.out.println(JsonStringUitility.toString(image));
        return workerService.saveImage(image);
    }

    /**
     * 通过用户名查询一个用户的
     * @param username 用户的Id
     * @param projectId 项目Id
     * @return 该用户认领的该项目下的所有图片
     * */
    @RequestMapping("/worker/images")
    public ArrayList<SingleUserImageModel>
        queryImagesByUserNameAndProjectId(@RequestParam("username") String username,
                                          @RequestParam("projectId") String projectId,
                                          @RequestParam("begin") int begin,
                                          @RequestParam("num") int num) {
        //参数成功传递
        //System.out.println("JoinerController.queryByUserNameAndProjId:    "+"projectId: "+projectId + "  username: "+ username);
        return workerService.queryImagesByUserIdAndProjectId(username, projectId, begin, num);
    }

    /**
     * 根据用户名和项目Id查看这个人的项目内现有的积分
     * @param username 用户名
     * @param projectId 项目Id
     * @return 该用户在项目内积分
     */
    @RequestMapping("/worker/proj/credit")
    public int queryCreditByUsernameAndProjectId(@RequestParam("username") String username,
                                        @RequestParam("projectId") String projectId){
        return rankService.queryCreditInProject(projectId, username);
    }

    /**
     * 根据用户名和项目Id查询该用户的项目内信息(项目名称，图片总数等)
     * @param username
     * @param projectId
     * @return
     */
    @RequestMapping("/worker/proj/projInfo")
    public WorkerProjectInfoModel queryWorkerProjectInfoByUsernameAndProjectId(@RequestParam("username") String username,
                                                                               @RequestParam("projectId") String projectId){
        return workerService.queryWorkerProjectInfoByUsernameAndProjectId(username, projectId);
    }

    /**
     * 查看可以加入的项目
     * @return 可以加入的项目简略信息清单
     * */
    @RequestMapping("/worker/proj/canJoinedProj")
    public  ArrayList<ProjectInfoModel> listCanbeJoinedProjectInfos(){
        return  workerService.queryCanbeJoinedProjectInfos();
    }

    /**
     *结算当前标记批次的图片
     * @param projectId 项目的Id
     * @param username 用户名
     * @return  本批次的总得分
     * */
    @RequestMapping("/worker/closeBatch")
    public int closeBatchImgs(@RequestParam("projectId") String projectId, @RequestParam("username") String username){
        return  workerService.closeBatchImgs(projectId, username);
    }

    /**
     * 根据项目Id和用户名得到一个用户加入的项目清单
     *@param username 用户名
     * @return 项目的简略信息清单
     * */
    @RequestMapping("/worker/listJoinedProjs")
    public ArrayList<ProjectInfoModel> listJoinedProjectInfosByProjectIdAndUserName( String username){
        //System.out.println("WorkerController: " + JsonStringUitility.toString(workerService.listJoinedProjectInfosByProjectIdAndUserName(username)));
        return  workerService.listJoinedProjectInfosByProjectIdAndUserName(username);
    }

}
