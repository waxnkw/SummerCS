package com.example.summer.daoImpl;

import com.example.summer.configInfo.DataAbsolutePath;
import com.example.summer.dao.ImageDao;
import com.example.summer.domain.*;
import com.example.summer.utility.dataUtility.InitTimeUtility;
import com.example.summer.utility.dataUtility.ReadAndWritePOsUtility;
import com.example.summer.utility.dataUtility.SaveImageFlowUtility;
import com.example.summer.utility.json.JsonStringUitility;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;


@Repository
public class ImageDaoImpl implements ImageDao{


    private static final String IMAGEPO_LAST_FILE_PATH = DataAbsolutePath.DATA_PATH+"Image";
    //private static final String IMAGE_FLOW_LAST_FILE_PATH ="data/ImageFlow/"; //DataAbsolutePath.IMAGEFLOW_PATH;
  //  private static final String IMAGE_PATH="static/data/Image";
   // private static final String IMAGE_FLOW_SUFFIX=".jpg";

    /**
     * 根据图片PO的id查询该项目所有图片信息
     * @param imageId 图片PO的Id
     * @return 该图片PO
     * */
    public ImagePO queryImageByImageId(String imageId){
        ArrayList<PO> pos= ReadAndWritePOsUtility.readPOs(IMAGEPO_LAST_FILE_PATH);
       // ImagePO imagePO = null;
        for (int i = 0; i <= pos.size() - 1; i++) {
            PO po=pos.get(i);
            if(po instanceof ImagePO){
                ImagePO imagePO=(ImagePO)po;
                String id=imagePO.getImageId();
                if(id.equals(imageId)){
                    return imagePO;
                }
            }
        }
        return null;
    }

    /**
     * 根据项目id查询该项目所有图片信息
     *
     * @param projectID 项目Id
     * @return 该项目的图片信息
     */
    @Override
    public  ArrayList<ImagePO> listImagesByProjectId(String projectID) {
        ArrayList<PO> pos= ReadAndWritePOsUtility.readPOs(IMAGEPO_LAST_FILE_PATH );
        ArrayList<ImagePO> imagePOs=new ArrayList<ImagePO>();
        for (int i = 0; i <= pos.size() - 1; i++) {
            PO po=pos.get(i);

            ImagePO imagePO=(ImagePO)po;
            String projectId=imagePO.getProjectId();
            if(projectId.equals(projectID)){
                imagePOs.add(imagePO);
            }
        }
        if(imagePOs.size()==0) {
            return null;
        }
        return imagePOs;
    }

    /**
     * 根据项目id和userID查询某用户的某项目的所有图片信息
     *
     * @param projectId 项目Id，userId 用户Id
     * @param userId
     * @return 该项目的图片信息
     */
    @Override
    public ArrayList<ImagePO> listImagesByProjectIdAndUserId(String projectId, String userId){
        ArrayList<ImagePO> imagePOsOfProjectID = listImagesByProjectId(projectId);
        //imagePOsOfProjectID.forEach(e->System.out.println(JsonStringUitility.toString(e)));
        ArrayList<ImagePO> imagePOsOfUserID = new ArrayList<ImagePO>();
        if (imagePOsOfProjectID == null) { return null; }
        for (int i = 0; i <= imagePOsOfProjectID.size() - 1; i++) {
            ImagePO imagePoOfProjectID = imagePOsOfProjectID.get(i);
            ArrayList<MarkerInfoPO> markers = imagePoOfProjectID.getMarkers();
            if(markers == null){continue;}
            for (int j = 0; j <= markers.size() - 1; j++) {
                MarkerInfoPO markerInfo = markers.get(j);
                String markerName = markerInfo.getUsername();
                if (markerName.equals(userId)) { imagePOsOfUserID.add(imagePoOfProjectID); }
            }
        }
        if (imagePOsOfUserID.size() == 0) { return null; }
        return imagePOsOfUserID;
    }

    /**
     * * 根据项目id和userID查询某用户的某项目的所有图片信息
     * begin=12 num=9 则取出 12到20 图片信息。 若不到20张图，返回剩余所有图片
     * @param projectId 项目Id
     * @param userId 用户名
     * @param begin 开始文件的标号,从0开始
     * @param num 取出的图片数量
     * @return 该项目的图片信息
     * */
    @Override
    public ArrayList<ImagePO> listImagesByProjectIdAndUserId(String projectId, String userId, int begin, int num){
        ArrayList<ImagePO> imagePOsOfUserIDAndProjectID = listImagesByProjectIdAndUserId(projectId,userId);
        ArrayList<ImagePO> imagePOsOfNum=new ArrayList<ImagePO>();
        int count=0;
        if (imagePOsOfUserIDAndProjectID==null||imagePOsOfUserIDAndProjectID.size() == 0||imagePOsOfUserIDAndProjectID.size()<(begin+1)) { return null; }
        for(int i=begin;i<=imagePOsOfUserIDAndProjectID.size()-1;i++){
            //begin can be 0
            imagePOsOfNum.add(imagePOsOfUserIDAndProjectID.get(i));
            count++;
            if(count==num){
                break;
                //to avoid begin+num>pos.size()
                //if begin+num>pos.size(), just get pos until pos.size()-1
                //if begin+num<pos.size(),get pos of num(count)
            }
        }
        if(imagePOsOfNum.size()==0){
            return null;
        }
        return imagePOsOfNum;
    }


    /**
     * 根据项目id和userID查询某用户的某项目的当前批次的图片信息
     *
     * @param projectId 项目Id，userId 用户Id
     * @return 该批次的图片信息
     */
    @Override
    public ArrayList<ImagePO> listCurrentImagesByProjectIdAndUserId(String projectId, String userId){
        ArrayList<ImagePO> imagePOsOfProjectID = listImagesByProjectId(projectId);
        ArrayList<ImagePO> currentImagePOsOfUserID = new ArrayList<ImagePO>();
        if (imagePOsOfProjectID == null) { return null; }
        for (int i = 0; i <= imagePOsOfProjectID.size() - 1; i++) {
            ImagePO imagePoOfProjectID = imagePOsOfProjectID.get(i);
            ArrayList<MarkerInfoPO> markers = imagePoOfProjectID.getMarkers();
            if(markers == null){continue;}
            for (int j = 0; j <= markers.size() - 1; j++) {
                MarkerInfoPO markerInfo = markers.get(j);
                String markerName = markerInfo.getUsername();
                boolean isCurrent=markerInfo.isCurrentImg();
                if (markerName.equals(userId)&&isCurrent) { currentImagePOsOfUserID.add(imagePoOfProjectID); }
            }
        }
        if (currentImagePOsOfUserID.size() == 0) { return null; }
        return currentImagePOsOfUserID;
    }


    /**
     * * 根据项目id和userID查询某用户的某项目的当前一批的部分图片信息
     * begin=12 num=9 则取出 12到20 图片信息。 若不到20张图，返回剩余所有图片
     * @param projectId 项目Id
     * @param userId 用户名
     * @param begin 开始文件的标号
     * @param num 取出的图片数量
     * @return 该项目的图片信息
     * */
    @Override
    public ArrayList<ImagePO> listCurrentImagesByProjectIdAndUserId(String projectId, String userId, int begin, int num){
        ArrayList<ImagePO> currentImagePOsOfUserIDAndProjectID = listCurrentImagesByProjectIdAndUserId(projectId,userId);
        ArrayList<ImagePO> imagePOsOfNum=new ArrayList<ImagePO>();
        int count=0;
        if (currentImagePOsOfUserIDAndProjectID == null||currentImagePOsOfUserIDAndProjectID.size() == 0||currentImagePOsOfUserIDAndProjectID.size()<(begin+1)) { return null; }
        for(int i=begin;i<=currentImagePOsOfUserIDAndProjectID.size()-1;i++){
            //begin can be 0
            imagePOsOfNum.add(currentImagePOsOfUserIDAndProjectID.get(i));
            count++;
            if(count==num){
                break;
                //to avoid begin+num>pos.size()
                //if begin+num>pos.size(), just get pos until pos.size()-1
                //if begin+num<pos.size(),get pos of num(count)
            }
        }
        if(imagePOsOfNum.size()==0){
            return null;
        }
        return imagePOsOfNum;
    }

    /**
     * 更新图片信息(只更新已存在图片)
     * 不要更新已有的Id
     *
     * @param imagePO 需要更新的图片
     * @return 是否新增成功
     */
    @Override
    public boolean updateImage(ImagePO imagePO){
        String imageID=imagePO.getImageId();
        ArrayList<PO> pos= ReadAndWritePOsUtility.readPOs(IMAGEPO_LAST_FILE_PATH);
        ArrayList<ImagePO> imagePOs=new ArrayList<ImagePO>();
        boolean exist=false;
        for (int i = 0; i <= pos.size() - 1; i++) {
                PO po=pos.get(i);
                if(po instanceof ImagePO){
                    ImagePO imagePo=(ImagePO)po;
                    String id=imagePo.getImageId();
                    if(id.equals(imageID)){
                        exist=true;
                        imagePOs.add(imagePO);
                    }else{
                        imagePOs.add(imagePo);
                    }
                    //read all records first,and select the target object and replace it
                }else{
                    return false;
                    //this means the file path is false so the content of the file is not of class ImagePO
                    //it will not happen as normal
                }
            }
            if(exist){
                ReadAndWritePOsUtility.writePOs(imagePOs,IMAGEPO_LAST_FILE_PATH);
                return true;
            }
            return false;
        }


    /**
     * 根据项目Id储存该项目的 图片信息 以及 图片本体
     * 需要张贝贝同学自己生成：imageId fileName(example：xxx.jpg)
     * 建议:上面生成 Id和name 抽象一个方法叫 imageInit(ImagePO)方法,不要写进代码里
     *
     * @param projectId  项目Id
     * @param images     图片信息
     * @return 是否储存成功
     */
    @Override
    public boolean saveProjectImages(String projectId, ArrayList<ImagePO> images) {
        return ReadAndWritePOsUtility.writePos(images,IMAGEPO_LAST_FILE_PATH);
    }

    /**
     * 本来我不应该改这个类的，但是我不改就写不下去了，就先写接口吧，实现就交给贝贝啦，辛苦了
     * hhhhhh，不辛苦，应该的~
     * 批量返回一个项目的图片
     * @param projectID projectId
     * @param begin 开始位置
     * @param num 图片数量
     * @return java.util.ArrayList<com.example.summer.domain.ImagePO>
     */
    public ArrayList<ImagePO> listLauncherImagesByProjectId(String projectID, int begin, int num){
        ArrayList<ImagePO> imagePosOfTheProjectId= listImagesByProjectId(projectID);
        ArrayList<ImagePO> imagePOs=new ArrayList<ImagePO>();
        int count=0;
        if(imagePosOfTheProjectId==null||imagePosOfTheProjectId.size()==0||begin>=imagePosOfTheProjectId.size()){
            return null;
        }
        for (int i = begin; i <= imagePosOfTheProjectId.size()-1; i++) {
            ImagePO po=imagePosOfTheProjectId.get(i);
            //this means the file path is false so the content of the file is not of class ImagePO
            //it will not happen as normal
                imagePOs.add(po);
                count++;
                if(count==num){
                    break;
                    //to avoid begin+num>pos.size()
                    //if begin+num>pos.size(), just get pos until pos.size()
                    //if begin+num<pos.size(),get pos of num(count)
                }

        }
        if(imagePOs.size()==0) {
            return null;
        }
        return imagePOs;
    }



}
