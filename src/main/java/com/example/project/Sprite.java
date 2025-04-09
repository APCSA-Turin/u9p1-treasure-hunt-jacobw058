package com.example.project;

public class Sprite {
    private int x;
    private int y;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public void setX(int newX){x = newX;}
    public void setY(int newY){y = newY;}

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "(" + x + "," + y + ")";
    }

    public String getRowCol(int size) { //returns the row and column of the sprite -> "[row][col]"
      //  Grid newGrid = new Grid(size);
       // if (newGrid.getGridPos(Grid.converter(x, y, size)) instanceof Player) {
            return arrToString(Grid.converter(x, y, size));
        // } else if (newGrid.getGridPos(Grid.converter(x, y, size)) instanceof Enemy) {
        //     return "Enemy:" + arrToString(Grid.converter(x, y, size));
        // } else if (newGrid.getGridPos(Grid.converter(x, y, size)) instanceof Treasure) {
        //     if (newGrid.getGridPos(Grid.converter(x, y, size)) instanceof Trophy) {
        //         return "Trophy:" + arrToString(Grid.converter(x, y, size));
        //     }
        //     return "Treasure:" + arrToString(Grid.converter(x, y, size));
        // } else if (newGrid.getGridPos(Grid.converter(x, y, size)) instanceof Dot) {
        //     return "Dot:" + arrToString(Grid.converter(x, y, size));
       // } else {
       //     return arrToString(Grid.converter(x, y, size));
       // }
    }
    
    public static String arrToString(int[] arr) {
        return "[" + arr[0] + "]" + "[" + arr[1] + "]";
    }

    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
