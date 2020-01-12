package com.example.summer.utility.dataUtility;
import com.example.summer.domain.PO;

import java.io.*;
import java.util.ArrayList;

public class ReadAndWritePOsUtility {

    public static boolean writePos(ArrayList<? extends PO> pos,String path){
        path+=File.separator;
        ObjectOutputStream oStream = null;
//		System.out.println(path);
        try {
            for(PO po: pos){
                oStream = new ObjectOutputStream(new FileOutputStream(path+po.getID()));
                oStream.writeObject(po);
                //oStream.close();
            }
            oStream.close();
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
    }

    public static void writePOs(ArrayList<? extends PO> pos,String lastFilePath){
        if((pos!=null)&&(pos.size()!=0)&&(lastFilePath!=null)){
            File lastFile = new File(lastFilePath);
            lastFile.mkdirs();
            File[] fileList = lastFile.listFiles();
            if (fileList.length != 0) {
                for (int i = 0; i <= fileList.length - 1; i++) {
                    fileList[i].delete();
                }
                //delete all first and then insert all
            }
            String lastFileAbsolutePath = lastFile.getAbsolutePath();
            for (int i = 0; i <= pos.size() - 1; i++) {
                PO po = pos.get(i);
                File singleFile = new File(lastFileAbsolutePath + "/" + po.getID());
                try {
                    singleFile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(singleFile);
                    ObjectOutputStream oos = new ObjectOutputStream((fos));
                    oos.writeObject(po);
                    oos.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
        }
    }

    public static ArrayList<PO> readPOs(String lastFilePath){
        //System.out.println(lastFilePath);
        ArrayList<PO> pos=new ArrayList<PO>();
        if(lastFilePath!=null){
            File lastFile = new File(lastFilePath);
            lastFile.mkdirs();
            File[] fileList = lastFile.listFiles();
            if (fileList.length != 0) {
                try{
                    for (int i = 0; i <= fileList.length - 1; i++) {
                        File singleFile = fileList[i];
                        FileInputStream fis = new FileInputStream(singleFile);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        PO po = (PO) ois.readObject();
                        //remember to use instanceof operator to confirm if 'po' is the instance of target class in the concrete class function
                        // and then turn po into target class without error
                        pos.add(po);
                        ois.close();
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return pos;
    }

    public static PO readPO(String path){
        PO po = null;
        File file = new File(path);
        if(!file.exists()){return null;}
        ObjectInputStream oInputStream;
        try {
            oInputStream = new ObjectInputStream(new FileInputStream(path));
            po = (PO) oInputStream.readObject();
            oInputStream.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            return null;
        }
        return po;
    }
}