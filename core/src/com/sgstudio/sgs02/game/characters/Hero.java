package com.sgstudio.sgs02.game.characters;

import java.util.Map;

import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//import java.util.Calendar;

import com.badlogic.gdx.utils.TimeUtils;
import com.sgstudio.sgs02.main.Main;
import com.sgstudio.sgs02.utils.Tiles;
import com.sgstudio.sgs02.utils.audio.Audio;
import com.sgstudio.sgs02.utils.controller.KeyManager;

public class Hero {
    private int potatoes;

    private int speed;
    private long time_speed;

    private int points;
    private int spend_points;
    private int time;

    private int x;
    private int y;

    final private float imgX = 50;
    final private float imgY = 50;

    private float center_x;
    private float center_y;

    private Texture texture;
    private Sprite sprite;
    private Map<String, TextureRegion> health;

    private Main main;
    private KeyManager keys;
    private SpriteBatch batch;
    private int width;
    private int height;

    Sprite img_runOn;
    Sprite img_runOff;
    Sprite img_life;

    private Tiles tiles;
    Sprite img_scrow;

    private int lifes;

    private int angle = 0;

    public Hero(Main main, SpriteBatch batch) {
        tiles = new Tiles();
        tiles.createAtlas("icons/health_anim.png", 1, 11);
        health = tiles.getTextureRegion();

        this.main = main;
        this.lifes = 10;

        keys = new KeyManager();
        this.batch = batch;
        texture = new Texture("farmer.png");
        sprite = new Sprite(texture);
        x = width = main.mainLevel.x_center;
        y = height = main.mainLevel.y_center;
        int i = 500;

        img_scrow = new Sprite(new Texture("icons/pugalo_icon.png"));
        img_runOff = new Sprite(new Texture("icons/speed_icon_off.png"));
        img_runOn = new Sprite(new Texture("icons/speed_icon_on.png"));
        img_runOn.setSize(36 * 3, 30 * 4);
        img_runOff.setSize(36 * 3, 30 * 4);
        img_runOn.setPosition(i, 0);
        img_runOff.setPosition(i, 0);

        img_life = new Sprite(new Texture("icons/health_add.png"));
        img_scrow.setPosition(36 * 3 + i, 0);
        img_life.setPosition(36 * 3 * 2 + i, 0);
        img_life.setSize(36 * 3, 30 * 4);
        img_scrow.setSize(36 * 3, 30 * 4);

        center_x = x + imgX / 2;
        center_y = y + imgY / 2;

        speed = 2;
        time_speed = 0;
        this.time = (int) TimeUtils.millis() / 1000;
        this.spend_points = 0;
    }

    public void render() {
    	if ((angle == 360)||(angle == -360)){angle = 0;}
        batch.draw(sprite, x, y, sprite.getWidth()/2, sprite.getHeight()/2, sprite.getWidth(), sprite.getHeight(), 1, 1, angle);
    	if (keys.getPressedDown()){
    		if ((angle != -90)||(angle != 270)){
				if (((angle > 90)&&(angle < 270))||((angle > -90)&&(angle < 90))){
					angle++;
				} else {
					angle--;
				}
			}
    	}
    	if (keys.getPressedUp()){
    		if ((angle != -270)||(angle != 90)){
	    		if (((angle >= -90)&&(angle < 90))||((angle >= 270)&&(angle <= 0))){
	    			angle++;
	    		} else {
	    			angle--;
	    		}
    		}
    	}
    	if (keys.getPressedLeft()){
    		if ((angle != 180)||(angle != -180)){
	    		if (((angle >= 0)&&(angle < 180))||((angle < -180)&&(angle > -360))){
	    			angle++;
	    		} else {
	    			angle--;
	    		}
    		}
    	}
    	if (keys.getPressedRight()){
    		if ((angle != 0)||(angle != 360)||(angle != -360)){
	    		if (((angle > 180)&&(angle < 360))||((angle > -180)&&(angle < 0))){
	    			angle++;
	    		} else {
	    			angle--;
	    		}
    		}
    	}
        System.out.println(angle);
    }

    public void static_render() {
        img_runOff.draw(batch);
        if (time_speed > 0) {
            if (time_speed + 8 > TimeUtils.millis() / 1000)
                img_runOn.draw(batch);
            else if (time_speed + 10 > TimeUtils.millis() / 1000 && ((int) TimeUtils.millis() / 10) % 2 == 0)
                img_runOn.draw(batch);
        }
        img_life.draw(batch);
        img_scrow.draw(batch);

        renderLife(10, 10);
    }
    
    private void renderLife(int x, int y) {
    	if(this.getLifes() <= 0)
    		batch.draw(health.get("tiles0_0"), x, y);
    	else if(this.getLifes() >= 10)
    		batch.draw(health.get("tiles0_10"), x, y);
    	else
    		batch.draw(health.get("tiles0_" + this.getLifes()), x, y);
    }

    public void update() {
        if (lifes <= 0) {
            main.mainLevel.lostTime = TimeUtils.millis() / 1000 - main.mainLevel.startTime;
            Audio.dispose();
            this.main.setScreen(main.defeat);
        }
        center_y = this.y + imgY / 2;
        center_x = this.x + imgX / 2;

        float coor_x = width - this.x;
        float coor_y = height - this.y;

        if (Math.sqrt(Math.pow(center_x - width, 2) + Math.pow(center_y - height, 2)) > 480) {
            this.x += coor_x * 0.05;
            this.y += coor_y * 0.05;
        }

        if (Math.sqrt(Math.pow(center_x - width, 2) + Math.pow(center_y - height, 2)) < 40) {
            this.x -= coor_x * 0.1;
            this.y -= coor_y * 0.1;
        }
        if (time_speed > 0) {
            if (time_speed + 10 < TimeUtils.millis() / 1000) {
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

        if (keys.getJustPressed1() && this.time_speed == 0 && points >= 3) {
            main.mainLevel.count_speed += 1;
            this.time_speed = TimeUtils.millis() / 1000;
            this.speed += 5;
            this.spend_points += 3;
        }
        if (keys.getJustPressed2() && points >= 4) {
            main.mainLevel.count_scarecrow += 1;
            this.putScarecrow();
        }
        if (keys.getJustPressed3() && points >= 10) {
            main.mainLevel.count_added_lifes += 1;
            plusLife();
        }

        points = (int) TimeUtils.millis() / 1000 - this.time - this.spend_points;
    }

    public int getPoints() {
        return this.points;
    }

    public int getLifes() {
        return this.lifes;
    }

    public void minusLife(int life) {
        this.lifes -= life;
    }

    private void plusLife() {
        this.spend_points += 10;
        lifes++;
    }

    private void putScarecrow() {
        main.mainLevel.addScarecrow(this.x, this.y);
        this.spend_points += 4;
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

    public float GetImgX() {
        return this.imgX;
    }

    public float GetImgY() {
        return this.imgY;
    }

    public float getCenter_x() {
        return this.center_x;
    }

    public float getCenter_y() {
        return this.center_y;
    }
}
