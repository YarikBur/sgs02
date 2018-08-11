package com.sgstudio.sgs02.game;

import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Rectangle;
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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sgstudio.sgs02.game.characters.Hero;

public class Test implements Screen {
    Text text;
    KeyManager key;
    SpriteBatch batch;
    Texture img;
    Audio audio;
    Tiles tiles;
    Particle effect;
    Sprite bg;

    private Hero hero;

    public OrthographicCamera staticCamera;
    public OrthographicCamera camera;

    private final Main main;

    Map<String, TextureRegion> gui;

    public Test(final Main main) {
        this.main = main;

        staticCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        staticCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
    }

    @Override
    public void render (float delta) {
        Audio.update();
        staticCamera.update();
        camera.position.set(hero.GetX(), hero.GetY(), 0);
        camera.update();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        bg.draw(batch);
        text.writeUpperleft(batch,
                Language.getMessage(8) + ": " + Settings.getProperty("width"),
                Language.getMessage(9) + ": " + Settings.getProperty("height"),
                Language.getMessage(10) + ": " + Variables.booleanToInt(Variables.stringToBoolean(Settings.getProperty("console"))));
        hero.render();
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
        batch = main.getBatch();;
        img = new Texture("badlogic.jpg");
        key = new KeyManager();
        text = new Text();
        audio = new Audio();
        tiles = new Tiles();
        tiles.createAtlas("GUI.png", 4, 4);
        gui = tiles.getTextureRegion();
        effect = new Particle("test.p");
        bg = new Sprite(new Texture("atlas/g4.png"));
        Language.getAllStrings();
        Audio.randomStart();

        hero = new Hero(main, batch);
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

