package com.archer.counterwatch;

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

    int heroSize = 22;
    int [][] graph = new int[heroSize][heroSize];

    // populate the graph
    //
    public Graph(){

    }


}
