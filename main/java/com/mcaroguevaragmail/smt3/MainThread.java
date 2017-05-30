package com.mcaroguevaragmail.smt3;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
//where game loop goes
public class MainThread extends Thread
{
    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;
    //constructor
    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        //call constructor of super class
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }
    @Override
    public void run()
    {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount =0;
        //how long you want each game loop runs (take target time of seconds)
        long targetTime = 1000/FPS;

        while(running) {
            //get start time
            startTime = System.nanoTime();
            canvas = null;
            //try lock canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    //every time you go through game loop, it will update once and draw one
                    //what makes app work
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                    //game loop called  30 times a second to make game flow naturally
                }
            } catch (Exception e) {
            }
            //finally block always executed
            finally{
                if(canvas!=null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

            //get time in nanoseconds
            //how many milliseconds it took to update and draw game once (target is 1/30 of second)
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            //how much time wait to go to another loop
            waitTime = targetTime-timeMillis;
            //make thread wait that thread time
            try{
                this.sleep(waitTime);
            }catch(Exception e){}
            //gone through 1 gameloop & frame count goes up one
            totalTime += System.nanoTime()-startTime;
            frameCount++;
            //if frame count = 30, calculate the average FPS and print
            if(frameCount == FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount =0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }
    public void setRunning(boolean b){
        running=b;
    }
}