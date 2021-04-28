package com.company;

import java.util.ArrayList;

public class Cell {
    private int number;
    private int x;
    private int y;
    private boolean east, west, south, north, isVisited;
    ArrayList<Cell> neighbors = new ArrayList<>();

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
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

    public boolean allWallsIntact(){
        return neighbors.size() == 0;
    }

    public void setVisited(){ isVisited = true;}

    public boolean isVisited(){return  isVisited;}
}
