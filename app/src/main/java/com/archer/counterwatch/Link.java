package com.archer.counterwatch;

public class Link {

    private int source;
    private int target;
    private int weigth;

    public Link(int source, int target,int weigth){
        this.source = source;
        this.target = target;
        this.weigth = weigth;

    }
    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    public int getWeight() {
        return weigth;
    }
}
