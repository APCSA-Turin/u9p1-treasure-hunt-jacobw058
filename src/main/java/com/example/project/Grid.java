package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid {
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        grid = new Sprite[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Dot(reverseConverter(i, j, size)[0], reverseConverter(i, j, size)[1]);
            }
        }
        this.size = size;
    }

 
    public Sprite[][] getGrid(){return grid;}

    public static int[] converter(int x, int y, int arrLen) {
        int[] arr = {(arrLen - y - 1), x};
        return arr;
    }
    public static int[] reverseConverter(int rows, int cols, int arrLen) {
        int[] arr = {arrLen - rows, cols - 1};
        return arr;
    }

    public void placeSprite(Sprite s){ //place sprite in new spot
        System.out.println(Sprite.arrToString(converter(s.getX(), s.getY(), size)));
        System.out.println(s.getCoords());
        grid[converter(s.getX(), s.getY(), size)[0]][converter(s.getX(), s.getY(), size)[1]] = s;
    }

    public void setGridPos(Sprite spr, int x, int y) {
        grid[converter(x, y, size)[0]][converter(x, y, size)[1]] = spr;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        int tempX = s.getX();
        int tempY = s.getY();
        if (direction.equals("w")) {
            tempY++;
        } else if (direction.equals("s")) {
            tempY--;
        } else if (direction.equals("a")) {
            tempX--;
        } else if (direction.equals("d")) {
            tempX++;
        }
        grid[converter(tempX, tempY, size)[0]][converter(tempX, tempY, size)[1]] = s;
    }


    public void display() { //print out the current grid to the screen 
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] instanceof Dot) {
                    System.out.print("[ ]");
                } else
                if (grid[i][j] instanceof Player) {
                    System.out.print("[P]");
                } else
                if (grid[i][j] instanceof Trophy) {
                    System.out.print("[W]");
                } else 
                if (grid[i][j] instanceof Treasure) {
                    System.out.print("[T]");
                } else
                if (grid[i][j] instanceof Enemy) {
                    System.out.print("[E]");
                }
            }
            System.out.println();
        }
    }
    
    public void gameover(){ //use this method to display a loss
    }

    public void win(){ //use this method to display a win 
    }


}