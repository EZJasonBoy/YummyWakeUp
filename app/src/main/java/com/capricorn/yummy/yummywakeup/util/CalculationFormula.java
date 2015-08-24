package com.capricorn.yummy.yummywakeup.util;

import java.util.Random;

/**
 * Created by chuandong on 15/8/23.
 */
public class CalculationFormula {

    // Uses int to present math operations
    public final static int PLUS_SIGN = 101;
    public final static int MINUS_SIGN = 102;

    /**
     * Random int between 0 and max
     * @param max
     * @return
     */
    public static int getRandomInt(int max) {
        Random rand = new Random(max);
        return rand.nextInt();
    }

    /**
     * Random int between 0 and 100
     * @return
     */
    public static int getRandomInt() {
        Random rand = new Random(100);
        return rand.nextInt();
    }

    /**
     * Random true or false
     * @return
     */
    public static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Random plus sign or minus sign
     * @return plus sign (101) or minus sign (102)
     */
    public static int getRandomOperation() {
        return getRandomBoolean()? PLUS_SIGN:MINUS_SIGN;
    }

    /**
     * Generate formula according to difficult level
     * @param difficultLevel
     * @return formula in int[]
     */
    public static int[] generateFormula(int difficultLevel) {
        int[] formula = null;
        switch (difficultLevel) {
            case 1:
                formula = initFormula(5);
                break;
            case 2:
                formula = initFormula(7);
                break;
            // ToDo more difficultLevel if needed
            default:
                break;
        }
        return formula;
    }

    /**
     * Calculates formula's result
     * @param formula
     * @return result
     */
    public static int getFormulaResult(int[] formula) {
        int result = 0;
        int previous = 0;
        for (int i = 0; i < formula.length; i++) {
            if(isEvenOrOdd(i)) {
                previous = formula[i];
            }else{
                if(formula[i] == PLUS_SIGN) {
                    result += previous;
                }else{
                    result -= previous;
                }
            }
        }
        return result;
    }

    /**
     * Gets the String format of formula
     * @param formula
     * @return
     */
    public static String getFormulaString(int[] formula) {
        String formulaString = "";
        for (int i = 0; i < formula.length; i++) {
            if(isEvenOrOdd(i)) {
                formulaString += String.valueOf(formula[i]);
            }else{
                if(formula[i] == PLUS_SIGN) {
                    formulaString += " + ";
                }else{
                    formulaString += " - ";
                }
            }
        }
        return formulaString;
    }

    /**
     * Init formula with the size given
     * @param size  3+2 -> size 3
     * @return
     */
    private static int[] initFormula(int size) {
        int[] formula = new int[size];
        for (int i = 0; i < formula.length; i++) {
            if(isEvenOrOdd(i)) {
                formula[i] = getRandomInt();
            }else{
                formula[i] = getRandomOperation();
            }
        }
        return formula;
    }

    /**
     * Checks Even or Odd
     * @param i
     * @return true if i is Even, false if Odd
     */
    public static boolean isEvenOrOdd(int i) {
        return (i%2 == 0)? true:false;
    }

}