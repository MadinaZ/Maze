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

    public boolean allWallsIntact(){
        return neighbors.size() == 0;
    }

    public void setVisited(){ isVisited = true;}

    public boolean isVisited(){return  isVisited;}

    /*
    cell to the left maze:[row][col-1]
    cell to the right maze:[row][col+1]
    cell above maze:[row-1][col]
    cell below maze:[row+1][col]
     */

    public void removeWall(Cell cell){
        if(cell.getX() == getX() && cell.getY() == getY()-1){

        }

        else if(cell.getX() == getX() && cell.getY() == getY()+1){

        }

        else if(cell.getX() == getX()-1 && cell.getY() == getY()){

        }

        else if(cell.getX() == getX()+1 && cell.getY() == getY()+1){

        }

    }
}
