package com.sexample.emily.myapplication.ViewModel;

import com.sexample.emily.myapplication.ormlite.Bean.CommityInfo;

/**
 * Created by Emily on 2017/5/9.
 */

public class CommityItemModel extends CommityInfo {
    private int UserPermit;

    public int getUserPermit() {
        return UserPermit;
    }

    public void setUserPermit(int userPermit) {
        UserPermit = userPermit;
    }
    public void setCommityItemModel(String CID ,String ImgUrl, String CName,String Introduce,int userPermit) {
       this.setCid(CID);
        this.setCHeadImg(ImgUrl);
        this.setCName(CName);
        this.setCintroduce(Introduce);
        UserPermit = userPermit;
    }
}
