package com.example.mdp.map;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

public class Obstacle {
    int x, y;
    int initX, initY;
    int xArena = -1, yArena = -1;
    int offset;
    float obsOffset1 = 3f;
    int obsOffset2 = 28;
    int touchCount = 0;
    int aObsX, aObsY;
    boolean actionDown = false;
    boolean longPress = false;
    int obsFace = -1;
    String obsID, targetID;
    Paint obstacleNumberPaint = new Paint();


    public Obstacle(int x, int y, int initX, int initY, String obsID, int touchCount, int obsFace, String targetID){
        this.x = x;
        this.y = y;
        this.touchCount = touchCount;
        this.obsFace = obsFace;
        this.obsID = obsID;
        this.targetID = targetID;
        this.initX = initX;
        this.initY = initY;
        this.obstacleNumberPaint.setColor(Color.WHITE);
        this.obstacleNumberPaint.setTextSize(13);
        this.obstacleNumberPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.obstacleNumberPaint.setAntiAlias(true);
        this.obstacleNumberPaint.setStyle(Paint.Style.FILL);
        this.obstacleNumberPaint.setTextAlign(Paint.Align.LEFT);
    }
    public float getPaintTextSize(){
        return this.obstacleNumberPaint.getTextSize();
    }
    public void setPaintTextSize(int size){
        this.obstacleNumberPaint.setTextSize(size);
    }

    public int getObsX(){
        return  x;
    }

    public int getObsY(){
        return  y;
    }

    public int getaObsX(){
        return  aObsX;
    }

    public int getaObsY(){
        return  aObsY;
    }

    public void setaObsX(int aObsX){
        this.aObsX = aObsX;
    }

    public void setaObsY(int aObsY){
        this.aObsY = aObsY;
    }

    public int [] getInitCoords () {
        System.out.println(initX);
        System.out.println(initY);
        return new int [] {initX,initY};}

    public int getObsFace(){
        return obsFace;
    }

    public String getObsID(){
        return obsID;
    }

    public int getTouchCount(){
        return touchCount;
    }

    public int incrTouchCount(){
        touchCount++;
        return touchCount;
    }

    public void setObsX(int x){
        this.x = x;
    }

    public void setObsY(int y){
        this.y = y;
    }

    public void setInitX(int initX){
        this.initX = initX;
    }

    public void setInitY(int initY){
        this.initY = initY;
    }

    public void setInitCoords (int initX, int initY) {
        this.initX = initX;
        this.initY = initY;
    }

    public int setObsFace(int touchCount){
        switch (touchCount){
            case 1:
                //Green: Left (West)
//                obsFace = "North";
                obsFace = 0;
                break;
            case 2:
                //Red: Right
//                obsFace = "East";
                obsFace = 2;
                break;
            case 3:
                //Yellow: Down
//                obsFace = "South";
                obsFace = 1;
                break;
            case 4:
                //Blue: Front
//                obsFace = "West";
                obsFace = 3;
                break;
            default:
                //Black
//                obsFace = " ";
                obsFace = -1;
        }
        this.obsFace = obsFace;
        return obsFace;
    }

    public void drawObsFace(Canvas canvas, int touchCount, Paint paint){
        switch (touchCount){
            case 1:
                //Green: Left (West)
//                obsFace = "North";
                obsFace = 0;
                canvas.drawLine(x, y + obsOffset1, x + offset, y + obsOffset1, paint);
                break;

            case 2:
                //Red: Right
//                obsFace = "East";
                obsFace = 2;
                canvas.drawLine(x + obsOffset2, y , x + obsOffset2, y + offset, paint);
                break;
            case 3:
                //Yellow: Down
//                obsFace = "South";
                obsFace = 1;
                canvas.drawLine(x, y + obsOffset2, x + offset, y + obsOffset2, paint);
                break;
            case 4:
                //Blue: Front
//                obsFace = "West";
                obsFace = 3;
                canvas.drawLine(x + obsOffset1, y , x + obsOffset1, y + offset, paint);
                break;
            default:
                //Black
//                obsFace = " ";
                obsFace = -1;
        }

    }

    //Setting action down status
    public void setActionDown(boolean status){
        //When touched down
        this.actionDown = status;
    }

    //Getting action down status
    public boolean getActionDown(){
        return actionDown;
    }

    //Setting long press status
    public void setLongPress(boolean status){
        //When touched down
        this.longPress = status;
    }

    //Getting long press status
    public boolean getLongPress(){
        return longPress;
    }

    //Set draggable obj coordinates, can be adjusted to change how much of the object is covered by the users finger
    //currently able to see just the corners of it
    public void setPosition(int x, int y){
        this.x = x - 63;
        this.y = y - 63;
    }

    //Use this for draggable object
    public boolean isTouched(int x, int y){
        boolean xIsInside = x > this.x && x < this.x + 100;
        boolean yIsInside = y > this.y && y < this.y + 100;

        return xIsInside && yIsInside;
    }

    //sets objects coordinates in the arena
    public void setObsMapCoord (int xArena, int yArena){
        this.xArena = xArena;
        this.yArena = yArena;
    }

    public int[] getObsMapCoord (){
        return new int[]{xArena, yArena};
    }

    public int resetTouchCount() {
        touchCount = 1;
        return touchCount;
    }

//    public Paint getObsPaint() {
//        return obsPaint;
//    }

    //Setting the obstacle's paint settings
//    public Paint setObsPaint(Paint obsPaint) {
//        this.obsPaint = obsPaint;
//        return obsPaint;
//    }
//
    public void setTouchCount(int touchCount) {
        this.touchCount = touchCount;
    }

    public String setTargetID(String targetID) {
        this.targetID = targetID;
        return targetID;
    }

    public String getTargetID() {
        return targetID;
    }
    public void drawObj(Canvas canvas){
        Log.d("Obstacle", "Drawing Object");
        Paint obstaclePaint = new Paint();
        obstaclePaint.setColor(Color.BLACK);
        obstaclePaint.setStyle(Paint.Style.FILL);
        obstaclePaint.setStrokeWidth(3f);

        Log.d("Drawing Object:", "Coordinates: " + x + "," + y);

        canvas.drawRect(x,y,x+offset,y+offset, obstaclePaint);
    }

    public void setResizeUp(boolean status){
        if(status == true){
            offset = 70;
        } else {
            offset = 31;
        }

    }

    public void setFaceResizeUp(boolean status){
        if(status == true){
            offset = 100;
        } else {
            offset = 31;
        }

    }



}
