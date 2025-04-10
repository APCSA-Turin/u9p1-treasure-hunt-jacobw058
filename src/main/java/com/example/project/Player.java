package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite { //child of Sprite
    private int treasureCount; //current number of treasures collected
    private int numLives; //current number of lives remaining
    private boolean win; //used to determine when game ends
    private boolean lose; //used to determine when game ends

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y); //calls constructor in parent class Sprite
        treasureCount = 0;
        numLives = 2;
        win = false; //sets win to false, necessary for game to start
        lose = false; //sets lose to false, necessary for game to start
    }


    public int getTreasureCount(){return treasureCount;} //getter method for treasureCount
    public int getLives(){return numLives;} //getter method for numLives
    public boolean getWin(){return win;} //getter method for win
    public boolean getLose(){return lose;} //getter method for lose
    public void lose() { //makes the player lose the game, achieved when numLives <= 0
        lose = true;
    }

  
    //move method should override parent class, sprite
    public void move(String direction) { //move the (x,y) coordinates of the player
        if (direction.equals("w")) { //converts input "w" to increase in y coordinates
            setY(getY() + 1);
        } else if (direction.equals("s")) { //converts input "s" to decrease in y coordinates
            setY(getY() - 1);
        } else if (direction.equals("a")) { //converts input "a" to decrease in x coordinates
            setX(getX() - 1);
        } else if (direction.equals("d")) { //converts input "d" to increase in x coordinates
            setX(getX() + 1);
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
        if (isValid(size, direction)) { //checks validity of coordinate attempted to be interacted with
            if (obj instanceof Trophy) { //if the object being interacted with is a trophy and all treasures have been collected, the player wins the game
                if (numTreasures == treasureCount) {
                    win = true;
                }
            } else if (obj instanceof Enemy) { //if the object being interacted with is an enemy, the player will lose a life
                numLives--;
            } else if (obj instanceof Treasure) { //if the object being interacted with is a treasure, the player's treasure count will increase
                treasureCount++;
            }
        }
    }

    @Override
    public String getRowCol(int size) { //returns the array location of the player: Player:[row][col]
        return "Player:" + super.getRowCol(size);
    }
    @Override
    public String getCoords(){ //returns the coordinates of the player ->"Player:(x,y)"
        return "Player:" + super.getCoords();
    }

    public boolean isValid(int size, String direction){ //check grid boundaries
        int tempX = getX(); //x value used to simulate movement to check validity of coordinate
        int tempY = getY(); //y value used to simulate movement to check validity of coordinate
        if (direction.equals("w")) { //converts input "w" to increase in simulated y coordinates
            tempY++;
        } else if (direction.equals("s")) { //converts input "s" to decrease in simulated y coordinates
            tempY--;
        } else if (direction.equals("a")) { //converts input "a" to decrease in simulated x coordinates
            tempX--;
        } else if (direction.equals("d")) { //converts input "d" to increase in simulated x coordinates
            tempX++;
        }
        if ((tempX >= size || tempY >= size) || (tempX < 0 || tempY < 0)) { //returns false if simulated coordinates are out of bounds of the array, returns true otherwise
            return false;
        }
        return true;
    }


   
}



