package com.sexample.emily.myapplication.ormlite.Bean;

import org.threeten.bp.LocalDate;

/***
 * PackageName com.weiCommity.Model
 * 软件信箱类
 * 附加的附件类为 MessageAttach
 * Created by uryuo on 17/5/1.
 */

public class MessageBox {
    private String MId;
    private LocalDate MCreateTime;
    private String MSenderId;
    private String MTarId;
    private String MTitle;
    private String MThings;
    private int MIsReaded = 0;
    private int MImportant = 0;
    private int MCheck = -2;
    private String MSpcType;

    public String getMId() {
        return MId;
    }

    public void setMId(String MId) {
        this.MId = MId;
    }

    public LocalDate getMCreateTime() {
        return MCreateTime;
    }

    public void setMCreateTime(LocalDate MCreateTime) {
        this.MCreateTime = MCreateTime;
    }

    public String getMSenderId() {
        return MSenderId;
    }

    public void setMSenderId(String MSenderId) {
        this.MSenderId = MSenderId;
    }

    public String getMTarId() {
        return MTarId;
    }

    public void setMTarId(String MTarId) {
        this.MTarId = MTarId;
    }

    public String getMTitle() {
        return MTitle;
    }

    public void setMTitle(String MTitle) {
        this.MTitle = MTitle;
    }

    public String getMThings() {
        return MThings;
    }

    public void setMThings(String MThings) {
        this.MThings = MThings;
    }

    public int getMIsReaded() {
        return MIsReaded;
    }

    public void setMIsReaded(int MIsReaded) {
        this.MIsReaded = MIsReaded;
    }

    public int getMImportant() {
        return MImportant;
    }

    public void setMImportant(int MImportant) {
        this.MImportant = MImportant;
    }

    public int getMCheck() {
        return MCheck;
    }

    public void setMCheck(int MCheck) {
        this.MCheck = MCheck;
    }

    public String getMSpcType() {
        return MSpcType;
    }

    public void setMSpcType(String MSpcType) {
        this.MSpcType = MSpcType;
    }
}
