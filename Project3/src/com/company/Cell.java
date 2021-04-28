package com.company;

public class Cell {
    private int number;
    private int x;
    private int y;
    private boolean east, west, south, north;


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getNumber() {
        return number;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEast() {
//        if(east)
        return east;
    }

    public boolean isWest() {
        return west;
    }

    public boolean isSouth() {
        return south;
    }

    public boolean isNorth() {
        return north;
    }
}
