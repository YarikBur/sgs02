package com.sgstudio.sgs02.menu;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.sgstudio.sgs02.utils.*;
import com.sgstudio.sgs02.main.Main;
import com.sgstudio.sgs02.utils.Tiles;

public class Menu implements Screen {
    private Map<String, TextureRegion> atlasMenu;
    private static Map<String, TextureRegion> atlasSound;
    private boolean Moved[] = {false, false, false, false, false};
    private boolean Pressed[] = {false, false, false, false, false};
    private boolean Play = true;

    private final Main main;
    private Tiles tiles;

    private SpriteBatch batch;

    BitmapFont miniFont;

    private static Music sound;
    //private static MusicGame music;

    private OrthographicCamera camera;

    public Menu(final Main main) {
        this.main = main;
    }

    private float width, height;

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        main.setScreen(main.game);

        Gdx.input.setInputProcessor(new InputProcessor(){
            @Override
            public boolean keyDown(int keycode) { return false; }

            @Override
            public boolean keyUp(int keycode) { return false; }

            @Override
            public boolean keyTyped(char character) { return false; }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return true;
           }

            @Override
            public boolean scrolled(int amount) { return false; }
        });
    }

    public static Map<String, TextureRegion> getAtlasSound(){ return atlasSound; }


    private void renderMenu(){

    }


    @Override
    public void render(float delta) {

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
