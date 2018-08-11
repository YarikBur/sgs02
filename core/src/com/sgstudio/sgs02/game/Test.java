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

public class Test implements Screen {
    Text text;
    KeyManager key;
    SpriteBatch batch;
    Texture img;
    Audio audio;
    Tiles tiles;
    Particle effect;
    Sprite bg;

    OrthographicCamera staticCamera;
    OrthographicCamera camera;

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
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(bg,0,0,800,600);
        batch.end();
        this.update();
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
        bg = new Sprite(new Texture("atlas/bg.png"), 1600,900);
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

    private void update(){

        if (key.getJustPressedUp()){
            if (staticCamera.position.y < 1024)
                staticCamera.translate(0, 3, 0);
        }

        if(key.getJustPressedDown()) {
            if (staticCamera.position.y > 0)
                staticCamera.translate(0, -3, 0);
        }

        if(key.getJustPressedLeft()) {
            if (staticCamera.position.x > 0)
                staticCamera.translate(-3, 0, 0);
        }

        if(key.getJustPressedRight()) {
            if (staticCamera.position.x < 1024)
                staticCamera.translate(3, 0, 0);
        }
    }
}

