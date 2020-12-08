package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Bomb extends AnimatedEntity {
    Sprite _sprite;
    private long start;
    private long end;
    private int bombPower;
    private boolean left, right, top, bot;

    public Bomb(int x, int y, Image img, int bombPower) {
        super(x, y, img);
        left = false;
        top = false;
        right = false;
        bot = false;
        start = System.currentTimeMillis();
        this.bombPower = bombPower;
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
        if (delta > 2) {
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
        _sprite = Sprite.movingSprite(Sprite.bomb_2, Sprite.bomb_1, Sprite.bomb, _animate, 20);
    }

    public void explosion(List<Entity> exp, List<Entity> stillObject) {
        int x = this.getX();
        int y = this.getY();
        for (int i = 0; i < bombPower; i++) {
            if (i == 0) {
                exp.add(new Explosion(getXUnit(), getYUnit(), Sprite.bomb_exploded.getFxImage(), "center"));
            } else if (i < bombPower - 1) {
                if (left == false) {
                    int x1 = x - (32 * i) + 16;
                    int y1 = y + 16;
                    boolean wallTest = false;
                    for (Entity s : stillObject) {
                        if (s instanceof Wall) {
                            if (vachamwall(s, x1, y1)) {
                                wallTest = true;
                                left = true;
                            }
                        }

                    }
                    if (wallTest == false) {
                        exp.add(new Explosion(getXUnit() - i, getYUnit(), Sprite.explosion_horizontal.getFxImage(), "horizontal"));
                    }

                }
                if (right == false) {
                    int x1 = x + (32 * i) + 16;
                    int y1 = y + 16;
                    boolean wallTest = false;

                    for (Entity s : stillObject) {
                        if (s instanceof Wall) {
                            if (vachamwall(s, x1, y1)) {
                                wallTest = true;
                                right = true;
                            }
                        }

                    }
                    if (wallTest == false) {
                        exp.add(new Explosion(getXUnit() + i, getYUnit(), Sprite.explosion_horizontal.getFxImage(), "horizontal"));
                    }

                }
                if (top == false) {
                    int x1 = x + 16;
                    int y1 = y - (32 * i) + 16;
                    boolean wallTest = false;

                    for (Entity s : stillObject) {
                        if (s instanceof Wall) {
                            if (vachamwall(s, x1, y1)) {
                                wallTest = true;
                                top = true;
                            }
                        }

                    }
                    if (wallTest == false) {
                        exp.add(new Explosion(getXUnit(), getYUnit() - i, Sprite.explosion_vertical.getFxImage(), "vertical"));
                    }

                }
                if (bot == false) {
                    int x1 = x + 16;
                    int y1 = y + (32 * i) + 16;
                    boolean wallTest = false;
                    //boolean brickTest = false;
                    for (Entity s : stillObject) {
                        if (s instanceof Wall) {
                            if (vachamwall(s, x1, y1)) {
                                wallTest = true;
                                bot = true;
                            }
                        }

                    }
                    if (wallTest == false) {
                        exp.add(new Explosion(getXUnit(), getYUnit() + i, Sprite.explosion_vertical.getFxImage(), "vertical"));
                    }

                }
            } else {
                if (left == false) {
                    int x1 = x - (32 * i) + 16;
                    int y1 = y + 16;
                    boolean wallTest = false;
                    for (Entity s : stillObject) {
                        if (s instanceof Wall) {
                            if (vachamwall(s, x1, y1)) {
                                wallTest = true;
                                left = true;
                            }
                        }

                    }
                    if (wallTest == false) {
                        exp.add(new Explosion(getXUnit() - i, getYUnit(), Sprite.explosion_horizontal_left_last.getFxImage(), "horizontal_left"));
                    }

                }
                if (right == false) {
                    int x1 = x + (32 * i) + 16;
                    int y1 = y + 16;
                    boolean wallTest = false;

                    for (Entity s : stillObject) {
                        if (s instanceof Wall) {
                            if (vachamwall(s, x1, y1)) {
                                wallTest = true;
                                right = true;
                            }
                        }

                    }
                    if (wallTest == false) {
                        exp.add(new Explosion(getXUnit() + i, getYUnit(), Sprite.explosion_horizontal_right_last.getFxImage(), "horizontal_right"));
                    }

                }
                if (top == false) {
                    int x1 = x + 16;
                    int y1 = y - (32 * i) + 16;
                    boolean wallTest = false;

                    for (Entity s : stillObject) {
                        if (s instanceof Wall) {
                            if (vachamwall(s, x1, y1)) {
                                wallTest = true;
                                top = true;
                            }
                        }

                    }
                    if (wallTest == false) {
                        exp.add(new Explosion(getXUnit(), getYUnit() - i, Sprite.explosion_vertical_top_last.getFxImage(), "vertical_top"));
                    }

                }
                if (bot == false) {
                    int x1 = x + 16;
                    int y1 = y + (32 * i) + 16;
                    boolean wallTest = false;
                    for (Entity s : stillObject) {
                        if (s instanceof Wall) {
                            if (vachamwall(s, x1, y1)) {
                                wallTest = true;
                                bot = true;
                            }
                        }

                    }
                    if (wallTest == false) {
                        exp.add(new Explosion(getXUnit(), getYUnit() + i, Sprite.explosion_vertical_down_last.getFxImage(), "vertical_bot"));
                    }

                }
            }
        }
    }

    public boolean vachamwall(Entity wall, int x, int y) {
        return (x < wall.getX() + wall.getSize() && x > wall.getX() && y < wall.getY() + wall.getSize() && y > wall.getY());
    }


}
