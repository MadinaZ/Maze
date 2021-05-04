package com.company;

import java.awt.*;
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
    ArrayList<Cell> neighbors;
    //for DFS
    int discoveryTime;
    int finishingTime;
    Color color;

    //for BFS
    int distance;
    int numberOfVisits;
    Cell predecessor;

    public Cell(int x, int y) {
        neighbors = new ArrayList<>();
        this.x = x;
        this.y = y;
        color = Color.WHITE;
        east = west = north = south = true;
        isVisited = false;
    }

//    public int getX() { return x; }
//
//    public int getY() { return y; }

    public boolean allWallsIntact(){
        return neighbors.size() == 0;
    }

    public void setVisited() { isVisited = true;}

    public boolean isVisited() {return  isVisited;}

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        Cell other = (Cell) o;
        return other.x == x && other.y == y;
    }

    /*
    cell to the left maze:[x][y-1]
    cell to the right maze:[x][y+1]
    cell above maze:[x-1][y]
    cell below maze:[x+1][y]
     */
    public void removeWall(Cell cell){
        //west
        if(cell.x == x && cell.y == y - 1){
            west = false;
            cell.east = false;
            neighbors.add(cell);
            cell.neighbors.add(this);
        }
        //cell is east neighbor
        else if(cell.x == x && cell.y == y + 1){
            east = false;
            cell.west = false;
            neighbors.add(cell);
            cell.neighbors.add(this);
        }
        //cell is north neighbor
        else if(cell.y == y && cell.x == x - 1){
            north = false;
            cell.south = false;
            neighbors.add(cell);
            cell.neighbors.add(this);
        }
        //cell is south neighbor
        else if(cell.y == y && cell.x == x + 1){
            south = false;
            cell.north = false;
            neighbors.add(cell);
            cell.neighbors.add(this);
        }

    }
}
