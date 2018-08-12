package com.sgstudio.sgs02.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.main.Main;
import com.sgstudio.sgs02.menu.Menu;

public class AboutSGS implements Screen{
    private Main main;
    private Menu menu;
    
    private Sprite about;
    
    private SpriteBatch batch;

    public AboutSGS(Main main) {
        this.main = main;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        about = new Sprite(new Texture("menu/about.png"));
    	
        menu = new Menu(main);
        Gdx.input.setInputProcessor(new InputProcessor(){

            @Override
            public boolean keyDown(int keycode) {
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
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            	System.out.println("x: " + screenX + "; y: " + screenY + "; p: " + pointer);
            	if(screenX >= 45 && screenX <= 188)
            		System.out.println("OK");
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
            	return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
            	return false;
            }

        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        
        batch.draw(about, 0, 0, 1280, 720);
        
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

