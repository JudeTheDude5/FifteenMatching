package com.example.fifteenmatching;

public class FifteenModel {
    public static final int BOX_SIZE = 100;

    public float x = 10;

    public float y = 10;

    public int boxValue = 1;

    public FifteenModel(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setCoords(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
