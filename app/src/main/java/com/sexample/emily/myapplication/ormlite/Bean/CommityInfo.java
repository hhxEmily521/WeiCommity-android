package com.sexample.emily.myapplication.ormlite.Bean;

import org.threeten.bp.LocalDate;
/**
 * Created by uryuo on 17/5/1.
 */

public class CommityInfo {
    private String Cid;
    private String CNAame;
    private LocalDate CCreateTime;
    private String Cintroduce;
    private String CTag;
    private String CHeadImg;
    private int CIsNMin;
    private String CNMDemand;
    private int CMeMCount;
    private String CNotice;

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getCNAame() {
        return CNAame;
    }

    public void setCNAame(String CNAame) {
        this.CNAame = CNAame;
    }

    public LocalDate getCCreateTime() {
        return CCreateTime;
    }

    public void setCCreateTime(LocalDate CCreateTime) {
        this.CCreateTime = CCreateTime;
    }

    public String getCintroduce() {
        return Cintroduce;
    }

    public void setCintroduce(String cintroduce) {
        Cintroduce = cintroduce;
    }

    public String getCTag() {
        return CTag;
    }

    public void setCTag(String CTag) {
        this.CTag = CTag;
    }

    public String getCHeadImg() {
        return CHeadImg;
    }

    public void setCHeadImg(String CHeadImg) {
        this.CHeadImg = CHeadImg;
    }

    public int getCIsNMin() {
        return CIsNMin;
    }

    public void setCIsNMin(int CIsNMin) {
        this.CIsNMin = CIsNMin;
    }

    public String getCNMDemand() {
        return CNMDemand;
    }

    public void setCNMDemand(String CNMDemand) {
        this.CNMDemand = CNMDemand;
    }

    public int getCMeMCount() {
        return CMeMCount;
    }

    public void setCMeMCount(int CMeMCount) {
        this.CMeMCount = CMeMCount;
    }

    public String getCNotice() {
        return CNotice;
    }

    public void setCNotice(String CNotice) {
        this.CNotice = CNotice;
    }
}
