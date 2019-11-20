package Initialization;

import Game.GameEntity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Background extends GameEntity{

    protected static Image[][] images = load();
    protected static int[][] map = loadMapFromFile();
    protected static GraphicsContext gc;

    public Background(GraphicsContext gc) {
        this.gc = gc;
    }

    public int[][] getMap()
    {
        return map;
    }


    private static Image[][] load() {
        Image[][] images = new Image[9][18];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 18; j++) {
                images[i][j] = ImageProcessing.splits(j, i);
            }
        }

        return images;
    }

    private static int[][] loadMapFromFile() {
        int a[][] = new int[15][25];
        File file = new File("src/Resources/Map/Map.txt");
        Scanner sc = null;

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
            return null;
        }

        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 25; j++) {
                a[i][j] = sc.nextInt();
                System.out.print(a[i][j] + " ");
            }

        System.out.print("\n");
        //sc.close();
        return a;
    }

    public static void draw() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 25; j++) {
                int index = map[i][j];
                gc.drawImage(images[index / 8][index % 8 + 10], j * Config.TILE_SIZE, i * Config.TILE_SIZE, Config.TILE_SIZE, Config.TILE_SIZE);
                gc.fillRect(j*Config.LENGTH_IMAGE, i*Config.LENGTH_IMAGE, 0.3, Config.LENGTH_IMAGE);
                gc.fillRect(j*Config.LENGTH_IMAGE,i*Config.LENGTH_IMAGE, Config.LENGTH_IMAGE,0.3);
            }
        }
    }
}
