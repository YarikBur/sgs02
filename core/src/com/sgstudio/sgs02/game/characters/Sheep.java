package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.sgstudio.sgs02.game.MyGame;
import com.sgstudio.sgs02.game.Test.*;
import com.sgstudio.sgs02.main.Main;

public class Sheep {
    private int mass;
    private int speed = 1;
    private float x;
    private float y;
    private float img_width = 222 / 5;
    private float img_height = 152 / 5;
    private float center_x;
    private float center_y;

    private Texture texture;

    private float lostX;
    private float lostY;

    private float vect_x;
    private float vect_y;

    private int counter;

    private SpriteBatch batch;
    private Main main;

    public Sheep(Main main, SpriteBatch batch) {
        texture = new Texture("Models/sheep_one.png");
        x = 1500 * (float) Math.random();
        y = 1500 * (float) Math.random();
        this.batch = batch;
        this.main = main;

        this.lostX = main.test.x_center;
        this.lostY = main.test.y_center;

        /*
        TODO Add field vect_x and vect_y and change moving to % of vector
        One time (in C-tor) save vector, after that go on it
        */
        this.vect_x = this.lostX - this.x;
        this.vect_y = this.lostY - this.y;

        this.center_x = x + img_width / 2;
        this.center_y = y + img_height / 2;


        this.mass = 1;
    }

    public void render() {
        batch.draw(texture, x, y, img_width, img_height);
    }

    public void update() {
        this.center_x = x + img_width / 2;
        this.center_y = y + img_height / 2;

        if (Math.sqrt(Math.pow(this.x - lostY, 2) + Math.pow(this.y - lostY, 2)) > 550) {
            this.updateVect();
        }

        this.x += this.vect_x * 0.001 * speed / mass;
        this.y += this.vect_y * 0.001 * speed / mass;

    }

    public void updateVect() {
        this.vect_x = this.lostX - this.x;
        this.vect_y = this.lostY - this.y;
    }

    public void reverseVect() {
        this.vect_x *= -1;
        this.vect_y *= -1;
    }

    public void SetX(int x) {
        this.x = x;
    }

    public void SetY(int y) {
        this.y = y;
    }

    public void SetMass(int mass) {
        this.mass = mass;
    }

    public void SetSpeed(int speed) {
        this.speed = speed;
    }

    public float GetX() {
        return this.x;
    }

    public float GetY() {
        return y;
    }

    public int GetMass() {
        return mass;
    }

    public int GetSpeed() {
        return speed;
    }

    public void joinSheeps() {
        this.img_height *= 1.2;
        this.img_width *= 1.2;
    }

    public float getImgWidth() {
        return this.img_width;
    }

    public float getImgHeight() {
        return this.img_height;
    }

    public float getCenter_x() {
        return this.center_x;
    }

    public float getCenter_y() {
        return this.center_y;
    }
}
