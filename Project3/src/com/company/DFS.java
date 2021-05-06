package com.company;

import java.awt.*;
import java.util.ArrayList;


public class DFS {
    int gridSize;
    Cell grid [][];
    ArrayList<Cell> dfsVisit;
    Color color;
    private int time;
    boolean added = false;
    int pathLength, cellsVisited;
    boolean check;
    boolean DfsShortestPath;

    public DFS(int gridSize, Cell grid[][]) {
        this.gridSize = gridSize;
        this.grid = grid;
        check = false;
        dfsVisit = new ArrayList<>();
        DfsShortestPath = false;
    }

    public void Dfs(){
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                grid[i][j].color = Color.WHITE;
        time = 0;
        for(Cell[] arr: grid)
            for(Cell node: arr)
                if(node.color == Color.WHITE)
                    DfsVisit(node);

        added = false;
    }

    public void DfsVisit(Cell current) {
        if(current.equals(new Cell(gridSize - 1, gridSize - 1))){
            added = true;
            dfsVisit.add(current);
        }
        if(!added) {
            dfsVisit.add(current);
        }
        current.color = Color.GRAY;
        time = time + 1;
        current.discoveryTime = time;
        for(Cell cell: current.neighbors)
            if(cell.color == Color.WHITE)
                DfsVisit(cell);

        current.color = Color.BLACK;
        time = time + 1;
        current.finishingTime = time;
        cellsVisited = dfsVisit.size();
    }

    public String printDFSShortestPath(){
        DfsShortestPath = true;
        for(int x = 0; x < dfsVisit.size() - 2; x++)
            if(dfsVisit.get(x).finishingTime < dfsVisit.get(x+1).finishingTime){
                dfsVisit.remove(x);
            }
        String s = displayMaze();
        DfsShortestPath = false;
        pathLength = dfsVisit.size();
        return s;
    }

    public String printDFS(){
        check = true;
        String s = displayMaze();
        check = false;
        return s;
    }

    public String displayMaze(){
        String[][] chars = new String[gridSize*2 + 1][gridSize*2 + 1];
        String s = "";
        for(int i = 0; i < chars.length; i ++){
            for(int j = 0; j < chars.length; j ++){
                //node is null, if no node exists
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

                    Integer visit = node.discoveryTime;

                    if(check && visit < 10 && dfsVisit.contains(node))
                        chars[i][j] = visit.toString();
                    else if(check && (visit >= 10 && visit < 19) && dfsVisit.contains(node)){
                        visit = visit - 9;
                        chars[i][j] = visit.toString();
                    }
                    else if(check && (visit >= 19 && visit < 29) && dfsVisit.contains(node)){
                        visit = visit - 19;
                        chars[i][j] = visit.toString();
                    }
                    else if(check && (visit >= 29 && visit < 39) && dfsVisit.contains(node)){
                        visit = visit - 29;
                        chars[i][j] = visit.toString();
                    }
                    else if(check && (visit >= 39 && visit < 49) && dfsVisit.contains(node)){
                        visit = visit - 39;
                        chars[i][j] = visit.toString();
                    }
                    else if(check && (visit >= 49 && visit < 59) && dfsVisit.contains(node)){
                        visit = visit - 49;
                        chars[i][j] = visit.toString();
                    }
                    else if(check && (visit >= 59 && visit < 69) && dfsVisit.contains(node)){
                        visit = visit - 59;
                        chars[i][j] = visit.toString();
                    }
                    else if(check && (visit >= 69 && visit < 79) && dfsVisit.contains(node)){
                        visit = visit - 69;
                        chars[i][j] = visit.toString();
                    }
                    else if(check && (visit >= 79 && visit < 89) && dfsVisit.contains(node)){
                        visit = visit - 79;
                        chars[i][j] = visit.toString();
                    }
                    else if(check && (visit >= 89 && visit < 99) && dfsVisit.contains(node)){
                        visit = visit - 89;
                        chars[i][j] = visit.toString();
                    }
                    else if(DfsShortestPath  && dfsVisit.contains(node))
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

    public int getCellsVisited(){
        return cellsVisited;
    }

    public int getPathLength(){
        return pathLength;
    }

    public ArrayList<Cell> getDfsVisit(){
        return dfsVisit;
    }

}
