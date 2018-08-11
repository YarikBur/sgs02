package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.main.Main;

public class Sheep {
	private int mass;
	private int speed = 1;
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
        x = 1500*(float)Math.random();
        y = 1500*(float)Math.random();
        this.batch = batch;
        this.main = main;

        /*
        TODO Add field vect_x and vect_y and change moving to % of vector
        One time (in C-tor) save vector, after that go on it
        */
    }
	
	public void render () {
		batch.draw(texture, x, y, width, height);
	}

	public void update(){

	    // Sheep's moving to 'lost' point.
        if ((x<lostX)&&(y<lostY)){
            x+=speed;
            y+=speed;
        } else if ((x<lostX)&&(y>lostY)){
            x+=speed;
            y-=speed;
        } else if ((x>lostX)&&(y<lostY)){
            x-=speed;
            y+=speed;
        } else if ((x>lostX)&&(y>lostY)){
            x-=speed;
            y-=speed;
        } else if (x<lostX)
            x+=speed;
        else if (y<lostY)
            y+=speed;
        else if (x>lostX)
            x-=speed;
        else if (y>lostY)
            y-=speed;
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
	public void SetSpeed(int speed){
		this.speed = speed;
	}
	
	public float GetX(){
		return this.x;
		}
	public float GetY(){
		return y;
		}
	public int GetMass(){
		return mass;
	}
	public int GetSpeed(){
		return speed;
	}
}
