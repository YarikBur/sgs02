package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.main.Main;
import com.sgstudio.sgs02.utils.controller.KeyManager;

public class Hero {
	private int potatoes;
	private int speed;
	private int x;
	private int y;

	private Texture texture;
	private Sprite sprite;

	private Main main;
	private KeyManager keys;
	private SpriteBatch batch;
	private int width;
	private int height;

	public Hero(Main main, SpriteBatch batch)
	{
		this.main = main;

		keys = new KeyManager();
		this.batch = batch;
		texture = new Texture("farmer.png");
		x  = 960;
		y  = 960;
		sprite = new Sprite(texture);
		width = 960;
		height = 960;
		speed = 2;
	}

	public void render () {
		batch.draw(texture, x, y,50,50);
	}

	public void update(){

		float coor_x = width - this.x;
		float coor_y = height - this.y;

        if(Math.sqrt(Math.pow(x - width , 2) + Math.pow(y - height, 2)) > 480)
        {
            this.x += coor_x * 0.05;
            this.y += coor_y * 0.05;
        }
        if(Math.sqrt(Math.pow(x - width , 2) + Math.pow(y - height, 2)) < 30)
        {
            this.x += coor_x * 0.01;
            this.y += coor_y * 0.01;
        }

		if (keys.getPressedLeft())
			x -= speed;

		if (keys.getPressedRight())
			x+=speed;

		if (keys.getPressedUp())
			y+=speed;

		if (keys.getPressedDown())
			y-=speed;

	}

	private void putScarecrow(){
       // Test.addScarecrow();
    }

	public void SetX(int x){
        this.x = x;
    }

	public void SetY(int y){
		this.y = y;
		}
	public void SetPotatoes(int potatoes){
		this.potatoes = potatoes;
	}
	public void SetSpeed(int speed){
		this.speed = speed;
	}

	public float GetX(){
		return x;
		}
	public float GetY(){
		return y;
		}
	int GetPotatoes(){
		return potatoes;
	}
	int GetSpeed(){
		return speed;
	}
}
