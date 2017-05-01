package com.sexample.emily.myapplication.ormlite.Bean;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.dao.ForeignCollection;

import java.sql.Timestamp;

/**
 * Created by emily on 17/4/19.
 */

//public class UserTFWork {
//
//@DatabaseTable
public class UserTFWork {
    @DatabaseField(id=true,  canBeNull = false)
    private String UWid;
    @DatabaseField(canBeNull = false)
    private String UUuid;
    @DatabaseField(canBeNull = false)
    private String WorkId;
    private String WorkFC;//第一个种类音乐和美工
    private String WorkSC;//歌手词作
    private int isFreq;
    private Timestamp delayTime;//不接新时间
    private int status;//是否接新状态



    //getter and setter
    public String getUWid() {
        return UWid;
    }

    public void setUWid(String UWid) {
        this.UWid = UWid;
    }

    public String getUUuid() {
        return UUuid;
    }

    public void setUUuid(String UUuid) {
        this.UUuid = UUuid;
    }


    public String getWorkId() {
        return WorkId;
    }

    public void setWorkId(String workId) {
        WorkId = workId;
    }

    public String getWorkFC() {
        return WorkFC;
    }

    public void setWorkFC(String workFC) {
        WorkFC = workFC;
    }

    public String getWorkSC() {
        return WorkSC;
    }

    public void setWorkSC(String workSC) {
        WorkSC = workSC;
    }

    public int getIsFreq() {
        return isFreq;
    }

    public void setIsFreq(int isFreq) {
        this.isFreq = isFreq;
    }

    public Timestamp getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Timestamp delayTime) {
        this.delayTime = delayTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
