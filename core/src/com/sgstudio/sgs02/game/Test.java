package com.sgstudio.sgs02.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.sgstudio.sgs02.game.characters.Hero;
import com.sgstudio.sgs02.game.characters.Scarecrow;
import com.sgstudio.sgs02.game.characters.Sheep;
import com.sgstudio.sgs02.main.Main;
import com.sgstudio.sgs02.utils.Language;
import com.sgstudio.sgs02.utils.Particle;
import com.sgstudio.sgs02.utils.Settings;
import com.sgstudio.sgs02.utils.Text;
import com.sgstudio.sgs02.utils.Tiles;
import com.sgstudio.sgs02.utils.audio.Audio;
import com.sgstudio.sgs02.utils.controller.KeyManager;

public class Test implements Screen {
    Text text;
    KeyManager key;
    SpriteBatch batch;
    Texture img;
    Audio audio;
    Tiles tiles;
    Particle effect;
    Sprite bg;

    public long time;
    public long score;

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
    public void render(float delta) {
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
        batch.draw(img, 970, 970, 20, 20);
        for (Scarecrow scare: list_scarecrow)
            scare.render();
        for (Sheep sheep : list_sheep)
            sheep.render();
        hero.render();
        batch.end();

        batch.setProjectionMatrix(staticCamera.combined);
        batch.begin();
        text.writeUpperleft(batch,
                "Высота" + ": " + hero.GetX(),
                "Ширина" + ": " + hero.GetY(),
                "Время" + ": " + (TimeUtils.millis() / 1000 - this.time));

        text.writeUpperRight(batch,
                Language.getMessage(12) + ": " + Audio.getPlayed(),
                Language.getMessage(13) + ": " + Settings.getProperty("volume"),
                "Очки героя: " + hero.getPoints(), "Жизни: " + hero.getLifes());


        batch.end();


    }

    @Override
    public void resize(int width, int height) {
        Settings.setProperty("width", width + "");
        Settings.setProperty("height", height + "");
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        Audio.dispose();
    }

    @Override
    public void show() {
        batch = main.getBatch();
        img = new Texture("badlogic.jpg");
        key = new KeyManager();
        text = new Text();
        audio = new Audio();
        tiles = new Tiles();
        tiles.createAtlas("GUI.png", 4, 4);
        gui = tiles.getTextureRegion();
        effect = new Particle("test.p");
        bg = new Sprite(new Texture("atlas/g4_new.png"));
        Language.getAllStrings();
        Audio.randomStart();
        hero = new Hero(main, batch);

        time = TimeUtils.millis() / 1000;


        // Create Sheep
        for (int i = 0; i < 100; i++)
            list_sheep.add(new Sheep(main, batch));
    }

    public void update() {
        hero.update();
        for (Sheep sheep : list_sheep)
            sheep.update();
        for (Scarecrow scare: list_scarecrow)
            scare.update();
        colSheepHero();
        colSheepScare();
        clearScarecow();
    }

    private void colSheepHero() {
        /*
            Function for check collision Hero with sheep and repale
            Uses `list_sheep`, get<Class>()-functions and little Java.Math
         */
        for (Sheep sheep : list_sheep) {
            if (Math.sqrt(Math.pow(sheep.GetX() - hero.GetX(), 2) + Math.pow(sheep.GetY() - hero.GetY(), 2)) < 50) {
                float coor_x = hero.GetX() - sheep.GetX();
                float coor_y = hero.GetY() - sheep.GetY();
                sheep.SetX((int) (sheep.GetX() - coor_x));
                sheep.SetY((int) (sheep.GetY() - coor_y));
                sheep.reverseVect();
                //sheep.updateVect();
            }
        }
    }

    private void colSheepScare() {
        for (Sheep sheep : list_sheep)
            for (Scarecrow scare : list_scarecrow) {
                if (Math.sqrt(Math.pow(sheep.GetX() - scare.GetX(), 2) + Math.pow(sheep.GetY() - scare.GetY(), 2)) < 100) {
                    float coor_x = scare.GetX() - sheep.GetX();
                    float coor_y = scare.GetY() - sheep.GetY();
                    sheep.SetX((int) (sheep.GetX() - coor_x));
                    sheep.SetY((int) (sheep.GetY() - coor_y));
                }
            }
    }

    public void sheepHoly() {

    }

    public void addScarecrow(int x, int y) {
        this.list_scarecrow.add(new Scarecrow(main, batch, x, y));
    }

    public void clearScarecow(){
        for (Scarecrow scare: list_scarecrow)
            if(scare.GetX() == 0 && scare.GetY() == 0)
                list_scarecrow.remove(scare);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}

