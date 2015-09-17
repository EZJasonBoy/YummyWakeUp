package com.capricorn.yummy.yummywakeup.unlockTypeModule.model;

/**
 * Created by chuandong on 15/9/5.
 */
public class UnlockTypeItem {

    private String mTypeName; // Unlock Type Name
    private String mDiffLvl;
    private int mTypeImage;   // Image ID

    public UnlockTypeItem(String mTypeName, String mDiffLvl, int mTypeImage) {
        this.mTypeName = mTypeName;
        this.mDiffLvl = mDiffLvl;
        this.mTypeImage = mTypeImage;
    }

    public int getTypeImage() {
        return mTypeImage;
    }

    public void setTypeImage(int mTypeImage) {
        this.mTypeImage = mTypeImage;
    }

    public String getTypeName() {
        return mTypeName;
    }

    public void setTypeName(String mTypeName) {
        this.mTypeName = mTypeName;
    }

    public String getmDiffLvl() {
        return mDiffLvl;
    }

    public void setmDiffLvl(String mDiffLvl) {
        this.mDiffLvl = mDiffLvl;
    }
}
