package com.capricorn.yummy.yummywakeup.unlockTypeModule.model;

/**
 * Created by chuandong on 15/9/10.
 */
public enum UnlockDiffcultLevel {

    level1 ("Easy"),
    level2 ("Normal"),
    level3 ("Hard");

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
        if(s == level1.toString()) {
            return 0;
        } else if(s == level2.toString()) {
            return 1;
        } else if(s == level3.toString()) {
            return 2;
        }
        return -1;
    }

    public static String valueString(int i) {
        switch (i) {
            case 0:
                return level1.toString();
            case 1:
                return level2.toString();
            case 2:
                return level3.toString();
        }
        return null;
    }

}
