package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy extends Sprite { //child  of Sprite
    
    public Enemy(int x, int y) { //constructor for Enemy
        super(x, y); //calls constructor in parent class Sprite
    }


    //the methods below should override the super class 


    @Override
    public String getCoords(){ //returns "Enemy:"+(x,y)
        return "Enemy:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size){ //return "Enemy:"+[row][col]
        return "Enemy:" + super.getRowCol(size);
    }
}