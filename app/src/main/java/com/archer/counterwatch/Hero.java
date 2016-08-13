package com.archer.counterwatch;


import android.widget.ImageButton;

public class Hero {

    int characterID;
    String name;
    int imageButtonID;
    public Hero(int id, String name, int imageButtonID){
        characterID = id;
        this.name = name;
        this.imageButtonID = imageButtonID;
    }
}
