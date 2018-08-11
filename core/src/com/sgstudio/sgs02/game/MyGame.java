package com.sgstudio.sgs02.game;

import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.sgstudio.sgs02.utils.Language;
import com.sgstudio.sgs02.utils.Particle;
import com.sgstudio.sgs02.utils.Settings;
import com.sgstudio.sgs02.utils.Text;
import com.sgstudio.sgs02.utils.Tiles;
import com.sgstudio.sgs02.utils.Variables;
import com.sgstudio.sgs02.utils.audio.Audio;
import com.sgstudio.sgs02.utils.controller.KeyManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.sgstudio.sgs02.menu.Menu;
import com.sgstudio.sgs02.main.Main;

public class MyGame implements Screen {
	Text text; 
	KeyManager key; 
	SpriteBatch batch;
	Texture img;
	Audio audio;
	Tiles tiles;
	Particle effect;

	private final Main main;
	
	Map<String, TextureRegion> gui;

	public MyGame(final Main main) {
		this.main = main;

//		staticCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		staticCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
//
//		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
	}


//	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
//		key = new KeyManager();
//		text = new Text();
//		audio = new Audio();
//		tiles = new Tiles();
//		tiles.createAtlas("GUI.png", 4, 4);
//		gui = tiles.getTextureRegion();
//		effect = new Particle("test.p");
//
//		Language.getAllStrings();
//
//		Audio.randomStart();
//	}
//
//
	@Override
	public void render (float delta) {
		Audio.update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 5, 5);

		effect.draw(batch, Gdx.graphics.getDeltaTime());
		effect.setPosition(Variables.stringToInt(Settings.getProperty("width")) / 2, Variables.stringToInt(Settings.getProperty("height")) / 2);
		if(effect.getParticle().isComplete())
			effect.draw(batch, Gdx.graphics.getDeltaTime());

		batch.draw(gui.get("tiles0_1"), 0, 0);


		//Напечатать текст
		text.writeUpperleft(batch,
				Language.getMessage(8) + ": " + Settings.getProperty("width"),
				Language.getMessage(9) + ": " + Settings.getProperty("height"),
				Language.getMessage(10) + ": " + Variables.booleanToInt(Variables.stringToBoolean(Settings.getProperty("console"))));

		text.writeUpperRight(batch,
				Language.getMessage(12) + ": " + Audio.getPlayed(),
				Language.getMessage(13) + ": " + Settings.getProperty("volume"));



		//Включить/Выключить вывод в консоль
		if(key.getJustPressedE()) {
			boolean console = !Settings.stringToBoolean(Settings.getProperty("console"));
			Settings.setProperty("console", console + "");
		}



		//Управление музыкой
		if(key.getJustPressedUp()) {
			int vol = Variables.stringToInt(Settings.getProperty("volume"));
			vol+=10;
			Audio.setVolume(vol);
		}

		if(key.getJustPressedDown()) {
			int vol = Variables.stringToInt(Settings.getProperty("volume"));
			vol-=10;
			Audio.setVolume(vol);
		}

		if(key.getJustPressedLeft()) {
			int vol = Audio.getPlayed();
			vol-=1;
			Audio.smoothAttenuation(vol);
		}

		if(key.getJustPressedRight()) {
			int vol = Audio.getPlayed();
			vol+=1;
			Audio.smoothAttenuation(vol);
		}



		//Поменять язык
		if(key.getJustPressedT()) {
			String lan = Settings.getProperty("language");
			if(lan.equals("ru_RU"))
				Settings.setProperty("language", "en_UK");
			else
				Settings.setProperty("language", "ru_RU");
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
		Audio.dispose();
	}

	@Override
	public void show(){
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		key = new KeyManager();
		text = new Text();
		audio = new Audio();
		tiles = new Tiles();
		tiles.createAtlas("GUI.png", 4, 4);
		gui = tiles.getTextureRegion();
		effect = new Particle("test.p");

		Language.getAllStrings();

		Audio.randomStart();
	}

	 @Override
	 public  void pause(){

	 }

	 @Override
	 public void resume(){

	 }

	@Override
	public void hide(){

	}
}
