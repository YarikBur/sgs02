package com.sgstudio.sgs02.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.sgstudio.sgs02.main.Main;
import com.sgstudio.sgs02.menu.Menu;
import com.sgstudio.sgs02.utils.Language;
import com.sgstudio.sgs02.utils.Text;
import com.sgstudio.sgs02.utils.audio.Audio;
import com.sgstudio.sgs02.utils.controller.KeyManager;

public class Defeat implements Screen {

    private final OrthographicCamera staticCamera;
    private Main main;
    SpriteBatch batch;
    Sprite defeat;
    @SuppressWarnings("unused")
	private Menu menu;
    private Sprite bg;
    @SuppressWarnings("unused")
	private Audio audio;
    private KeyManager key;
    private Text text;
    long time;

    public Defeat(Main main) {
        this.main = main;

        staticCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        staticCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
    }

    @Override
    public void show() {
        time = (int)TimeUtils.millis() / 1000;
        batch = main.getBatch();
        key = new KeyManager();
        text = new Text();
        audio = new Audio();
        bg = new Sprite(new Texture("menu/about.png"));
        Language.getAllStrings();
        Audio.randomStart();
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

    @Override
    public void render(float delta) {
        staticCamera.update();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(staticCamera.combined);
        batch.begin();
        bg.draw(batch);

        if (key.getPressedAny() && (int)TimeUtils.millis() / 1000  - time > 2)
        {
            main.mainLevel.dispose();
            main.mainLevel = new MainLevel(main);
            Audio.dispose();
            main.setScreen(main.menu);
        }

        text.writeUpperCenter(batch, "You got " + main.mainLevel.lostTime + " points","","Your hero used:",
                "Fast Running: " + main.mainLevel.count_speed + " times",
                "Inserting Scarecrow: " + main.mainLevel.count_scarecrow + " times",
                "Adding Lifes: " + main.mainLevel.count_added_lifes + " times","","Count of sheep: " + main.mainLevel.count_sheep);


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
