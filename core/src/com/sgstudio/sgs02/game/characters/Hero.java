package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.game.MyGame;
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
		update();
	}

	public void update(){
		if (Math.sqrt(Math.pow(x-width , 2) + Math.pow(y - height, 2)) > 520) {
			x = 960;
			y = 960;
		}
		if (keys.getPressedLeft()) {

			if(Math.sqrt(Math.pow(x-speed*1.5-width , 2) + Math.pow(y - height, 2)) > 480)
				x += speed * 2;
			x-=speed;
		}
		if (keys.getPressedRight()) {
			if(Math.sqrt(Math.pow(x+speed*1.5-width , 2) + Math.pow(y - height, 2)) > 450)
				x-= speed * 2;
			x+=speed;
		}
		if (keys.getPressedUp()) {
			if(Math.sqrt(Math.pow(x-width, 2) + Math.pow(y + speed*1.5 - height, 2)) > 450)
				y-= speed * 2;
			y+=speed;
		}
		if (keys.getPressedDown()){
			if(Math.sqrt(Math.pow(x-width , 2) + Math.pow(y - speed*1.5 - height, 2)) > 450)
				y+= speed * 2;
			y-=speed;
		}
	}
	
	void SetX(int x){
		this.x = x;
		}
	void SetY(int y){
		this.y = y;
		}
	void SetPotatoes(int potatoes){
		this.potatoes = potatoes;
	}
	void SetSpped(int speed){
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
