package com.archer.counterwatch;

import android.util.Log;

public class Graph {



    private Hero[] vertex;
    private int [][] edges;
    private int size;

    // populate the graph
    //
    public Graph(int size){
        vertex = new Hero[size];
        edges = new int[size][size];
        this.size = size;

    }
    public void vertexSet(int i,Hero newHero)
    {
        vertex[i] = newHero;
    }
    public void addEdge(int source,int target,int weight)
    {
        if(weight == -1)
            edges[source][target] = weight;
    }

    public Hero[] getVertex() {
        return vertex;
    }

    public int getSize() {
        return size;
    }
}
