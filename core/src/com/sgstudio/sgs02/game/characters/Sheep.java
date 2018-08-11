package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sheep {
	private int mass;
	private int velocity;
	private int x;
	private int y;
	private final float width = 222/5;
	private final float height = 152/5;
	private Texture texture;
	
	public void create () {
		texture = new Texture("sheep.png");
		x = 100;
		y = 100;
	}
	
	public void render (SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
		x++;
	}
	
	void SetX(int x){
		this.x = x;
		}
	void SetY(int y){
		this.y = y;
		}
	void SetMass(int mass){
		this.mass = mass;
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
	int GetMass(){
		return mass;
	}
	int GetVelocity(){
		return velocity;
	}

	
}
