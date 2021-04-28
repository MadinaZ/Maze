package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class Maze {
    int gridSize;
    Cell currentCell;
    int totalCell;
    int visitedCell;
    Cell grid [][] = new Cell[gridSize][gridSize];
    Stack<Cell> cells = new Stack<>();

    ArrayList<Cell> walls = new ArrayList<>();
    public Maze(int gridSize) {
        this.gridSize = gridSize;
    }

    public void createMaze(){
        totalCell = gridSize*gridSize;
        currentCell = grid[0][0];
        visitedCell = 1;

        while(visitedCell < totalCell){
            if()
        }
    }


    public void findNeighbors(Cell c){
        if(c.getX()-1 >= 0 && grid[c.getX() - 1][c.getY()].allWallsIntact() && !grid[c.getX()-1][c.getY()].isVisited())
            walls.add(grid[c.getX()-1][c.getY()]);
        if(c.getY()-1 >= 0 && grid[c.getX()][c.getY()-1].allWallsIntact() && !grid[c.getX()][c.getY()-1].isVisited())
            walls.add(grid[c.getX()][c.getY()-1]);
        if(c.getX()+1 < gridSize && grid[c.getX() + 1][c.getY()].allWallsIntact() && !grid[c.getX()+1][c.getY()].isVisited())
            walls.add(grid[c.getX()+1][c.getY()]);
        if(c.getY()+1 < gridSize && grid[c.getX()][c.getY()+1].allWallsIntact() && !grid[c.getX()][c.getY()+1].isVisited())
            walls.add(grid[c.getX()][c.getY()+1]);
    }


}
