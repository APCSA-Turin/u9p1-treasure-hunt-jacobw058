package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        initialize();
        play();
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
        Scanner scanner = new Scanner(System.in);
        
        while(!player.getWin() && !player.getLose()){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            System.out.println(player.getCoords());
            System.out.println(player.getRowCol(size));
            System.out.println("Treasure collected: " + player.getTreasureCount());
            System.out.println("Lives remaining: " + player.getLives());
            grid.display();
            System.out.print("Enter a direction (w,a,s,d): ");
            String direction = scanner.nextLine();
            int tempX = player.getX();
            int tempY = player.getY();
            if (player.isValid(size, direction)) {
                if (direction.equals("w")) {
                    tempY++;
                } else if (direction.equals("s")) {
                    tempY--;
                } else if (direction.equals("a")) {
                    tempX--;
                } else if (direction.equals("d")) {
                    tempX++;
                }
            }
            Sprite object = grid.getGrid()[Grid.converter(tempX, tempY, size)[0]][Grid.converter(tempX, tempY, size)[1]];
            player.interact(size, direction, treasures.length, object);
            Dot newDot = new Dot(player.getX(), player.getY());
            if ((!(object instanceof Trophy) || player.getTreasureCount() == treasures.length) && player.isValid(size, direction)) {
                player.move(direction);
                grid.placeSprite(player);
                grid.placeSprite(newDot);
            }
            if (player.getWin()) {
                grid.display();
            }
            if (player.getLives() <= 0) {
                player.lose();
                grid.display();
                System.out.println("Game Over, you have zero lives remaining");
            }
        }
    }

    public void initialize(){
        grid = new Grid(size);
        player = new Player(0, 0);
        trophy = new Trophy(9, 9);
        Enemy firstEnemy = new Enemy(7, 7);
        Enemy secondEnemy = new Enemy(7, 2);
        Enemy thirdEnemy = new Enemy(2, 7);
        Enemy fourthEnemy = new Enemy(2, 2);
        Enemy fifthEnemy = new Enemy(9, 0);
        enemies = new Enemy[] {firstEnemy, secondEnemy, thirdEnemy, fourthEnemy, fifthEnemy};
        Treasure firstTreasure = new Treasure(2, 0);
        Treasure secondTreasure = new Treasure(0, 2);
        Treasure thirdTreasure = new Treasure(8, 8);
        treasures = new Treasure[] {firstTreasure, secondTreasure, thirdTreasure};
        grid.placeSprite(player);
        grid.placeSprite(trophy);
        for (int i = 0; i < enemies.length; i++) {
            grid.placeSprite(enemies[i]);
        }
        for (int j = 0; j < treasures.length; j++) {
            grid.placeSprite(treasures[j]);
        }
        grid.display();
        
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
    }

    public static void main(String[] args) {
        Game game = new Game(10);
    }
}