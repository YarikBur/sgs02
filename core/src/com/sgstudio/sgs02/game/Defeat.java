package com.sgstudio.sgs02.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.main.Main;
import com.sgstudio.sgs02.menu.Menu;

public class Defeat implements Screen{
	
	private Main main;
	SpriteBatch batch;
	Sprite defeat;
	private Menu menu;

	public Defeat(Main main) {
		this.main = main;
	}

	@Override
	public void show() {
        batch = new SpriteBatch();
        defeat = new Sprite(new Texture(""));
        menu = new Menu(main);
        
	}
	
	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(defeat, 0, 0, 1280, 720);
        batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
        this.dispose();		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
