package com.example.summer.model;

import com.example.summer.dao.ImageDao;
import com.example.summer.daoImpl.ImageDaoImpl;
import com.example.summer.domain.ImagePO;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageModel implements  Model{
    //id
    private String imageId;

    //用户最大上限
    @JsonIgnore
    private int upUserLimit;

    //标记者们
    private ArrayList<MarkerInfoModel> markers;
    //图片标记们
    private ArrayList<MarkModel> marks;
    //对应项目id
    private String projectId;
    //图片名称
    private String fileName;
    //是train还是test
    private String split;


    /***************************************************************************
     *                                                                         *
     * static methods                                                   *
     *                                                                         *
     *************************************************************************/
    @JsonIgnore
   // @Autowired
    private static ImageDao imageDao= new ImageDaoImpl();

    public static ArrayList<ImageModel> listCurrentImagesByUserIdAndProjectId(String username, String projectId, int begin, int num){
        ArrayList<ImageModel> imageModels = new ArrayList<>();
        ArrayList<ImagePO> imagePOS = imageDao.listCurrentImagesByProjectIdAndUserId(username, projectId, begin, num);
        imagePOS.forEach(e->imageModels.add(DozerMapSingleton.getInstance().map(e, ImageModel.class)));
        return imageModels;
    }

    /**
     * @author zhangao
     * @param projectId projectId
     * @return
     */
    public static ArrayList<ImageModel> listCurrentImagesByUserIdAndPorjectId(String projectId, String username){
        ArrayList<ImageModel> currentImagesModels = new ArrayList<>();
        ArrayList<ImagePO> pos = imageDao.listCurrentImagesByProjectIdAndUserId(projectId, username);
        if(pos==null){return null;}
        pos.forEach(e->currentImagesModels.add(DozerMapSingleton.getInstance().map(e, ImageModel.class)));
        return currentImagesModels;
    }

    /**
     * @author Mr.Wang
     * @param projectId projectId
     * @param begin 开始位置
     * @param num 图片数量
     * @return java.util.ArrayList<com.example.summer.model.ImageModel>
     */
    static ArrayList<ImageModel> listImageModelByProjectId(String projectId, int begin, int num){
        ArrayList<ImageModel> imageModels = new ArrayList<>();
        ArrayList<ImagePO> imagePOS = imageDao.listLauncherImagesByProjectId(projectId, begin, num);
        if (imagePOS==null){return null;}
        imagePOS.forEach(e->imageModels.add(DozerMapSingleton.getInstance().map(e, ImageModel.class)));
        return imageModels;
    }

    /**
     * 根据项目Id查询ArrayList<ImageModel>
     * @author Mr.Wang
     * @param projectId projectId
     * @return java.util.ArrayList<com.example.summer.model.ImageModel>
     */
    static ArrayList<ImageModel> listImagesByProjectId(String projectId){
        ArrayList<ImageModel> imageModels = new ArrayList<>();
        ArrayList<ImagePO> imagePOS = imageDao.listImagesByProjectId(projectId);
        if(imagePOS!=null){
            imagePOS.forEach(e->imageModels.add(DozerMapSingleton.getInstance().map(e, ImageModel.class)));
            return imageModels;
        }
        return null;
    }

    /**
     * 根据图片Id查询ImageModel
     * @author bb
     * @param imageId
     * @return java.util.ArrayList<com.example.summer.model.ImageModel>
     */
    public static ImageModel queryImageModelByImageId(String imageId){
        ImagePO image=imageDao.queryImageByImageId(imageId);
        ImageModel imageModel=DozerMapSingleton.getInstance().map(image, ImageModel.class);
        return imageModel;
    }


    /***************************************************************************
     *                                                                         *
     * non static methods                                                   *
     *                                                                         *
     *************************************************************************/

    /**更新存储ImageModel（ImagePO）
     * @author bb
     * @param () ImageModel model
     * @return boolean
     */
    public boolean update(){
        ImagePO po= DozerMapSingleton.getInstance().map(this, ImagePO.class);
        return imageDao.updateImage(po);
    }


    /**判断这个图片是否还可以加新的标记者
     * @author bb
     * @param () 空
     * @return boolean
     */
     public  boolean canAddNewMarker(){
        if(this.markers==null){
            return true;
        }
        if(this.markers.size()<this.upUserLimit){
            return true;
        }
        return false;
    }

    /**
     * 判断markers之中是否有这个人
     * @author bb
     * @param username username
     * @return boolean
     */
    public boolean markerAlreadyIn(String username){
        //TODO
        if(markers==null){
            return false;
        }
        for(int i=0;i<=markers.size()-1;i++){
            MarkerInfoModel marker=markers.get(i);
            String name=marker.getUsername();
            if(username.equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * 将某一个标记者的图片提交(置为非current批次)
     * 更新后台存储数据
     * @param username 用户名
     * @return 是否提交成功
     * */
    public boolean closeByMarkerName(String username){
        for (MarkerInfoModel markerInfo: markers){
            if(markerInfo.getUsername().equals(username)){
                markerInfo.setCurrentImg(false);
                this.update();
            }
        }
        return true;
    }

    /**
     * tbt
     *判断当前图片是否被某个人标记
     *必须要有具体标记才能够算被标记过
     * @param username 用户名
     * @return 是否被该用户标记过
     * */
    public boolean markedByMarker(String username){
        if(marks==null){return false;}
        for (MarkModel mark: marks){
            if (mark.getUsername().equals(username)){return true;}
        }
        return false;
    }

}
