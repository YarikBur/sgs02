package com.sgstudio.sgs02;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.utils.Settings;
import com.sgstudio.sgs02.utils.Text;
import com.sgstudio.sgs02.utils.controller.KeyManager;

public class MyGame extends ApplicationAdapter {
	
	Settings cfg;
	Text text;
	KeyManager key;
	
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		cfg = new Settings();
		key = new KeyManager();
		text = new Text();
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		
		text.writeUpperleft(batch, "Width: " + cfg.getProperty("width"), "Height: " + cfg.getProperty("height"), "Console: " + cfg.getProperty("console"));
		
		if(key.getJustPressedE()) {
			boolean console = !cfg.stringToBoolean(cfg.getProperty("console"));
			cfg.setProperty("console", console + "");
		}
		batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		cfg.setProperty("width", width + "");
		cfg.setProperty("height", height + "");
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
