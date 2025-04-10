package com.example.project;

public class Sprite {
    private int x; //x coordinate on grid of sprite
    private int y; //y coordinate on grid of sprite

    public Sprite(int x, int y) { //constructor that sets x and y
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;} //getter method for x
    public int getY(){return y;} //getter method for y

    public void setX(int newX){x = newX;} //setter method for x
    public void setY(int newY){y = newY;} //setter method for y

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "(" + x + "," + y + ")";
    }

    public String getRowCol(int size) { //returns the row and column of the sprite -> "[row][col]"
            return arrToString(Grid.converter(x, y, size));
    }
    
    public static String arrToString(int[] arr) { //toString method for 2 length arrays in the format necessary
        return "[" + arr[0] + "]" + "[" + arr[1] + "]";
    }

    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
