package com.archer.counterwatch;

import android.util.Log;

public class Graph {

    private static final int[] BUTTON_IDS = {
            R.id.ana,
            R.id.bastion,
            R.id.dva,
            R.id.genji,
            R.id.hanzo,
            R.id.junkrat,
            R.id.lucio,
            R.id.mccree,
            R.id.mei,
            R.id.mercy,
            R.id.pharah,
            R.id.reaper,
            R.id.reinhardt,
            R.id.roadhog,
            R.id.soldier76,
            R.id.symmetra,
            R.id.torbjorn,
            R.id.tracer,
            R.id.widowmaker,
            R.id.winston,
            R.id.zarya,
            R.id.zenyatta,
    };

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
    public void addEdge(String from,String to,int weight)
    {
        int source = -1,target = -1;
        for(int i=0;i<vertex.length;i++)
        {
            if (vertex[i].name.equals(from))
                source = i;
            if (vertex[i].name.equals(to))
                target = i;
        }
        if(edges[source][target] != 0)
            Log.e("Link", "Link exist already");
        else {
            edges[source][target] = weight;
        }
    }



}
