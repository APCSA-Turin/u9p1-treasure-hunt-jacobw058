package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid; //grid object used to run the game
    private Player player; //player object used to run the game
    private Enemy[] enemies; //array of enemy objects used to run the game
    private Treasure[] treasures; //array of treasure objects used to run the game
    private Trophy trophy; //trophy object used to run the game
    private int size; //size of grid

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size; //initializes size
        initialize(); //initializes all other variables
        play(); //runs the game
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() { //write your game logic here
        Scanner scanner = new Scanner(System.in); //sets up a scanner variable
        
        while(!player.getWin() && !player.getLose()){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            System.out.println("Treasure collected: " + player.getTreasureCount()); //prints current treasure count
            System.out.println("Lives remaining: " + player.getLives()); //prints current lives remaining
            grid.display(); //prints grid
            System.out.print("Enter a direction (w,a,s,d): ");
            String direction = scanner.nextLine(); //stores player input as a direction
            int tempX = player.getX(); //simulated x value
            int tempY = player.getY(); //simulated y value
            if (player.isValid(size, direction)) {
                if (direction.equals("w")) { //converts input "w" to increase in simulated y coordinates
                    tempY++;
                } else if (direction.equals("s")) { //converts input "s" to decrease in simulated y coordinates
                    tempY--;
                } else if (direction.equals("a")) { //converts input "a" to decrease in simulated x coordinates
                    tempX--;
                } else if (direction.equals("d")) { //converts input "d" to increase in simulated x coordinates
                    tempX++;
                }
            }
            Sprite object = grid.getGrid()[Grid.converter(tempX, tempY, size)[0]][Grid.converter(tempX, tempY, size)[1]]; //stores sprite ahead of player facing direction into a sprite object
            player.interact(size, direction, treasures.length, object); //calls interact to interact with the sprite
            Dot newDot = new Dot(player.getX(), player.getY());
            if ((!(object instanceof Trophy) || player.getTreasureCount() == treasures.length) && player.isValid(size, direction)) {
                player.move(direction); //moves player coordinates
                grid.placeSprite(player); //places player in new coordinates
                grid.placeSprite(newDot); //places dot where player was before
            }
            if (player.getWin()) { //simulates player winning
                clearScreen();
                System.out.println("Treasure collected: " + player.getTreasureCount()); //prints current treasure count
                System.out.println("Lives remaining: " + player.getLives()); //prints current lives remaining
                grid.display();
                System.out.println("You Win!");
            }
            if (player.getLives() <= 0) { //simulates player losing
                player.lose();
                clearScreen();
                System.out.println("Treasure collected: " + player.getTreasureCount()); //prints current treasure count
                System.out.println("Lives remaining: " + player.getLives()); //prints current lives remaining
                grid.display();
                System.out.println("Game Over, you have zero lives remaining");
            }
        }
    }

    public void initialize(){ //initializes all variables
        grid = new Grid(size); //initializes empty grid filled with dots
        player = new Player(0, 0); //initializes player at 0 0
        trophy = new Trophy(9, 9); //initializes trophy in top right corner
        Enemy firstEnemy = new Enemy(7, 7); // initializes enemy objects
        Enemy secondEnemy = new Enemy(7, 2);
        Enemy thirdEnemy = new Enemy(2, 7);
        Enemy fourthEnemy = new Enemy(2, 2);
        Enemy fifthEnemy = new Enemy(9, 0); 
        enemies = new Enemy[] {firstEnemy, secondEnemy, thirdEnemy, fourthEnemy, fifthEnemy}; //initializes enemy array to be filled with enemy objects
        Treasure firstTreasure = new Treasure(2, 0); //initializes treasure objects
        Treasure secondTreasure = new Treasure(0, 2);
        Treasure thirdTreasure = new Treasure(8, 8);
        treasures = new Treasure[] {firstTreasure, secondTreasure, thirdTreasure}; //initializes treasure array to be filled with treasure objects
        grid.placeSprite(player); //places player on grid
        grid.placeSprite(trophy); //places trophy on grid
        for (int i = 0; i < enemies.length; i++) { //places enemies on grid
            grid.placeSprite(enemies[i]);
        }
        for (int j = 0; j < treasures.length; j++) { //places treasures on grid
            grid.placeSprite(treasures[j]);
        }
        
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
    }

    public static void main(String[] args) { //main method
        Game game = new Game(10); //initializes and runs game
    }
}