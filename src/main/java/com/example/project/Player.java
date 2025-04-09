package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;
    private boolean lose;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y);
        treasureCount = 0;
        numLives = 2;
        win = false;
        lose = false;
    }


    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}
    public boolean getLose(){return lose;}
    public void lose() {
        lose = true;
    }

  
    //move method should override parent class, sprite
    public void move(String direction) { //move the (x,y) coordinates of the player
        if (direction.equals("w")) {
            setY(getY() + 1);
        } else if (direction.equals("s")) {
            setY(getY() - 1);
        } else if (direction.equals("a")) {
            setX(getX() - 1);
        } else if (direction.equals("d")) {
            setX(getX() + 1);
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
        int tempX = getX();
        int tempY = getY();
        if (direction.equals("w")) {
            tempY++;
        } else if (direction.equals("s")) {
            tempY--;
        } else if (direction.equals("a")) {
            tempX--;
        } else if (direction.equals("d")) {
            tempX++;
        }
        if (isValid(size, direction)) {
            if (obj instanceof Trophy) {
                if (numTreasures == treasureCount) {
                    win = true;
                }
            } else if (obj instanceof Enemy) {
                numLives--;
            } else if (obj instanceof Treasure) {
                treasureCount++;
            }
        }
    }

    @Override
    public String getRowCol(int size) { 
        return "Player:" + arrToString(Grid.converter(getX(), getY(), size));
    }
    @Override
    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "Player:(" + getX() + "," + getY() + ")";
    }

    public boolean isValid(int size, String direction){ //check grid boundaries
        int tempX = getX();
        int tempY = getY();
        if (direction.equals("w")) {
            tempY++;
        } else if (direction.equals("s")) {
            tempY--;
        } else if (direction.equals("a")) {
            tempX--;
        } else if (direction.equals("d")) {
            tempX++;
        }
        if ((tempX >= size || tempY >= size) || (tempX < 0 || tempY < 0)) {
            return false;
        }
        return true;
    }


   
}



