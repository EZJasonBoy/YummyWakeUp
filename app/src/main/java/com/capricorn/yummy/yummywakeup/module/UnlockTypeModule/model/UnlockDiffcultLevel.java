package com.capricorn.yummy.yummywakeup.module.UnlockTypeModule.model;

/**
 * Created by chuandong on 15/9/10.
 */
public enum UnlockDiffcultLevel {

    EASY ("Easy"),
    NORMAL ("Normal"),
    HARD ("Hard");

    private final String lvl;

    private UnlockDiffcultLevel(String s) {
        lvl = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : lvl.equals(otherName);
    }

    public String toString() {
        return this.lvl;
    }

    public static int valueInt(String s) {
        if(s == EASY.toString()) {
            return 0;
        } else if(s == NORMAL.toString()) {
            return 1;
        } else if(s == HARD.toString()) {
            return 2;
        }
        return -1;
    }

    public static String valueString(int i) {
        switch (i) {
            case 0:
                return EASY.toString();
            case 1:
                return NORMAL.toString();
            case 2:
                return HARD.toString();
        }
        return null;
    }

}
