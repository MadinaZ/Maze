package com.company;

import java.util.ArrayList;

enum Color{
    WHITE, GRAY, BLACK;
}

public class Cell {
    //coordinates
     int x;
     int y;
    //directions
    public boolean east, west, south, north, isVisited;
    ArrayList<Cell> neighbors = new ArrayList<>();
    //for DFS
    int discoveryTime;
    int finishingTime;
    Color color;

    //for BFS
    int distance;
    int numberOfVisits;
    Cell predecessor;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        color = Color.WHITE;
    }

//    public int getX() { return x; }
//
//    public int getY() { return y; }

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
        //west
        if(cell.x == this.x && cell.y == this.y-1){
            west =false;
            cell.east = false;
            neighbors.add(cell);
            cell.neighbors.add(this);
        }
        //east
        else if(cell.x == x && cell.y == y + 1){
            east = false;
            cell.east = false;
            neighbors.add(cell);
            cell.neighbors.add(this);
        }
        //north
        else if(cell.x == x-1 && cell.y == y){
            north = false;
            cell.north = false;
            neighbors.add(cell);
            cell.neighbors.add(this);
        }
        //south
        else if(cell.x == x+1 && cell.y == y+1){
            south = false;
            cell.south = false;
            neighbors.add(cell);
            cell.neighbors.add(this);
        }

    }
}
