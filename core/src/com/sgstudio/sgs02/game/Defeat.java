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
    private KeyManager key;
    private Text text;
    public long time;
    public long startTime;
    private long lastTime;
    public long lostTime;

    public Defeat(Main main) {
        this.main = main;

        staticCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        staticCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
    }

    @Override
    public void show() {
    	Audio.Attenuation(1);
        time = (int)TimeUtils.millis() / 1000;
        batch = main.getBatch();
        key = new KeyManager();
        text = new Text();
        bg = new Sprite(new Texture("menu/result.png"));
        Language.getAllStrings();
        Gdx.input.setInputProcessor(new InputProcessor() {

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean keyDown(int keycode) {
                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
    	if(!Audio.isPlayedMusic())
    		Audio.Attenuation(5);
    	
    	
    	double sec = TimeUtils.millis() / 1000 - this.startTime;

        if (sec <= 0 || sec <= this.lastTime)
            return;
        else
            this.lastTime = TimeUtils.millis() / 1000 - this.startTime;
    	
        staticCamera.update();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(staticCamera.combined);
        batch.begin();
        batch.draw(bg, 0, 0, 1280, 720);

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
                "Adding Lives: " + main.mainLevel.count_added_lifes + " times","","Count of sheep: " + main.mainLevel.count_sheep);


        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {

    }

}
