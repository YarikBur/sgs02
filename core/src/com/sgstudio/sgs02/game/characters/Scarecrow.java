package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.main.Main;

public class Scarecrow {
	private int range;
	private int x;
	private int y;
	private final float width = 185/4;
	private final float height = 146/4;
	private Texture texture;

	private Main main;
	private SpriteBatch batch;


	public Scarecrow(Main main, SpriteBatch batch){
		texture = new Texture("pugalo.png");
		x = -100;
		y = -100;
	}

	public void render (SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
	}
	
	public void SetX(int x){
		this.x = x;
		}
	public void SetY(int y){
		this.y = y;
		}
	public void SetRange(int range){
		this.range = range;
		}

	public int GetX(){
		return x;
		}
	public int GetY(){
		return y;
		}
	public int GetRange(){
		return range;
		}
}
