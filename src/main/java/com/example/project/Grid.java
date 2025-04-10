package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid { //class to modify and utilize the game's grid
    private Sprite[][] grid; //2d array to simulate a grid
    private int size; //dimensions of square grid

    public Grid(int size) { //initialize and create a grid with all DOT objects
        grid = new Sprite[size][size]; //declare and initialize grid variable as a square array of sprites with dimensions size * size 
        for (int i = 0; i < size; i++) { //nested for loop to traverse the grid and fill it with dot objects
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Dot(reverseConverter(i, j, size)[0], reverseConverter(i, j, size)[1]);
            }
        }
        this.size = size; //initializes size instance variable
    }

 
    public Sprite[][] getGrid(){return grid;} //getter method for grid array


    public static int[] converter(int x, int y, int arrLen) { //static helper method that returns an array containing the equivalent array coordinates of the inputted x and y parameters, in a grid with size arrLen
        int[] arr = {(arrLen - y - 1), x};
        return arr;
    }
    public static int[] reverseConverter(int rows, int cols, int arrLen) { //static helper method that does the reverse of converter, taking grid coordinates and returning cartesian coordinates
        int[] arr = {arrLen - rows, cols - 1};
        return arr;
    }

    public void placeSprite(Sprite s){ //place sprite on coordinates on grid equal to its x and y attributes
        grid[converter(s.getX(), s.getY(), size)[0]][converter(s.getX(), s.getY(), size)[1]] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction and its coordinate values
        int tempX = s.getX(); //simulated x value
        int tempY = s.getY(); //simulated y value
        if (direction.equals("w")) { //converts input "w" to increase in simulated y coordinates
            tempY++;
        } else if (direction.equals("s")) { //converts input "s" to decrease in simulated y coordinates
            tempY--;
        } else if (direction.equals("a")) { //converts input "a" to decrease in simulated x coordinates
            tempX--;
        } else if (direction.equals("d")) { //converts input "d" to increase in simulated x coordinates
            tempX++;
        }
        grid[converter(tempX, tempY, size)[0]][converter(tempX, tempY, size)[1]] = s; //sets coordinates of sprite to new value
    }


    public void display() { //print out the current grid to the screen 
        for (int i = 0; i < grid.length; i++) { //nested for loop to traverse grid array
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] instanceof Dot) { //prints dots as purple square emojis
                    System.out.print("🟪");
                } else
                if (grid[i][j] instanceof Player) { //prints player as smiley face emoji
                    System.out.print("🙂");
                } else
                if (grid[i][j] instanceof Trophy) { //prints trophy as trophy emoji
                    System.out.print("🏆");
                } else 
                if (grid[i][j] instanceof Treasure) { //prints treasures as pizza emojis
                    System.out.print("🍕");
                } else
                if (grid[i][j] instanceof Enemy) { //prints enemies as mad face emojis
                    System.out.print("😡");
                }
            }
            System.out.println();
        }
    }
}