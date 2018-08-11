package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.main.Main;

public class Sheep {
	private int mass;
	private int velocity;
	private float x;
	private float y;
	private final float width = 222/5;
	private final float height = 152/5;
	private Texture texture;
	private int lostX = 960;
	private int lostY = 960;

	private SpriteBatch batch;
	private Main main;

	public Sheep(Main main, SpriteBatch batch){
        texture = new Texture("Models/sheep_one.png");
        x = 960*(float)Math.random();
        y = 960*(float)Math.random();
        this.batch = batch;
        this.main = main;
    }
	
	public void render () {
		batch.draw(texture, x, y, width, height);
	}

	public void update(){
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
	
	public void SetX(int x){
		this.x = x;
		}
	public void SetY(int y){
		this.y = y;
		}
	public void SetMass(int mass){
		this.mass = mass;
	}
	public void SetVelocity(int velocity){
		this.velocity = velocity;
	}
	
	public float GetX(){
		return x;
		}
	public float GetY(){
		return y;
		}
	public int GetMass(){
		return mass;
	}
	public int GetVelocity(){
		return velocity;
	}
}
