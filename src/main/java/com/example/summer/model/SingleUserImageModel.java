package com.example.summer.model;

import com.example.summer.dao.ImageDao;
import com.example.summer.daoImpl.ImageDaoImpl;
import com.example.summer.domain.ImagePO;
import com.example.summer.domain.MarkPO;
import com.example.summer.domain.MarkerInfoPO;
import com.example.summer.utility.convertorUtility.SingleUserImageListModelPOConvertor;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.json.JSONObject;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SingleUserImageModel implements Model{

    @JsonIgnore
    private static ImageDao imageDao = new ImageDaoImpl();

    //id
    private String imageId;
    //标记者们
    private MarkerInfoModel marker;
    //图片标记们
    private MarkModel mark;
    //对应项目id
    private String projectId;
    //图片名称
    private String fileName;
    //是train还是test
    private String split;

    /***************************************************************************
     *                                                                         *
     * static methods                                                     *
     *                                                                         *
     *************************************************************************/
    public static  ArrayList<SingleUserImageModel> querySingleUserImagesByUserIdAndProjectId(String projectId, String userId, int begin, int num){
        ImageDao imageDao = new ImageDaoImpl();
        ArrayList<ImagePO> imagePOs = imageDao.listCurrentImagesByProjectIdAndUserId(projectId, userId, begin, num);
        return SingleUserImageListModelPOConvertor.poToModel(imagePOs, userId);
    }
    public static  ArrayList<SingleUserImageModel> querySingleUserImagesByUserIdAndProjectId(String projectId, String userId){
        ImageDao imageDao = new ImageDaoImpl();
        ArrayList<ImagePO> imagePOs = imageDao.listImagesByProjectIdAndUserId(projectId, userId);
        return SingleUserImageListModelPOConvertor.poToModel(imagePOs, userId);
    }
   public static SingleUserImageModel querySingleUserImageByUserIdAndImageId(String imageId,String userId){
        ImagePO po=imageDao.queryImageByImageId(imageId);
        SingleUserImageModel model=new SingleUserImageModel(po,userId);
        return model;

   }
    /***************************************************************************
     *                                                                         *
     * Constructor                                                     *
     *                                                                         *
     *************************************************************************/

    public SingleUserImageModel(ImagePO po, String username){
        this.fileName = po.getFileName();
        this.imageId = po.getImageId();
        this.projectId = po.getProjectId();
        this.split = po.getSplit();

        if (po.getMarkers()==null){po.setMarkers(new ArrayList<>());}
        for (MarkerInfoPO markerInfoPO : po.getMarkers()){
            if(markerInfoPO.getUsername().equals(username)){
                this.marker = DozerMapSingleton.getInstance().map(markerInfoPO ,MarkerInfoModel.class);
                break;
            }
        }

        if(po.getMarks()==null){po.setMarks(new ArrayList<>());}
        for (MarkPO markPO: po.getMarks()){
            if(markPO!=null&&markPO.getUsername().equals(username)){
                this.mark = DozerMapSingleton.getInstance().map(markPO, MarkModel.class);
            }
        }
    }



    public boolean update(){
        if (this.getMark()!=null){this.getMark().setUsername(this.getMarker().getUsername());}//填写mark中的用户信息

        ImagePO po = imageDao.queryImageByImageId(this.imageId);
        if (po==null){System.out.println("查询出错:没有该图片"); return  false;}
        if(po.getMarks()==null){ po.setMarks(new ArrayList<>()); }
        MarkPO newMarkPO = DozerMapSingleton.getInstance().map(this.mark, MarkPO.class);
        boolean isMarkedByUserBefore = false;
        for (int i=0; i<po.getMarks().size(); i++){
            MarkPO markPO = po.getMarks().get(i);
            if(markPO.getUsername().equals(this.getMark().getUsername())){
                po.getMarks().set(i,newMarkPO);
                isMarkedByUserBefore = true;
            }
        }

        if(!isMarkedByUserBefore){po.getMarks().add(newMarkPO);}

        System.out.println(JSONObject.fromObject(po));
        return imageDao.updateImage(po);
    }

}
