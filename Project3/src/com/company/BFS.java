package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    int gridSize;
    Cell grid [][];
    ArrayList<Cell> bfsVisit = new ArrayList<>();
    Color color;
    private boolean added = false;

    public BFS(int gridSize, Cell grid[][]) {
        this.gridSize = gridSize;
        this.grid = grid;
    }

    public void bfs(){
        for(int row = 0; row < gridSize; row++)
            for(int col = 0; col < gridSize; col++)
                grid[row][col].color = Color.WHITE;
        Queue<Cell> queue = new LinkedList<Cell>();
        //choose first node as starting node
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
            for(Cell v: node.neighbors)
                if(v.color == Color.WHITE){
                    v.color = Color.GRAY;
                    v.numberOfVisits = visit;
                    visit ++;
                    v.distance = node.distance + 1;
                    v.predecessor = node;
                    if(v.x == gridSize - 1 && v.y == gridSize - 1)
                        added  = true;
                    if(!added )
                        bfsVisit.add(v);
                    queue.add(v);
                }

            node.color = Color.BLACK;
        }
        bfsVisit.add(grid[gridSize-1][gridSize-1]);
    }


}
