package com.company;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BFS {
    int gridSize;
    Cell grid [][];
    ArrayList<Cell> bfsVisit = new ArrayList<>();
    Color color;
    int pathLength, cellsVisited;
    boolean added = false;
    boolean checkBfs;
    boolean BfsShortestPath;

    public BFS(int gridSize, Cell grid[][]) {
        this.gridSize = gridSize;
        this.grid = grid;
    }

    public void bfs(){
        for(int i = 0; i < gridSize; i++)
            for(int j = 0; j < gridSize; j++)
                grid[i][j].color = Color.WHITE;
        Queue<Cell> queue = new LinkedList<>();

        int visit = 0;
        Cell node = grid[0][0];
        node.distance = 0;
        node.color = Color.GRAY;
        node.numberOfVisits = visit;

        visit++;
        bfsVisit.add(node);
        queue.add(node);
        while(queue.size() != 0){
            node = queue.remove();
            if(node.x == gridSize - 1 && node.y == gridSize - 1)
                added  = true;
            for(Cell cell: node.neighbors)
                if(cell.color == Color.WHITE){
                    cell.color = Color.GRAY;
                    cell.numberOfVisits = visit;
                    visit ++;
                    cell.distance = node.distance + 1;
                    cell.predecessor = node;
                    if(cell.x == gridSize - 1 && cell.y == gridSize - 1)
                        added  = true;
                    if(!added )
                        bfsVisit.add(cell);
                    queue.add(cell);
                }

            node.color = Color.BLACK;
        }
        bfsVisit.add(grid[gridSize-1][gridSize-1]);
        cellsVisited = bfsVisit.size();
    }

    public String printBFSShortestPath(){
        bfsVisit = new ArrayList<Cell>();
        Cell node = grid[gridSize-1][gridSize-1];
        while(node != null){
            bfsVisit.add(node);
            node = node.predecessor;
        }
        pathLength= bfsVisit.size();
        BfsShortestPath = true;
        String s = displayBFS();
        BfsShortestPath = false;
        //store length of path for display later
        pathLength= bfsVisit.size();
        return s;
    }

    public String printBFS(){
        checkBfs = true;
        String s = displayBFS();
        checkBfs = false;
        return s;
    }

    public String displayBFS(){
        String[][] chars = new String[gridSize*2 + 1][gridSize*2 + 1];
        String s = "";
        for(int i = 0; i < chars.length; i ++){
            for(int j = 0; j < chars.length; j ++){
                Cell node = null;
                if(i % 2 == 1 && j % 2 == 1)
                    node = grid[i /2][j/2];
                if((i == 0 && j == 1) || (i == gridSize*2 && j == gridSize*2 - 1))
                    chars[i][j] = " ";
                else if(chars[i][j] == null && i % 2 == 0 && j % 2 == 0)
                    chars[i][j] = "+";
                else if(i % 2 == 1 && (j == 0 || j == chars[0].length - 1))
                    chars[i][j] = "|";
                else if(i == 0 || i == chars.length - 1)
                    chars[i][j] = "-";
                else if(node != null){
                    if(node.west)
                        chars[i][j - 1] = "|";
                    else
                        chars[i][j - 1] = " ";
                    if(node.east)
                        chars[i][j + 1] = "|";
                    else
                        chars[i][j + 1] = " ";
                    if(node.north)
                        chars[i - 1][j] = "-";
                    if(node.south)
                        chars[i + 1][j] = "-";
                    int visit = node.numberOfVisits;

                    if(checkBfs && visit < 10 && bfsVisit.contains(node))
                        chars[i][j] = Integer.toString(visit);
                    else if(checkBfs && (visit >= 10 && visit < 19) && bfsVisit.contains(node)){
                        visit = visit - 9;
                        chars[i][j] = Integer.toString(visit);
                    }
                    else if(checkBfs && (visit >= 19 && visit < 29) && bfsVisit.contains(node)){
                        visit = visit - 19;
                        chars[i][j] = Integer.toString(visit);
                    }
                    else if(checkBfs && (visit >= 29 && visit < 39) && bfsVisit.contains(node)){
                        visit = visit - 29;
                        chars[i][j] = Integer.toString(visit);
                    }
                    else if(BfsShortestPath  && bfsVisit.contains(node))
                        chars[i][j] = "#";
                    else
                        chars[i][j] = " ";
                }
                else
                    chars[i][j] = " ";
            }
        }
        for(int i = 0; i < chars.length; i ++){
            for(int j = 0; j < chars.length; j ++)
                s += chars[i][j];
            s += "\n";
        }
        return s;
    }

    //experimental getters
    public ArrayList<Cell> getBfsVisit(){
        return bfsVisit;
    }
    public int getPathLength(){
        return pathLength;
    }
    public int getCellsVisited(){
        return cellsVisited;
    }

}
