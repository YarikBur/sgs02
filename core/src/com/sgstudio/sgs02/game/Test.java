package com.sgstudio.sgs02.game;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import com.sgstudio.sgs02.game.characters.Scarecrow;
import com.sgstudio.sgs02.game.characters.Sheep;
import com.sgstudio.sgs02.utils.Language;
import com.sgstudio.sgs02.utils.Particle;
import com.sgstudio.sgs02.utils.Settings;
import com.sgstudio.sgs02.utils.Text;
import com.sgstudio.sgs02.utils.Tiles;
import com.sgstudio.sgs02.utils.Variables;
import com.sgstudio.sgs02.utils.audio.Audio;
import com.sgstudio.sgs02.utils.controller.KeyManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

    //  Public Lists
    public List<Sheep> list_sheep = new ArrayList<Sheep>();
    public List<Scarecrow> list_scarecrow = new ArrayList<Scarecrow>();


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
        this.update();
        Audio.update();
        staticCamera.update();
        camera.position.set(hero.GetX(), hero.GetY(), 0);
        camera.update();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        bg.draw(batch);
        hero.render();
        for(Sheep sheep: list_sheep)
            sheep.render();
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

        // Create Sheep
        for (int i = 0; i < 10; i++)
            list_sheep.add(new Sheep(main, batch));
    }

    public void update(){
        hero.update();
        for(Sheep sheep: list_sheep)
            sheep.update();
        colSheepHero();
    }

    private void colSheepHero(){
        for (Sheep sheep: list_sheep)
        {
            float coor_x = hero.GetX() - sheep.GetX();
            float coor_y = hero.GetY() - sheep.GetY();

            if(Math.sqrt(Math.pow(sheep.GetX() - hero.GetX() , 2) + Math.pow(sheep.GetY() - hero.GetY(), 2)) < 50)
            {
                sheep.SetX((int)(sheep.GetX() - coor_x));
                sheep.SetY((int)(sheep.GetY() - coor_y));
            }
        }

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

