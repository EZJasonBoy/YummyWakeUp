package com.capricorn.yummy.yummywakeup.unlockTypeModule.model;

/**
 * Created by chuandong on 15/9/5.
 */
public class UnlockType {

    private String mTypeName; // Unlock Type Name
    private int mTypeImage;   // Image ID

    public UnlockType(String mTypeName, int mTypeImage) {
        this.mTypeName = mTypeName;
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
}
