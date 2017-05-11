package com.sexample.emily.myapplication.ormlite.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;
/**
 * Created by emily on 17/4/25.
 */
@DatabaseTable(tableName="tb_Login")
public class Login {
    public final static String ID_FIELD_NAME = "User_id";
    @DatabaseField(id=true, canBeNull = false,columnName =ID_FIELD_NAME )
   private String UUuid;
    @DatabaseField
    private String UPwd;
    @DatabaseField
   private String UAName;
    @DatabaseField
   private String UTel;
    @DatabaseField
   private String UMail;


    public void RegistWithFlag(String UPwd, String registName,String flag) {
        this.UUuid = UUID.randomUUID().toString();
        this.UPwd = UPwd;
        if (flag.equals("A")) {
            this.UAName = registName;
        }else if(flag.equals("T")){
            this.UTel = registName;
        }else if (flag.equals("M")){
            this.UMail = registName;
        }
    }

    public Login() {}

    //getter & setter
    public String getUUuid() {
        return UUuid;
    }

    public void setUUuid(String UUuid) {
        this.UUuid = UUuid;
    }

    public String getUPwd() {
        return UPwd;
    }

    public void setUPwd(String UPwd) {
        this.UPwd = UPwd;
    }

    public String getUAName() {
        return UAName;
    }

    public void setUAName(String UAName) {
        this.UAName = UAName;
    }

    public String getUTel() {
        return UTel;
    }

    public void setUTel(String UTel) {
        this.UTel = UTel;
    }

    public String getUMail() {
        return UMail;
    }

    public void setUMail(String UMail) {
        this.UMail = UMail;
    }


}
