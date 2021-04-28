package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {
    int gridSize;
    Cell currentCell;
    int totalCell;
    int visitedCell;
    Cell grid [][] = new Cell[gridSize][gridSize];
    Stack<Cell> cells = new Stack<>();

    public Maze(int gridSize) {
        this.gridSize = gridSize;
    }

    public void createMaze(){
        totalCell = gridSize*gridSize;
        currentCell = grid[0][0];
        visitedCell = 1;

        while(visitedCell < totalCell){
            ArrayList<Cell> neighbors = findNeighbors(currentCell);
            Random rand = new Random();
            if(neighbors.size() >= 1){
                int i = rand.nextInt(neighbors.size());
                Cell cell = neighbors.get(i);
                currentCell.removeWall(cell);
                cells.push(currentCell);
                currentCell = cell;
                visitedCell++;
            }
        }
    }


    public ArrayList<Cell> findNeighbors(Cell c){
        ArrayList<Cell> walls = new ArrayList<>();

        if(c.getX()-1 >= 0 && grid[c.getX() - 1][c.getY()].allWallsIntact())
            walls.add(grid[c.getX()-1][c.getY()]);
        if(c.getY()-1 >= 0 && grid[c.getX()][c.getY()-1].allWallsIntact())
            walls.add(grid[c.getX()][c.getY()-1]);
        if(c.getX()+1 < gridSize && grid[c.getX() + 1][c.getY()].allWallsIntact())
            walls.add(grid[c.getX()+1][c.getY()]);
        if(c.getY()+1 < gridSize && grid[c.getX()][c.getY()+1].allWallsIntact())
            walls.add(grid[c.getX()][c.getY()+1]);
        return walls;
    }


}
