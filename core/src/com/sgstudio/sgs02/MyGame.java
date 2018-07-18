package com.sgstudio.sgs02;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.utils.Language;
import com.sgstudio.sgs02.utils.Settings;
import com.sgstudio.sgs02.utils.Text;
import com.sgstudio.sgs02.utils.Variables;
import com.sgstudio.sgs02.utils.controller.KeyManager; 

public class MyGame extends ApplicationAdapter {
	Text text; 
	KeyManager key; 
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		key = new KeyManager();
		text = new Text();
		
		Language.getAllStrings();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 5, 5);
		
		text.writeLowerRight(batch, 
				Language.getMessage(8) + ": " + Settings.getProperty("width"), 
				Language.getMessage(9) + ": " + Settings.getProperty("height"), 
				Language.getMessage(10) + ": " + Variables.booleanToInt(Variables.stringToBoolean(Settings.getProperty("console"))));
		
		if(key.getJustPressedE()) { 
			boolean console = !Settings.stringToBoolean(Settings.getProperty("console")); 
			Settings.setProperty("console", console + ""); 
		}
		batch.end();
	}
	
	@Override 
	public void resize(int width, int height) { 
		Settings.setProperty("width", width + ""); 
		Settings.setProperty("height", height + ""); 
	} 
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
