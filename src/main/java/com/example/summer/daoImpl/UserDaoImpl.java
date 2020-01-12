package com.example.summer.daoImpl;

import com.example.summer.configInfo.DataAbsolutePath;
import com.example.summer.dao.UserDao;
import com.example.summer.domain.PO;
import com.example.summer.domain.UserPO;
import com.example.summer.domain.EmailPO;
import com.example.summer.utility.dataUtility.ReadAndWritePOsUtility;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserDaoImpl implements UserDao{
    private static final String LAST_RILE_PATH= DataAbsolutePath.DATA_PATH +"User/";
    @Override
    public boolean isValidUser(String logStr, String password) {
        //user can use username or emailaddress as logStr
        ArrayList<PO> pos=ReadAndWritePOsUtility.readPOs(LAST_RILE_PATH);
        boolean isValid=false;
        for(int i=0;i<=pos.size()-1;i++){
                PO po=pos.get(i);
                if(po instanceof UserPO){
                    UserPO userPo=(UserPO)po;
                    String name=userPo.getUsername();
                    EmailPO emailPO=userPo.getEmail();
                    String emailAddress=emailPO.getAddress();
                    String passWord=userPo.getPassword();
                    if((name.equals(logStr)||emailAddress.equals(logStr))&&(passWord.equals(password))){
                        isValid=true;
                        break;
                    }
                }else{
                    return false;
                    //this means the file path is false so the content of the file is not of class UserPO
                    //it will not happen as normal
                }
            }
        return isValid;
    }

    /**
     * 是否存在该用户
     *
     * @param username 用户名
     */
    @Override
    public boolean isExistedUserByUserName(String username) {
        ArrayList<PO> pos=ReadAndWritePOsUtility.readPOs(LAST_RILE_PATH);
        boolean exist=false;
        for(int i=0;i<=pos.size()-1;i++){
                PO po=pos.get(i);
                if(po instanceof UserPO){
                    UserPO userPo=(UserPO)po;
                    String name=userPo.getUsername();
                    if(name.equals(username)){
                        exist=true;
                        break;
                    }
                }else{
                    return false;
                    //this means the file path is false so the content of the file is not of class UserPO
                    //it will not happen as normal
                }
            }
        return exist;
    }

    /**
     * 是否存在该用户
     *
     * @param emailAddress 邮箱
     */
    @Override
    public boolean isExistedUserByEmail(String emailAddress) {
        ArrayList<PO> pos=ReadAndWritePOsUtility.readPOs(LAST_RILE_PATH);
        boolean exist=false;
        for(int i=0;i<=pos.size()-1;i++){
                PO po=pos.get(i);
                if(po instanceof UserPO){
                    UserPO userPo=(UserPO)po;
                    EmailPO emailPO=userPo.getEmail();
                    String emailAdd=emailPO.getAddress();
                    if(emailAdd.equals(emailAddress)){
                        exist=true;
                        break;
                    }
                }else{
                    return false;
                    //this means the file path is false so the content of the file is not of class UserPO
                    //it will not happen as normal
                }
        }
        return exist;
    }

    @Override
    public boolean register(UserPO userPO) {
        if(isExistedUserByUserName(userPO.getUsername())){
            return false;
        }
        ArrayList<PO> pos= ReadAndWritePOsUtility.readPOs(LAST_RILE_PATH);
        ArrayList<UserPO> userPOs=new ArrayList<UserPO>();
        for(int i=0;i<=pos.size()-1;i++){
                PO po=pos.get(i);
                if(po instanceof UserPO){
                    UserPO userPo=(UserPO)po;
                    userPOs.add(userPo);
                }else{
                    return false;
                    //this means the file path is false so the content of the file is not of class UserPO
                    //it will not happen as normal
                }
        }
        userPOs.add(userPO);
        ReadAndWritePOsUtility.writePOs(userPOs,LAST_RILE_PATH);
        return true;
    }

    @Override
    public UserPO queryUserByUsername(String username) {
        ArrayList<PO> pos=ReadAndWritePOsUtility.readPOs(LAST_RILE_PATH);
        UserPO userPO=null;
        for(int i=0;i<=pos.size()-1;i++){
                PO po=pos.get(i);
                if(po instanceof UserPO){
                    UserPO userPo=(UserPO)po;
                    String name=userPo.getUsername();
                    if(name.equals(username)){
                        userPO=userPo;
                        break;
                    }
                }else{
                    return null;
                    //this means the file path is false so the content of the file is not of class UserPO
                    //it will not happen as normal
                }
            }
        return userPO;
    }

    @Override
    public ArrayList<UserPO> listAllUsers() {
        ArrayList<PO> pos= ReadAndWritePOsUtility.readPOs(LAST_RILE_PATH);
        ArrayList<UserPO> userPOs=new ArrayList<UserPO>();
        for(int i=0;i<=pos.size()-1;i++){
            PO po=pos.get(i);
            if(po instanceof UserPO){
                UserPO userPo=(UserPO)po;
                userPOs.add(userPo);
            }else{
                return null;
                //this means the file path is false so the content of the file is not of class UserPO
                //it will not happen as normal
            }
        }
        return userPOs;
    }
}
