package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends AnimatedEntity {
    Sprite _sprite;
    private long start;
    private long end;
    private String id;

    public Explosion(int x, int y, Image img, String id) {
        super(x, y, img);
        start = System.currentTimeMillis();
        this.id = id;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    private boolean isDead;

    @Override
    public void update() {
        animate();
        chooseSprite();
        end = System.currentTimeMillis();
        double delta = (end - start) / 1000.0;
        if (delta > 0.25) {
            isDead = true;
        }
        img = _sprite.getFxImage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    private void chooseSprite() {
        switch (id) {
            case "center": {
                _sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, _animate, 20);
                break;
            }
            case "vertical": {
                _sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, _animate, 20);
                break;
            }
            case "horizontal": {
                _sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, _animate, 20);
                break;
            }
            case "horizontal_left": {
                _sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, _animate, 20);
                break;
            }
            case "horizontal_right": {
                _sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, _animate, 20);
                break;
            }
            case "vertical_top": {
                _sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, _animate, 20);
                break;
            }
            case "vertical_bot": {
                _sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, _animate, 20);
                break;
            }
        }

    }

    public boolean vacham(Entity wall) {
        if (check(wall)) {
            return true;
        }
        return false;
    }

    private boolean check(Entity wall) {
        boolean result = false;
        int x1 = wall.x;
        int y1 = wall.y;
        if (x >= x1 && x <= x1 + 32 && y + 16 > y1 && y + 16 < y1 + 32) {
            result = true;
        }
        if (x + 22 <= x1 + 32 && x + 22 >= x1 && y + 16 > y1 && y + 16 < y1 + 32) {
            return true;
        }
        if (y >= y1 && y <= y1 + 32 && x + 16 > x1 && x + 16 < x1 + 32) {
            return true;
        }
        if (y + 32 <= y1 + 32 && y + 32 >= y1 && x + 16 > x1 && x + 16 < x1 + 32) {
            return true;
        }
        return result;
    }


}
