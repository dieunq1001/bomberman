package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    private static BombermanGame instance;

    private GraphicsContext gc;
    private Canvas canvas;
    private long start;
    int bombPower = 3;
    int bombListSize = 4;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    private ArrayList<Bomb> bombArrayList = new ArrayList<>();
    private ArrayList<Ballon> ballonArrayList = new ArrayList<>();

    public static Keyboard keyboard;

    private Bomber bomberman;

    public static BombermanGame getInstance() {
        if (instance == null) {
            instance = new BombermanGame();
        }
        return instance;
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * 31, Sprite.SCALED_SIZE * 13);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
        keyboard = new Keyboard(scene);
        // Them scene vao stage
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        stage.setTitle("Bomberman");
        stage.setScene(scene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                keyboard.update();
                render();
                update();
            }
        };
        timer.start();
        CreateMap.createmap();
        entities.add(bomberman);
        start = System.currentTimeMillis();
    }


    public void update() {
        bombArrayList.forEach(Entity::update);
        entities.forEach(Entity::update);
    }

    public void render() {

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).render(gc);
            if (stillObjects.get(i) instanceof Wall) {
                if (bomberman != null) {
                    bomberman.vachamwall(stillObjects.get(i));
                }
            }
            if (stillObjects.get(i) instanceof Brick) {
                if (bomberman != null) {
                    bomberman.vachambrick(stillObjects.get(i));
                }
            }
        }
        if (keyboard.space) {
            long end = System.currentTimeMillis();
            double delta = (end - start) / 1000.0;
            if (delta > 0.35 && bomberman != null) {
                if (bombArrayList.size() < bombListSize) {
                    bombArrayList.add(new Bomb(bomberman.getXUnit(), bomberman.getYUnit(), Sprite.bomb_2.getFxImage(), bombPower));
                    start = System.currentTimeMillis();
                }
            }
        }
        for (int i = bombArrayList.size() - 1; i >= 0; i--) {
            bombArrayList.get(i).render(gc);
            if (bombArrayList.get(i).isDead()) {
                bombArrayList.get(i).explosion(entities, stillObjects);
                bombArrayList.remove(i);
            }
        }
        for (int i = entities.size() - 1; i >= 0; i--) {
            entities.get(i).render(gc);
            if (entities.get(i) instanceof Explosion) {
                if (bomberman != null) {
                    if (((Explosion) entities.get(i)).vacham(bomberman)) {
                        Bomber a = bomberman;
                        bomberman = null;
                        entities.remove(a);
                    }
                }
            }
        }
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i) instanceof Explosion) {
                if (((Explosion) entities.get(i)).isDead()) {
                    entities.remove(i);
                }
            }
        }

    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public void setStillObjects(List<Entity> stillObjects) {
        this.stillObjects = stillObjects;
    }

}
