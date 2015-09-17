package com.capricorn.yummy.yummywakeup.unlockTypeModule.model;

/**
 * Created by chuandong on 15/9/17.
 */
public enum UnlockType {

    Normal ("Normal"),
    Calculation ("Calculation"),
    Puzzle ("Puzzle"),
    Shake ("Shake");

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

    public int value() { return valueInt(type);}

    private int valueInt(String s) {
        if(s == Normal.toString()) {
            return 0;
        } else if(s == Calculation.toString()) {
            return 1;
        } else if(s == Puzzle.toString()) {
            return 2;
        } else if(s == Shake.toString()) {
            return 3;
        }
        return -1;
    }

    public static String valueString(int i) {
        switch (i) {
            case 0:
                return Normal.toString();
            case 1:
                return Calculation.toString();
            case 2:
                return Puzzle.toString();
            case 3:
                return Shake.toString();
        }
        return null;
    }
}
