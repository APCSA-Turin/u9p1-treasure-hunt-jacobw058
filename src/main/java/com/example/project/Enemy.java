package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy extends Sprite { //child  of Sprite
    
    public Enemy(int x, int y) {
        super(x, y);
    }


    //the methods below should override the super class 


    @Override
    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "Enemy:(" + getX() + "," + getY() + ")";
    }

    @Override
    public String getRowCol(int size){ //return "Enemy:"+row col
        return "Enemy:" + arrToString(Grid.converter(getX(), getY(), size));
    }
}