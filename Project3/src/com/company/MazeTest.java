package com.company;

import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

@Testable
public class MazeTest {
    Maze maze;
    MazeTest(){
        Random rand = new Random();
        //rand.setSeed();
        maze = new Maze(6);
    }

    @Description("For use with incrementalSize_Test")
    private void generateTestMaze(int i) {
        Maze maze = new Maze(i * 2);
        System.out.println("Grid Size: " + i * 2 + "\nOriginal maze: ");
        System.out.println(maze);

        System.out.println("DFS: ");
        double startTime = Math.ceil(System.nanoTime());
        maze.solveDFS();
        double endTime = Math.ceil(System.nanoTime());
        System.out.println("Time (ms)" + ((endTime - startTime) / 1000000));

        System.out.println("\nBFS: ");
        startTime = Math.ceil(System.nanoTime());
        maze.solveBFS();
        endTime = Math.ceil(System.nanoTime());
        System.out.println("Time (ms)" + ((endTime - startTime) / 1000000));
        System.out.println("");
        Assert.assertEquals(maze.bfs.printBFSShortestPath(), maze.dfs.printDFSShortestPath());
    }

    @Test
    @Description("Loops through grid sizes of 2 to 20 to construct random mazes and be solved")
    public void incrementalSize_Test() {
        //Check for random Mazes of Sizes 2 to 20.
        for (int i = 1; i <= 5; i++) {
            generateTestMaze(i);
        }
    }

    @Test
    @Description("Writes BFS (size 6) solved maze into file and compares it")
    public void BFS_6_writeFile_Test() {
        try {

            String fileName = "maze6BFS.txt";
            PrintWriter print = new PrintWriter(fileName);
            System.out.println("Maze of Size 6 BFS: ");
            this.maze.solveBFS();

            String BFS = this.maze.bfs.printBFSShortestPath();

            helper_write_to_file(fileName, print, BFS);

        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    @Test
    @Description("Writes BFS (size 6) solved maze into file and compares it")
    public void DFS_6_writeFile_Test() {
        try {
            String fileName = "maze6DFS.txt";
            PrintWriter print = new PrintWriter(fileName);
            System.out.println("Maze of Size 6 DFS: ");
            this.maze.solveDFS();

            String DFS = this.maze.dfs.printDFSShortestPath();

            helper_write_to_file(fileName, print, DFS);

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Description("Helper for DFS and BFS write to file tests.")
    private void helper_write_to_file(String fileName, PrintWriter fileWriter, String traversalType) throws FileNotFoundException {
        fileWriter.write(traversalType);
        fileWriter.close();
        Scanner reader = new Scanner(new File(fileName));
        StringBuilder readFromFile = new StringBuilder();
        while(reader.hasNextLine()){
            readFromFile.append(reader.nextLine()).append("\n");
        }
        reader.close();

        Assert.assertEquals(traversalType, readFromFile.toString());

    }

    @Test
    @Description("Tests whether DFS and BFS shortest path from constructed maze is the same")
    public void DFS_BFS_Equals()
    {
        maze.solveDFS();
        maze.solveBFS();
        Assert.assertEquals(this.maze.dfs.printDFSShortestPath(), this.maze.bfs.printBFSShortestPath());
    }

    @Test
    @Description("Tests whether all cells have at least one wall broken")
    public void cellWalls_Test(){
        for(int i = 0; i <maze.gridSize - 1; i++){
            for(int j = 1; j < maze.gridSize - 1; j++)
            {
                Assert.assertFalse(maze.grid[i][j].allWallsIntact());
            }
        }
    }
}
