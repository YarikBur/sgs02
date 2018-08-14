package com.sgstudio.sgs02.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.main.Main;
import com.sgstudio.sgs02.utils.Text;
import com.sgstudio.sgs02.utils.audio.Audio;

public class Menu implements Screen {

    Text text;
	@SuppressWarnings("unused")
	private Menu m;
    private final Main main;

    private SpriteBatch batch;
    
    private Sprite bg;

    Sprite img_runOff;
    Sprite img_life;
    Sprite img_scrow;

    public Menu(final Main main) {
        this.main = main;
    }
    
    private Sound clic_pressed, clic_released;
    
    //About
    int aboutX[] = {785, 865, 1015, 960};
    int aboutY[] = {510, 505, 600, 620};
    
    //Play
    int playX[] = {515, 485, 655, 680};
    int playY[] = {580, 540, 515, 548};
    
    //Settings
    int settingsX[] = {490, 450, 750, 805};
    int settingsY[] = {650, 600, 545, 580};
    
    //Exit
    int exitX[] = {585, 760, 805, 640};
    int exitY[] = {650, 615, 655, 700};
    
    int x0, y0;
    
	@Override
    public void show() {
    	m = new Menu(main);
    	
    	if(!Audio.isPlayedMusic())
    		Audio.Attenuation(3);
    	Audio.update();

    	text = new Text();
    	
    	bg = new Sprite(new Texture("menu/menu.png"));
    	clic_pressed = Gdx.audio.newSound(Gdx.files.internal("audio/sound/clic_pressed.mp3"));
    	clic_released = Gdx.audio.newSound(Gdx.files.internal("audio/sound/clic_released.mp3"));
    	
    	batch = new SpriteBatch();

        img_scrow = new Sprite(new Texture("Spells/scrow.png"));
        img_runOff = new Sprite(new Texture("Spells/run_on.png"));
        img_life = new Sprite(new Texture("Spells/Life.png"));

        img_runOff.setPosition(1012,720 - 452);
        img_runOff.setSize(82,93);

        img_scrow.setPosition(1094,720 - 452);
        img_scrow.setSize(82, 93);

        img_life.setSize(90,93);
        img_life.setPosition(1173,720 - 452);
    	
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

            	if(screen(aboutX, aboutY)) {
            		clic_pressed.play(Audio.volume());
            		slide = true;
            	} else if(screen(playX, playY)) {
            		clic_pressed.play(Audio.volume());
            		slide = true;
            	} else if(screen(settingsX, settingsY)) {
            		clic_pressed.play(Audio.volume());
            		slide = true;
        		} else if(screen(exitX, exitY)) {
        			clic_pressed.play(Audio.volume());
            		slide = true;
        		} else
            		slide = false;
            	
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            	
            	if(slide && screen(aboutX, aboutY)) {
            		clic_released.play(Audio.volume());
            		main.setScreen(main.aboutsgstudio);
            	} else if(slide && screen(playX, playY)) {
            		clic_released.play(Audio.volume());
            		main.setScreen(main.mainLevel);
            	} else if(slide && screen(settingsX, settingsY)) {
            		clic_released.play(Audio.volume());
            		main.setScreen(main.settings);
            	} else if(slide && screen(exitX, exitY)) {
            		clic_released.play(Audio.volume());
            		Gdx.app.exit();
            	}
            	
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
    	Audio.update();
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(bg, 0, 0, 1280, 720);
        img_runOff.draw(batch);
        img_scrow.draw(batch);
        img_life.draw(batch);
        text.writeUpperRight(batch,
                "_______________","|               ","| Controls:     ","|_______________",
                "|               ","| WASD or Arrows","|  for moving   ","|               ",
                "|  Num  1, 2, 3 ","|   for skills  ","|_______________","","","___________________",
                "|                   ", "|       Skills:     ","|___________________","|                   ",
                "|                   ","|                   ","|                   ","|___________________");
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();
            if (x > 1012 && x < 1094 && y > 359 && y < 452) {
                text.writeLowerRight(batch,  "", "", "", "", "", "", "", "|___________________", "| Cost:  3  points  ",
                        "|                   ", "|   for 10 seconds  ", "|  Update MoveSpeed ","|                   ");

            } else if (x > 1094 && x < 1176 && y > 359 && y < 452){
                text.writeLowerRight(batch,  "", "", "", "", "|___________________", "| P.S. sheep's scare", "|___________________", "| Cost:  4  points  ",
                        "|                   ","| at  hero position ", "|   for 10 seconds  ", "|   Set  Scarecrow  ","|                   ");
            } else if (x > 1176 && y > 359 && y < 452) {
                text.writeLowerRight(batch, "", "", "", "", "", "", "", "|___________________", "| Cost:  10 points  ",
                        "|                   ", "|                   ", "|   Add Extra Life  ", "|                   ");
            } else {
                text.writeLowerRight(batch,  "Put cursor to skills for more info");
            }
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
    	batch.dispose();
    	clic_pressed.dispose();
    	clic_released.dispose();
    }

}
