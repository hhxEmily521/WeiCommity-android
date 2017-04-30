package com.sexample.emily.myapplication.ormlite.Bean;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.threeten.bp.LocalDate;



/**
 * Created by emily on 17/4/22.
 */
@DatabaseTable
public class UserExtend {
    @DatabaseField(columnName = "UUuid")
    private String UUuid;
    @DatabaseField(columnName = "UUuid")
    private String UNackName;


    @DatabaseField(columnName = "UUuid")
    private String USex;
    @DatabaseField(columnName = "UUuid")
    private LocalDate UBirthday;
    @DatabaseField(columnName = "UUuid")
    private String UTag;
    @DatabaseField(columnName = "UUuid")
    private String USign;
    @DatabaseField(columnName = "UUuid")
    private String UHeadImg;
    //外部类，外键。以主键关联
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    public Login user;

    public Login getUser() {
        return user;
    }

    public void setUser(Login user) {
        this.user = user;
    }

    public String getUUuid() {
        return UUuid;
    }

    public void setUUuid(String UUuid) {
        this.UUuid = UUuid;
    }

    public String getUNackName() {
        return UNackName;
    }

    public void setUNackName(String UNackName) {
        this.UNackName = UNackName;
    }

    public String getUSex() {
        return USex;
    }

    public void setUSex(String USex) {
        this.USex = USex;
    }
    public void setUBirthday(LocalDate UBirthday) {
        this.UBirthday = UBirthday;
    }


    public LocalDate getUBirthday() {
        return UBirthday;
    }

    public void setUBirthday(String UBirthday) {
        String [] re = UBirthday.split("/");
        int []intDate = new int[3];
        for (int i=0;i<re.length;i++){
            intDate[i] = Integer.parseInt(re[i]);
        }

        LocalDate thisBirthday = LocalDate.of(intDate[0],intDate[1],intDate[2]);
        this.UBirthday = thisBirthday;
    }

    public String getUTag() {
        return UTag;
    }

    public void setUTag(String UTag) {
        this.UTag = UTag;
    }

    public String getUSign() {
        return USign;
    }

    public void setUSign(String USign) {
        this.USign = USign;
    }

    public String getUHeadImg() {
        return UHeadImg;
    }

    public void setUHeadImg(String UHeadImg) {
        this.UHeadImg = UHeadImg;
    }

}
