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
import com.sgstudio.sgs02.utils.Variables;
import com.sgstudio.sgs02.utils.audio.Audio;
import com.sgstudio.sgs02.utils.controller.KeyManager;

public class Settings implements Screen{
    private Main main;
    @SuppressWarnings("unused")
	private Menu menu;
    
    private Sprite settings;
    private SpriteBatch batch;

    private KeyManager key;
    
    public Settings(Main main) {
        this.main = main;
    }
    
    //Back
    int backX[] = {990, 990, 1210, 1210};
    int backY[] = {340, 265, 265, 340};
    
    int x0;
    int y0;
    
    @Override
    public void show() {

        batch = new SpriteBatch();
        settings = new Sprite(new Texture("menu/settings.png"));
        
        key = new KeyManager();
        
        menu = new Menu(main);
        Gdx.input.setInputProcessor(new InputProcessor(){
        	private boolean slide = false;
        	
        	private boolean screen(int pointsX[], int pointsY[]) {
        		int x1 = pointsX[0], x2 = pointsX[1], x3 = pointsX[2], x4 = x1, x5 = x3, x6 = pointsX[3];
        	    int y1 = pointsY[0], y2 = pointsY[1], y3 = pointsY[2], y4 = y1, y5 = y3, y6 = pointsY[3];
        	    
            	boolean screen = false;
            	
            	if(((x1-x0)*(y2-y1)-(x2-x1)*(y1-y0)) < 0 || ((x2-x0)*(y3-y2)-(x3-x2)*(y2-y0)) < 0 || ((x3-x0)*(y1-y3)-(x1-x3)*(y3-y0)) < 0)
            		screen = false;
            	else
            		screen = true;
            	if(!screen)
	            	if(((x4-x0)*(y5-y4)-(x5-x4)*(y4-y0)) < 0 || ((x5-x0)*(y6-y5)-(x6-x5)*(y5-y0)) < 0 || ((x6-x0)*(y4-y6)-(x4-x6)*(y6-y0)) < 0)
	            		screen = false;
	            	else
	            		screen = true;
            	return screen;
            }
        	
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
            	x0 = screenX;
            	y0 = screenY;
            	
            	if(screen(backX, backY))
            		slide = true;
            	
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            	if(slide && screen(backX, backY))
            		main.setScreen(main.menu);
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
        
        batch.draw(settings, 0, 0, 1280, 720);
        
        if(key.getJustPressedLeft())
        	Audio.setVolume(Variables.stringToInt(com.sgstudio.sgs02.utils.Settings.getProperty("volume"))-10);
        if(key.getJustPressedRight())
        	Audio.setVolume(Variables.stringToInt(com.sgstudio.sgs02.utils.Settings.getProperty("volume"))+10);
        if(key.getJustPressedUp())
        	com.sgstudio.sgs02.utils.Settings.setProperty("mute", "true");
        if(key.getJustPressedDown())
        	com.sgstudio.sgs02.utils.Settings.setProperty("mute", "false");
        
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

