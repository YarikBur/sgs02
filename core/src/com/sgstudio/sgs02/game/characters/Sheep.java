package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sheep {
	private int mass;
	private int velocity;
	private float x;
	private float y;
	private final float width = 222/5;
	private final float height = 152/5;
	private Texture texture;
	private int lostX = 300;
	private int lostY = 300;
	
	public void create () {
		texture = new Texture("sheep.png");
		x = 650*(float)Math.random();
		y = 600*(float)Math.random();
	}
	
	public void render (SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
		if ((x<lostX)&&(y<lostY)){
			x++;
			y++;
			} else if ((x<lostX)&&(y>lostY)){
				x++;
				y--;
			} else if ((x>lostX)&&(y<lostY)){
				x--;
				y++;
			} else if ((x>lostX)&&(y>lostY)){
				x--;
				y--;
			} 
			else if (x<lostX){x++;}
			else if (y<lostY){y++;} 
			else if (x>lostX){x--;} 
			else if (y>lostY){x--;} 
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
	
	float GetX(){
		return x;
		}
	float GetY(){
		return y;
		}
	int GetMass(){
		return mass;
	}
	int GetVelocity(){
		return velocity;
	}

	
}
