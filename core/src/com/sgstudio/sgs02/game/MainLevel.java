package com.sgstudio.sgs02.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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
import com.sgstudio.sgs02.utils.*;
import com.sgstudio.sgs02.utils.Settings;
import com.sgstudio.sgs02.utils.audio.Audio;
import com.sgstudio.sgs02.utils.controller.KeyManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainLevel implements Screen {
    Text text;
    KeyManager key;
    SpriteBatch batch;
    Texture img;
    public Audio audio;
    Tiles tiles;
    Particle effect;
    Sprite bg;



    public long time;
    public long startTime;
    private long lastTime;
    public long lostTime;

    public long score = 0;
    public long count_sheep = 0;
    public long count_speed = 0;
    public long count_scarecrow = 0;
    public long count_added_lifes = 0;

    final public int x_center = 1080;
    final public int y_center = 1085;

    //  Public Lists
    public List<Sheep> list_sheep = new ArrayList<Sheep>();
    public List<Scarecrow> list_scarecrow = new ArrayList<Scarecrow>();


    private Hero hero;
    public OrthographicCamera staticCamera;
    public OrthographicCamera camera;

    private final Main main;

    Map<String, TextureRegion> gui;

    public MainLevel(final Main main) {
        this.main = main;

        staticCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        staticCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
    }

    @Override
    public void render(float delta) {
        level();
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
        batch.draw(img, x_center - 25, y_center - 25, 50, 50);
        for (Scarecrow scare : list_scarecrow)
            scare.render();
        for (Sheep sheep : list_sheep)
            sheep.render();
        hero.render();
        batch.end();

        batch.setProjectionMatrix(staticCamera.combined);
        batch.begin();
        
        text.writeUpperCenter(batch, "Тест", "Это тестовая строка", "тест вывода номер 3");
        
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

    private void level()
    {
        double sec = TimeUtils.millis() / 1000 - this.startTime;

        if (sec <= 0 || sec <= this.lastTime)
            return;
        else
            this.lastTime = TimeUtils.millis() / 1000 - this.startTime;

        if (sec > 0 && sec <= 5) {
            for (int i = 0; i < 3; i++)
                list_sheep.add(new Sheep(this.main, this.batch));
        }
        if (sec > 5 && sec <= 20)
            return;
        if (sec > 20 && sec < 51) {
            list_sheep.add(new Sheep(this.main, this.batch));
            return;
        }
        if (sec == 51) {
            for (Sheep sheep: this.list_sheep){
                sheep.SetSpeed(sheep.GetSpeed()*1.1);
            }
        }
        if (sec > 51 && sec < 61){
            for (Sheep sheep: this.list_sheep){
                sheep.SetSpeed(sheep.GetSpeed()*1.01);
            }
        }
        if (sec >= 61){
            if(sec % 5 == 0){
                for(Sheep sheep: list_sheep){
                    sheep.SetSpeed(sheep.GetSpeed()*1.01);
                }
            }
            if (sec % 30 == 29){
                for(Sheep sheep: list_sheep){
                    sheep.SetSpeed(sheep.GetSpeed() + 1);
                }
            }
            if(sec % 2 == 1){
                list_sheep.add(new Sheep(this.main, this.batch));
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        Settings.setProperty("width", width + "");
        Settings.setProperty("height", height + "");
    }

    @Override
    public void dispose() {
        img.dispose();
        Audio.dispose();


        time = 0;
        startTime = 0;
        lastTime = 0;
        lostTime = 0;

        score = 0;
        count_sheep = 0;
        count_speed = 0;
        count_scarecrow = 0;
        count_added_lifes = 0;

    }

    @Override
    public void show() {
        batch = main.getBatch();
        img = new Texture("Models/untitled.png");
        key = new KeyManager();
        text = new Text();
        audio = new Audio();
        tiles = new Tiles();
        gui = tiles.getTextureRegion();
        effect = new Particle("test.p");
        bg = new Sprite(new Texture("atlas/g4_new.png"));
        Language.getAllStrings();
        Audio.randomStart();
        hero = new Hero(main, batch);

        time = TimeUtils.millis() / 1000;
        startTime = time;
        
        Gdx.input.setInputProcessor(new InputProcessor() {
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}
		});
    }

    public void update() {
        hero.update();
        for (Sheep sheep : list_sheep)
            sheep.update();
        for (Scarecrow scare : list_scarecrow)
            scare.update();
        colSheepHero();
        colSheepScare();
        sheepHoly();
        sheepJoin();
        clearScarecow();
        clearSheep();
    }

    private void sheepJoin() {
        List<Sheep> tmp_sheep = new ArrayList<Sheep>();
        for (Sheep sheep1 : list_sheep) {
            for (Sheep sheep2 : list_sheep) {
                if (sheep1 == sheep2 || tmp_sheep.contains(sheep2))
                    continue;
                float x1 = sheep1.getCenter_x();
                float x2 = sheep2.getCenter_x();
                float y1 = sheep1.getCenter_y();
                float y2 = sheep2.getCenter_y();
                if (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) < 20) {
                    tmp_sheep.add(sheep1);
                    sheep1.joinSheeps();
                    sheep1.SetMass(sheep1.GetMass() + sheep2.GetMass());
                    sheep2.SetX(-1000);
                    sheep2.SetY(-1000);

                }
            }
        }
    }

    private void colSheepHero() {
        /*
            Function for check collision Hero with sheep and repale
            Uses `list_sheep`, get<Class>()-functions and little Java.Math
         */
        for (Sheep sheep : list_sheep) {
            if (Math.sqrt(Math.pow(sheep.getCenter_x() - hero.getCenter_x(), 2) + Math.pow(sheep.getCenter_y() - hero.getCenter_y(), 2)) < 50) {
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
                if (Math.sqrt(Math.pow(sheep.GetX() - scare.GetX(), 2) + Math.pow(sheep.GetY() - scare.GetY(), 2)) < 80) {
                    float coor_x = scare.GetX() - sheep.GetX();
                    float coor_y = scare.GetY() - sheep.GetY();
                    sheep.SetX((int) (sheep.GetX() - coor_x));
                    sheep.SetY((int) (sheep.GetY() - coor_y));
                }
            }
    }

    public void sheepHoly() {
        for (Sheep sheep : list_sheep) {
            if (Math.sqrt(Math.pow(sheep.getCenter_x() - x_center, 2) + Math.pow(sheep.getCenter_y() - y_center, 2)) < 50) {
                hero.minusLife(sheep.GetMass());
                sheep.SetX(-1000);
                sheep.SetY(-1000);
            }
        }
    }

    public void addScarecrow(int x, int y) {
        this.list_scarecrow.add(new Scarecrow(main, batch, x, y));
    }

    private void clearScarecow() {
        List<Scarecrow> tmp_scarecrow = new ArrayList<Scarecrow>();
        for (Scarecrow scare : list_scarecrow)
            if (scare.GetX() == -1000 && scare.GetY() == -1000)
                tmp_scarecrow.add(scare);
        for (Scarecrow scare : tmp_scarecrow)
            list_scarecrow.remove(scare);
    }

    private void clearSheep() {

        List<Sheep> tmp_sheep = new ArrayList<Sheep>();
        for (Sheep sheep : list_sheep)
            if (sheep.GetX() == -1000 && sheep.GetY() == -1000)
                tmp_sheep.add(sheep);
        for (Sheep sheep : tmp_sheep)
            list_sheep.remove(sheep);

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

