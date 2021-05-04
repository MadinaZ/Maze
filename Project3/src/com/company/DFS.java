package com.company;

import java.util.ArrayList;

public class DFS {
    int gridSize;
    Cell grid [][];
    ArrayList<Cell> dfsVisit = new ArrayList<>();
    boolean check = false;
    Color color;
    private int time;
    boolean shortestPath;

    public DFS(int gridSize, Cell grid[][]) {
        this.gridSize = gridSize;
        this.grid = grid;
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
        check = false;
    }

    public void DfsVisit(Cell current) {
        if(current.equals(new Cell(gridSize - 1, gridSize - 1))){
            check = true;
            dfsVisit.add(current);
        }
        if(!check)
            dfsVisit.add(current);
        current.color = Color.GRAY;
        time = time + 1;
        current.discoveryTime = time;
        for(Cell n: current.neighbors)
            if(n.color == Color.WHITE)
                DfsVisit(n);

        current.color = Color.BLACK;
        time = time + 1;
        current.finishingTime = time;

    }

    public String printDFSShortestPath(){
        shortestPath = true;
        for(int x = dfsVisit.size() - 2; x > -1; x--)
            if(dfsVisit.get(x).finishingTime < dfsVisit.get(x+1).finishingTime)
                dfsVisit.remove(x);
        String s = this.toString();
        shortestPath = false;
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
        for(int row = 0; row < chars.length; row ++){
            for(int col = 0; col < chars.length; col ++){
                //if there is not a node at the given location, node is null
                Cell node = null;
                if(row % 2 == 1 && col % 2 == 1)
                    node = grid[row /2][col/2];
                //represents maze entrance and exit
                if((row == 0 && col == 1) || (row == gridSize*2 && col == gridSize*2 - 1))
                    chars[row][col] = "  ";
                    //represents corners
                else if(chars[row][col] == null && row % 2 == 0 && col % 2 == 0)
                    chars[row][col] = "+";
                    //represents top and bottom row
                else if(row == 0 || row == chars.length - 1)
                    chars[row][col] = "-";
                    //represents leftmost and rightmost column
                else if(row % 2 == 1 && (col == 0 || col == chars[0].length - 1))
                    chars[row][col] = "|";
                else if(node != null){
                    if(node.west)
                        chars[row][col - 1] = "|";
                    else
                        chars[row][col - 1] = " ";
                    if(node.east)
                        chars[row][col + 1] = "|";
                    else
                        chars[row][col + 1] = " ";
                    if(node.north)
                        chars[row - 1][col] = "-";
                    if(node.south)
                        chars[row + 1][col] = "-";
                    Integer t = (Integer)node.discoveryTime;
                    if(check && t < 10 && dfsVisit.contains(node))
                        chars[row][col] = t.toString();
                    else if(check && (t >= 10 && t < 19)&& dfsVisit.contains(node)){
                        t = t - 9;
                        chars[row][col] = t.toString();
                    }
                    else if(check && (t >= 19 && t < 29) && dfsVisit.contains(node)) {
                        t = t - 19;
                        chars[row][col] = t.toString();
                    }
                    else if(shortestPath  && dfsVisit.contains(node))
                        chars[row][col] = "#";
                    else
                        chars[row][col] = " ";
                }
                else
                    chars[row][col] = " ";
            }
        }
        for(int row = 0; row < chars.length; row ++){
            for(int col = 0; col < chars.length; col ++)
                s += chars[row][col];
            s += "\n";
        }
        return s;
    }
}
