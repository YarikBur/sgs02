package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scarecrow {
	private int range;
	private int x;
	private int y;
	private static float width;
	private static float height;
	private Texture texture;
	
	public void create () {
		texture = new Texture("pugalo.png");
		width = 185/4;
		height = 146/4;
		x = 100;
		y = 150;
	}
	
	public void render (SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
	}
	
	void SetX(int x){
		this.x = x;
		}
	void SetY(int y){
		this.y = y;
		}
	void SetRange(int range){
		this.range = range;
		}
	
	int GetX(){
		return x;
		}
	int GetY(){
		return y;
		}
	int GetRange(){
		return range;
		}
}
