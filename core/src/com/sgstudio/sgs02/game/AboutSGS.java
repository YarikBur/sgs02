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
    @SuppressWarnings("unused")
	private Menu menu;
    
    private Sprite about;
    
    private SpriteBatch batch;
    
    public AboutSGS(Main main) {
        this.main = main;
    }
    
    int x0, x1 = 55, x2 = 95, x3 = 215, x4 = x1, x5 = x3, x6 = 185;
    int y0, y1 = 400, y2 = 405, y3 = y1, y4 = y1, y5 = y3, y6 = 430;
    
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
            
            boolean down = false;
            
            private boolean back() {
            	boolean back = false;
            	if(((x1-x0)*(y2-y1)-(x2-x1)*(y1-y0)) < 0 || ((x2-x0)*(y3-y2)-(x3-x2)*(y2-y0)) < 0 || ((x3-x0)*(y1-y3)-(x1-x3)*(y3-y0)) < 0)
            		back = false;
            	else
            		back = true;
            	if(!back)
	            	if(((x4-x0)*(y5-y4)-(x5-x4)*(y4-y0)) < 0 || ((x5-x0)*(y6-y5)-(x6-x5)*(y5-y0)) < 0 || ((x6-x0)*(y4-y6)-(x4-x6)*(y6-y0)) < 0)
	            		back = false;
	            	else
	            		back = true;
            	return back;
            }
            
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            	x0 = screenX;
            	y0 = screenY;
            	System.out.print("x: " + screenX + "; y: " + screenY + "; Back: ");
            	System.out.println(back());
            	
            	if(back())
            		down = true;
            	
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            	if(down && back())
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

