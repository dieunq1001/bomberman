package uet.oop.bomberman.input;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;

public class Keyboard {

    private boolean[] keys = new boolean[128];
    public boolean up, down, left, right, space;
    public Scene scene;

    public Keyboard (Scene scene){
        this.scene=scene;
    }
    public void update() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    up = true;break;
                    case DOWN:  down = true; break;
                    case LEFT:  left  = true; break;
                    case RIGHT: right  = true; break;
                    case SPACE: space = true; break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    up = false; break;
                    case DOWN:  down = false; break;
                    case LEFT:  left  = false; break;
                    case RIGHT: right  = false; break;
                    case SPACE: space = false; break;
                }
            }
        });
    }



}
