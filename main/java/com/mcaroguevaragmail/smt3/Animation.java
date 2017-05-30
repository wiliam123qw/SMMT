package com.mcaroguevaragmail.smt3;
import android.graphics.Bitmap;

public class Animation {
    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private boolean playedOnce;

    public void setFrames(Bitmap[] frames){
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime(); //Starts timer for the background
    }
    public void setDelay(long d){
        delay = d;
    }
    public void setFrame(int i){
        currentFrame= i;
    }

    public void update() {
        long elapsed = (System.nanoTime()-startTime)/1000000;

        if(elapsed>delay)
        {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }
    }

    //getters
    public Bitmap getImage(){
        return frames[currentFrame];
    }
    public int getFrame(){
        return currentFrame;
    }
    public boolean playedOnce(){
        return playedOnce;
    }
}