package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero {
	private int potatoes;
	private int velocity;
	private int x;
	private int y;
	private static float width;
	private static float height;
	private Texture texture;
	
	public void create () {
		texture = new Texture("farmer.png");
		width = 81/2;
		height = 79/2;
		x = 150;
		y = 150;
	}
	
	public void render (SpriteBatch batch, boolean LeftPressed, boolean RightPressed, boolean UpPressed, boolean DownPressed) {
		batch.draw(texture, x, y, width, height);
		if (LeftPressed){x--;} 
		if (RightPressed){x++;}
		if (UpPressed){y++;} 
		if (DownPressed){y--;}
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
	void SetVelocity(int velocity){
		this.velocity = velocity;
	}
	
	int GetX(){
		return x;
		}
	int GetY(){
		return y;
		}
	int GetPotatoes(){
		return potatoes;
	}
	int GetVelocity(){
		return velocity;
	}
}
