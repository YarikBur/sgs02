package com.sgstudio.sgs02.game.characters;

//import java.util.Calendar;

import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.game.Test;
import com.sgstudio.sgs02.main.Main;
import com.sgstudio.sgs02.utils.controller.KeyManager;

import java.sql.Time;

public class Hero {
    private int potatoes;

    private int speed;
    private long time_speed;

    private int points;
    private int spend_points;
    private int time;

    private int x;
    private int y;

    private Texture texture;
    private Sprite sprite;

    private Main main;
    private KeyManager keys;
    private SpriteBatch batch;
    private int width;
    private int height;

    private int lifes;

    public Hero(Main main, SpriteBatch batch) {
        this.main = main;
        this.lifes = 10;

        keys = new KeyManager();
        this.batch = batch;
        texture = new Texture("farmer.png");
        sprite = new Sprite(texture);
        x = width = main.test.x_center;
        y = height = main.test.y_center;


        speed = 2;
        time_speed = 0;
        this.time = (int) TimeUtils.millis() / 1000;
        this.spend_points = 0;
    }

    public void render() {
        batch.draw(texture, x, y, 50, 50);
    }

    public void update() {


        float coor_x = width - this.x;
        float coor_y = height - this.y;

        if (Math.sqrt(Math.pow(x - width, 2) + Math.pow(y - height, 2)) > 480) {
            this.x += coor_x * 0.05;
            this.y += coor_y * 0.05;
        }

        if (Math.sqrt(Math.pow(x - width, 2) + Math.pow(y - height, 2)) < 40) {
            this.x -= coor_x * 0.1;
            this.y -= coor_y * 0.1;
        }
        if(time_speed > 0){
            if(time_speed + 10 < TimeUtils.millis()  / 1000){
                time_speed = 0;
                speed -= 5;
            }
        }



        if (keys.getPressedLeft())
            x -= speed;

        if (keys.getPressedRight())
            x += speed;

        if (keys.getPressedUp())
            y += speed;

        if (keys.getPressedDown())
            y -= speed;

        if (keys.getJustPressed1() && this.time_speed == 0 && points >= 1)
        {
            this.time_speed = TimeUtils.millis() / 1000;
            this.speed += 5;
            spend_points += 1;
        }
        if (keys.getJustPressed2() && points >= 2)
        {
            this.putScarecrow();
            spend_points += 2;
        }
        if (keys.getJustPressed3() && points >= 10)
           plusLife();

        points = (int)TimeUtils.millis() / 1000 - this.time - this.spend_points;
    }

    public int getPoints(){return this.points;}
    public int getLifes(){return this.lifes;}
    public void minusLife(){this.lifes--;}
    private void plusLife(){this.spend_points+=10;lifes++;}

    private void putScarecrow() {
        main.test.addScarecrow(this.x, this.y);
    }

    public void SetX(int x) {
        this.x = x;
    }

    public void SetY(int y) {
        this.y = y;
    }

    public void SetPotatoes(int potatoes) {
        this.potatoes = potatoes;
    }

    public void SetSpeed(int speed) {
        this.speed = speed;
    }

    public float GetX() {
        return x;
    }

    public float GetY() {
        return y;
    }

    int GetPotatoes() {
        return potatoes;
    }

    int GetSpeed() {
        return speed;
    }
}
