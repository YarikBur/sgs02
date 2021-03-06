package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.sgstudio.sgs02.main.Main;

public class Scarecrow {
    private int x;
    private int y;
    private final float imgWidth = 185 / 4;
    private final float imgHeight = 146 / 4;
    @SuppressWarnings("unused")
	private float center_x;
    @SuppressWarnings("unused")
	private float center_y;

    private long time = 0;


    private Texture texture;
    @SuppressWarnings("unused")
	private Main main;
    private SpriteBatch batch;


    public Scarecrow(Main main, SpriteBatch batch, int x, int y) {
        texture = new Texture("pugalo.png");
        this.x = x;
        this.y = y;
        this.main = main;
        this.batch = batch;
        this.time = TimeUtils.millis() / 1000;
    }

    public void render() {
        batch.draw(texture, x, y, imgWidth, imgHeight);
    }

    public void update() {
        center_x = this.x + imgWidth / 2;
        center_y = this.y + imgHeight / 2;
        if (this.time > 0) {
            if (this.time + 8 < TimeUtils.millis() / 1000) {
                this.x *= -1;
                this.y *= -1;
            }
            if (this.time + 10 < TimeUtils.millis() / 1000) {
                this.x = -1000;
                this.y = -1000;
                this.time = 0;
            }
        }
    }

    public int GetX() {
        return this.x;
    }

    public int GetY() {
        return this.y;
    }

}
