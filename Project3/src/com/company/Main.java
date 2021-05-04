package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Enter the grid size: ");
        Scanner sc= new Scanner(System.in);
        int grid = sc.nextInt();


        Maze maze = new Maze(grid);
        System.out.println("Original maze: ");
        System.out.println(maze);

        System.out.println("DFS: ");
        maze.solveDFS();
//        maze.dfs.Dfs();
//        maze.dfs.printDFS();
//        maze.dfs.printDFSShortestPath();
    }
}
