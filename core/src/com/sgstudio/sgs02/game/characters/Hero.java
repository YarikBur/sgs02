package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero {
	private int potatoes;
	private int velocity;
	private int x;
	private int y;
	private final float width = 81/2;
	private final float height = 79/2;
	private Sprite sprite;
	
	public void create () {
		x = 150;
		y = 150;
		sprite = new Sprite(new Texture("farmer.png"));
	}
	
	public void render (SpriteBatch batch, boolean LeftPressed, boolean RightPressed, boolean UpPressed, boolean DownPressed) {
		batch.draw(sprite, x, y, width, height);
		if (LeftPressed){
			x--; 
			sprite.rotate(180);
			} 
		if (RightPressed){x++;}
		if (DownPressed){y--;}
		if (UpPressed){y++;} 
	}
	
	void SetX(int x){this.x = x;}
	void SetY(int y){this.y = y;}
	void SetPotatoes(int potatoes){this.potatoes = potatoes;}
	void SetVelocity(int velocity){this.velocity = velocity;}
	
	int GetX(){return x;}
	int GetY(){return y;}
	int GetPotatoes(){return potatoes;}
	int GetVelocity(){return velocity;}
}
