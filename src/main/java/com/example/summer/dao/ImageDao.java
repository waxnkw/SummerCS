package com.example.summer.dao;

import com.example.summer.domain.ImageFlowPO;
import com.example.summer.domain.ImagePO;
import com.example.summer.utility.dataUtility.ReadAndWritePOsUtility;

import java.util.ArrayList;

public interface ImageDao {
    /**
     * 根据图片PO的id查询该项目所有图片信息
     * @param imageId 图片PO的Id
     * @return 该图片PO
     * */
    ImagePO queryImageByImageId(String imageId);
    /**
     * 根据项目id查询该项目所有图片信息
     * @param projectId 项目Id
     * @return 该项目的图片信息
     * */
     ArrayList<ImagePO> listImagesByProjectId(String projectId);

    /**
     * * 根据项目id和userID查询某用户的某项目的所有图片信息
     * @param projectId 项目Id
     * @param userId 用户名
     * @return 该用户的该项目的图片信息
     * */
    ArrayList<ImagePO> listImagesByProjectIdAndUserId(String projectId, String userId);//先写在这里，防止其他地方出错

    /**
     * * 根据项目id和userID查询某用户的某项目的部分图片信息
     * begin=12 num=9 则取出 12到20 图片信息。 若不到20张图，返回剩余所有图片
     * @param projectId 项目Id
     * @param userId 用户名
     * @param begin 开始文件的标号
     * @param num 取出的图片数量
     * @return 该项目的图片信息
     * */
   ArrayList<ImagePO> listImagesByProjectIdAndUserId(String projectId, String userId, int begin, int num);

    /**
     * * 根据项目id和userID查询某用户的某项目的当前一批的全部图片信息
     * @param projectId 项目Id
     * @param userId 用户名
     * @return 该项目的图片信息
     * */
    ArrayList<ImagePO> listCurrentImagesByProjectIdAndUserId(String projectId, String userId);

    /**
     * * 根据项目id和userID查询某用户的某项目的当前一批的部分图片信息
     * begin=12 num=9 则取出 12到20 图片信息。 若不到20张图，返回剩余所有图片
     * @param projectId 项目Id
     * @param userId 用户名
     * @param begin 开始文件的标号
     * @param num 取出的图片数量
     * @return 该项目的图片信息
     * */
    ArrayList<ImagePO> listCurrentImagesByProjectIdAndUserId(String projectId, String userId,int begin, int num);

    /**
     * 更新图片信息(只更新已存在图片)
     * 不要更新已有的Id
     * @param po 需要更新的图片
     * @return 是否新增成功
     * */
    boolean updateImage(ImagePO po);

    /**
     * 根据项目Id储存该项目的 图片信息 以及 图片本体
     * 需要张贝贝同学自己生成：imageId fileName(example：xxx.jpg)
     * 建议:上面生成 Id和name 抽象一个方法叫 imageInit(ImagePO)方法,不要写进代码里
     * @param projectId 项目Id
     * @param  images 图片信息
     * @return 是否储存成功
     * */
    boolean saveProjectImages(String projectId, ArrayList<ImagePO> images);

    /**
     * 本来我不应该改这个类的，但是我不改就写不下去了，就先写接口吧，实现就交给贝贝啦，辛苦了
     * hhhhhh，不辛苦，应该的~
     * 批量返回一个项目的图片
     * @param projectId
     * @param begin 开始位置
     * @param num 图片数量
     * @return java.util.ArrayList<com.example.summer.domain.ImagePO>
     */
    ArrayList<ImagePO> listLauncherImagesByProjectId(String projectId, int begin, int num);

}
