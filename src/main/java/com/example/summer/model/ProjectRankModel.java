package com.example.summer.model;

import com.example.summer.dao.ProjectRankDao;
import com.example.summer.daoImpl.ProjectRankDaoImpl;
import com.example.summer.domain.ProjectRankPO;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import com.example.summer.utility.json.JsonStringUitility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRankModel {
    private String projectId;
    private LinkedHashMap<String, Integer> rankMap;

    @JsonIgnore
    private static ProjectRankDao projectRankDao = new ProjectRankDaoImpl();

    public static ProjectRankModel generate(String projectId){
        ProjectRankPO po = projectRankDao.queryProjectRankByProjectId(projectId);
        return DozerMapSingleton.getInstance().map(po, ProjectRankModel.class);
    }

    public boolean save(String projectId){
        this.projectId = projectId;
        this.rankMap = new LinkedHashMap<>();
        ProjectRankPO po = DozerMapSingleton.getInstance().map(this, ProjectRankPO.class);
        System.out.println(JsonStringUitility.toString(po));
        return projectRankDao.save(po);
    }

    /**
     * tbt
     * 增加新用户
     * @param username 用户名
     * @return 是否新增成功
     * */
    public boolean addNewMarker(String username){
        if(rankMap.get(username)!=null){return true;}
        rankMap.put(username, 0);
        return  this.update();
    }

    /**
     * tbt
     * 根据用户名得到某个用户的项目内积分
     *@param username 用户名
     *@return 用户的项目内积分
     * */
    public Integer getCreditByUsername(String username){
        Integer credit;
        if ((credit = rankMap.get(username))!=null){return credit;}
        return -1;
    }

    /**
     * tbt
     *根据用户名更新该用户积分
     * @param userId 用户名
     * @param newCredit 新的积分
     * @return 是否更新成功
     * */
    public boolean updateByUserId(String userId, int newCredit){
        rankMap.replace(userId, newCredit);
        return  this.update();
    }

    /**
     * tbt
     * 更新当前项目排名信息
     * @return 是够更新成功
     * */
    public boolean update(){
        ProjectRankPO po = DozerMapSingleton.getInstance().map(this, ProjectRankPO.class);
        return projectRankDao.update(po);
    }


    /**
     * 根据用户名返回当前项目内部的排名
     * @param username 用户名
     * @return 该用户名的项目内排名
     * */
    public int getRankByUsername(String username){
        List<Map.Entry<String,Integer>> sortedList = getSortedList();
        for(int i=0; i<sortedList.size(); i++){
            if (sortedList.get(i).getKey().equals(username)){return  i+1;}
        }
        return -1;
    }

    /**
     *得到某一个区间的用户积分排名
     *@param begin 排名的起始位置
     *@param num 此次查询的人数
     *@return [begin, begin+num)区间内的用用户名和积分
     *  */
    public  ArrayList<WorkerRankInfoModel> getRankList(int begin, int num){
        ArrayList<WorkerRankInfoModel> rankList = new ArrayList<>();
        ArrayList<Map.Entry<String,Integer>> sortedList = getSortedList();
        int size = sortedList.size();
        for(int i=begin; i<begin+num; i++){
            if (i >= size){break;}
            Map.Entry<String,Integer> entry = sortedList.get(i);
            rankList.add(new WorkerRankInfoModel(entry.getKey(), entry.getValue()));
        }
        return  rankList;
    }

    /***/

    /**
     * 得到当前的map按照积分的降序排列
     * @return 升序排列的list
     * */
    public ArrayList<Map.Entry<String,Integer>> getSortedList(){
        ArrayList<Map.Entry<String,Integer>> sortedList = new ArrayList<>(rankMap.entrySet());
        Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //降序排列
                return -o1.getValue().compareTo(o2.getValue());
            }
        });
        return sortedList;
    }
}
