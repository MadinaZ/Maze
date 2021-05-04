package com.company;

import java.util.*;

public class Maze {
    int gridSize;
    Cell currentCell;
    int totalCell;
    int visitedCell;
    Cell grid [][];
    Stack<Cell> cells = new Stack<>();
    DFS dfs ;


    public Maze(int gridSize) {
        this.gridSize = gridSize;
        grid = new Cell[gridSize][gridSize];

        dfs = new DFS(gridSize, grid);

        for(int i = 0; i < gridSize; i++)
            for(int j = 0; j < gridSize; j++)
                grid[i][j] = new Cell(i, j);

        grid[0][0].north = false;
        grid[gridSize-1][gridSize-1].south = false;

        createMaze();
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
//                currentCell.isVisited();
                visitedCell++;
            }
            else if(!cells.isEmpty()){
                currentCell = cells.pop();

            }
        }
    }


    public ArrayList<Cell> findNeighbors(Cell c){
        ArrayList<Cell> walls = new ArrayList<>();

        if(c.x-1 >= 0 && grid[c.x - 1][c.y].allWallsIntact())
            walls.add(grid[c.x-1][c.y]);
        if(c.y-1 >= 0 && grid[c.x][c.y-1].allWallsIntact())
            walls.add(grid[c.x][c.y-1]);
        if(c.x+1 < gridSize && grid[c.x + 1][c.y].allWallsIntact())
            walls.add(grid[c.x+1][c.y]);
        if(c.y+1 < gridSize && grid[c.x][c.y+1].allWallsIntact())
            walls.add(grid[c.x][c.y+1]);
        return walls;
    }


//    public void displayMaze(){
////        ArrayList<Cell> path = new ArrayList<>();
////        Cell coordinate = grid[gridSize-1][gridSize-1]
//        grid[0][0].north = false;
//        grid[gridSize-1][gridSize-1].south = false;
//
//        for(int y = 0; y < grid.length; y++){
//            //horizontal
//            for(int x = 0; x < grid.length; x++){
//                Cell coordinate = grid[x][y];
//                if(coordinate.north == true){
//                    if(x == grid[0].length-1)
//                        System.out.println("+-+");
//                    else
//                        System.out.println("+-");
//                }
//                else{
//                    if(x == grid[0].length-1)
//                        System.out.println("+ +");
//                    else
//                        System.out.println("+ ");
//                }
//            }
//
//            //vertical
//            for(int x = 0; x < grid.length; x++){
//                if(grid[x][y].west == true){
//
//                    if(x == grid[0].length-1){
//                        if(!grid[x][y].isVisited()){
//                            System.out.println();
//                        }
//                        else
//                            System.out.println("| |");
//                    }
//                } else {
//                    if (x == grid[0].length - 1) {
//                        if (grid[x][y].isVisited) {
//                            System.out.printf("|%d", grid[x][y].finishingTime);
//                        } else {
//                            System.out.print("| ");
//                        }
//                    }
//                    else {
//                        if (grid[x][y].finishingTime != -1) { //if it was visited
//                            System.out.printf(" %d", grid[x][y].finishingTime);
//                        }
//                        else {
//                            System.out.print("  ");
//                        }
//                    }
//                }
//            }
//        }
//
//    }

    @Override
    public String toString() {
        String[][] chars = new String[gridSize*2 + 1][gridSize*2 + 1];
        String s = "";
        for(int i = 0; i < chars.length; i ++){
            for(int j = 0; j < chars.length; j ++){
                //node is null, if no node exists
                Cell node = null;
                if(i % 2 == 1 && j % 2 == 1)
                    node = grid[i /2][j/2];
                //represents maze entrance and exit
                if((i == 0 && j == 1) || (i == gridSize*2 && j == gridSize*2 - 1))
                    chars[i][j] = " ";
                    //represents corners
                else if(chars[i][j] == null && i % 2 == 0 && j % 2 == 0)
                    chars[i][j] = "+";
                    //represents top and bottom i
                else if(i == 0 || i == chars.length - 1)
                    chars[i][j] = "-";
                    //represents leftmost and rightmost jumn
                else if(i % 2 == 1 && (j == 0 || j == chars[0].length - 1))
                    chars[i][j] = "|";
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
                    chars[i][j] = " ";
                }
                else
                    chars[i][j] = " ";
            }
        }

        for(int i = 0; i < chars.length; i++){
            for(int j = 0; j < chars.length; j++)
                s += chars[i][j];
            s += "\n";
        }
        return s;
    }

    public void solveDFS(){
        DFS dfs = new DFS(gridSize, grid);
        dfs.Dfs();
        System.out.println(dfs.printDFS());
        dfs.printDFSShortestPath();
        System.out.println(dfs.displayMaze());
    }

}
