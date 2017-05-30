package com.mcaroguevaragmail.smt3;

import android.graphics.Rect;

public abstract class GameObject {

    //calls the name of the objects and formats them
    protected int x;
    protected int y;
    protected int dy;
    protected int dx;
    protected int width;
    protected int height;

    //setters
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    //getters
    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public Rect getRectangle(){
        return new Rect(x, y, x+width, y+height);
    }
}