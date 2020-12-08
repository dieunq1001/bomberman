package uet.oop.bomberman.graphics;

import javafx.scene.canvas.Canvas;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static uet.oop.bomberman.BombermanGame.entities;
import static uet.oop.bomberman.BombermanGame.stillObjects;

public class CreateMap {

    public static char[][] arr = new char[100][100];

    public static void createmap() {
        FileInputStream fin = null;

        try {
            fin = new FileInputStream("E:\\JAVA\\BOOM\\bomberman-starter-starter-2\\res\\levels\\Level1.txt");

            int ch = 0;
            int lv = 0, row = 0, col = 0;

            while ((ch = fin.read() - 48) > 0) {
                lv = lv * 10 + ch;
            }
            while ((ch = fin.read() - 48) > 0) {
                row = row * 10 + ch;
            }

            while ((ch = fin.read() - 48) > 0) {
                col = col * 10 + ch;
            }
            char[] chars = new char[row * col];

            int count = 0;
            while ((ch = fin.read()) != -1 && count < row * col) {
                if (ch == 10) {
                    continue;
                }
                chars[count] = (char) ch;
                count++;
            }
            count = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    arr[i][j] = chars[count];
                    count++;
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    Entity object;
                    Entity object1;
                    Entity object2;
                    switch (arr[i][j]) {
                        case '#':
                            object = new Wall(j, i, Sprite.wall.getFxImage());
                            stillObjects.add(object);
                            break;
                        case '*':
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(object);
                            break;
                        case 'x':
                            object = new Portal(j, i, Sprite.portal.getFxImage());
                            object1 = new Brick(j, i, Sprite.brick.getFxImage());
                            object2 = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object2);
                            stillObjects.add(object);
                            stillObjects.add(object1);
                            break;
                        case 'p':
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            break;
                        case '1':
                            object = new Ballon(j, i, Sprite.balloom_left1.getFxImage());
                            object1 = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object1);
                            stillObjects.add(object);
                            break;
                        case '2':
                            object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                            object1 = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object1);
                            stillObjects.add(object);
                            break;
                        case 'b':
                            object = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                            object1 = new Grass(j, i, Sprite.grass.getFxImage());
                            object2 = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(object1);
                            stillObjects.add(object);
                            stillObjects.add(object2);
                            break;
                        case 'f':
                            object = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                            object1 = new Grass(j, i, Sprite.grass.getFxImage());
                            object2 = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(object1);
                            stillObjects.add(object);
                            stillObjects.add(object2);
                            break;
                        case 's':
                            object = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                            object1 = new Grass(j, i, Sprite.grass.getFxImage());
                            object2 = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(object1);
                            stillObjects.add(object);
                            stillObjects.add(object2);
                            break;
                        case ' ':
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            break;
                    }

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

