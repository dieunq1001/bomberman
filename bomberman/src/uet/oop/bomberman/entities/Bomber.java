package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

public class Bomber extends AnimatedEntity {
    Sprite _sprite;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    Keyboard _input = BombermanGame.keyboard;
    Boolean _moving;
    int _direction;

    private boolean isDead;

    @Override
    public void update() {
        animate();
        calculateMove();
        chooseSprite();
        img = _sprite.getFxImage();
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    protected void calculateMove() {
        int xa = 0, ya = 0;
        if (_input.up) ya--;
        if (_input.down) ya++;
        if (_input.left) xa--;
        if (_input.right) xa++;

        if (xa != 0 || ya != 0) {
            move(xa * 1.0, ya * 1.0);
            _moving = true;
        } else {
            _moving = false;
        }

    }

    public boolean vachamwall(Entity wall) {
        if (checkwall(wall)) {
            return true;
        }
        return false;
    }

    private boolean checkwall(Entity wall) {
        boolean result = false;
        int x1 = wall.x;
        int y1 = wall.y;
        if (x >= x1 && x <= x1 + 32 && y + 16 > y1 && y + 16 < y1 + 32) {
            setX(wall.getX() + 32);
            result = true;
        }
        if (x + 22 <= x1 + 32 && x + 22 >= x1 && y + 16 > y1 && y + 16 < y1 + 32) {
            setX(wall.getX() - 22);
            return true;
        }
        if (y >= y1 && y <= y1 + 32 && x + 16 > x1 && x + 16 < x1 + 32) {
            setY(wall.getY() + 32);
            return true;
        }
        if (y + 32 <= y1 + 32 && y + 32 >= y1 && x + 16 > x1 && x + 16 < x1 + 32) {
            setY(wall.getY() - 32);
            return true;
        }
        return result;
    }

    public boolean vachambrick(Entity brick) {
        if (checkbrick(brick)) {
            return true;
        }
        return false;
    }

    private boolean checkbrick(Entity brick) {
        boolean result = false;
        int x1 = brick.x;
        int y1 = brick.y;
        if (x >= x1 && x <= x1 + 32 && y + 16 > y1 && y + 16 < y1 + 32) {
            setX(brick.getX() + 32);
            result = true;
        }
        if (x + 22 <= x1 + 32 && x + 22 >= x1 && y + 16 > y1 && y + 16 < y1 + 32) {
            setX(brick.getX() - 22);
            return true;
        }
        if (y >= y1 && y <= y1 + 32 && x + 16 > x1 && x + 16 < x1 + 32) {
            setY(brick.getY() + 32);
            return true;
        }
        if (y + 32 <= y1 + 32 && y + 32 >= y1 && x + 16 > x1 && x + 16 < x1 + 32) {
            setY(brick.getY() - 32);
            return true;
        }
        return result;
    }

    public void move(double xa, double ya) {
        if (xa > 0) _direction = 1;
        if (xa < 0) _direction = 3;
        if (ya > 0) _direction = 2;
        if (ya < 0) _direction = 0;
        y += ya;
        x += xa;

    }

    private void chooseSprite() {
        switch (_direction) {
            case 0:
                _sprite = Sprite.player_up;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case 1:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
            case 2:
                _sprite = Sprite.player_down;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                _sprite = Sprite.player_left;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
        }
    }

}
