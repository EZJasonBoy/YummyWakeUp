package com.capricorn.yummy.yummywakeup.unlockTypeModule.model;

/**
 * Created by chuandong on 15/9/17.
 */
public enum UnlockType {

    type1 ("Normal"),
    type2 ("Calculation"),
    type3 ("Puzzle"),
    type4 ("Shake");

    private final String type;

    private UnlockType(String s) {
        type = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : type.equals(otherName);
    }

    public String toString() {
        return this.type;
    }

    public static int valueInt(String s) {
        if(s == type1.toString()) {
            return 0;
        } else if(s == type2.toString()) {
            return 1;
        } else if(s == type3.toString()) {
            return 2;
        } else if(s == type4.toString()) {
            return 3;
        }
        return -1;
    }

    public static String valueString(int i) {
        switch (i) {
            case 0:
                return type1.toString();
            case 1:
                return type2.toString();
            case 2:
                return type3.toString();
            case 3:
                return type4.toString();
        }
        return null;
    }
}
