package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.main.Main;

public class Scarecrow {
	private int x;
	private int y;
	private final float width = 185 / 4;
	private final float height = 146 / 4;
	private Texture texture;

	private Main main;
	private SpriteBatch batch;


	public Scarecrow(Main main, SpriteBatch batch, int x, int y) {
		texture = new Texture("pugalo.png");
		this.x = x;
		this.y = y;
		this.main = main;
		this.batch = batch;
	}

	public void render() {
		batch.draw(texture, x, y, width, height);
	}

	public int GetX(){return this.x;}
	public int GetY(){return  this.y;}

}
