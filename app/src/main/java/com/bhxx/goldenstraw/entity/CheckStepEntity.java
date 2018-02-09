package com.bhxx.goldenstraw.entity;

/**
 * 判断审核到第几步
 */
public class CheckStepEntity {
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    private int step;

    public CheckStepEntity(int step) {
        super();
        this.step = step;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }


}
