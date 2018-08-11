package com.sgstudio.sgs02.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.main.Main;
import com.sgstudio.sgs02.menu.Menu;

public class AboutSGS implements Screen{
    private Main main;
    private SpriteBatch batch;
    private Texture back1;
    private Texture back2;
    private Texture back3;
    private Texture text;
    private Texture title;
    private int posofx;
    private int posofy;
    private int posoftextx;
    private int posoftexty;
    private int kost;
    private static boolean Moved = false;
    private static boolean Pressed = false;
    private static boolean Play = true;
    private Menu menu;
    private Music sound;

    public AboutSGS(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        sound = Gdx.audio.newMusic(Gdx.files.internal("audio/music/BlueCoach.ogg"));
        sound.setLooping(true);
        sound.setVolume(0.5f);
        sound.play();

        batch = main.getBatch();
        back1 = new Texture("pashasimages/back1.png");
        back2 = new Texture("pashasimages/back2.png");
        back3 = new Texture("pashasimages/back3.png");
        text = new Texture("pashasimages/text.png");
        title = new Texture("pashasimages/title.png");
        posofx = 50;
        posofy = 50;
        posoftextx = 50;
        posoftexty = 100;
        kost = -450;
        menu = new Menu(main);
        Gdx.input.setInputProcessor(new InputProcessor(){

            @Override
            public boolean keyDown(int keycode) { return false; }

            @Override
            public boolean keyUp(int keycode) { return false; }

            @Override
            public boolean keyTyped(char character) { return false; }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if(((Gdx.input.getX() <= back1.getWidth() + posofx)&&
                        (Gdx.input.getX() >= posofx)) &&

                        ((Gdx.input.getY() <= back1.getHeight() + posofy - kost))&&
                        (Gdx.input.getY() >= posofy - kost)){
                    Pressed = true;
                } else {
                    Pressed = false;
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if(Moved && Pressed){
                    main.setScreen(main.menu);
                } else {
                    Play = !Play;
                }
                Pressed = false;
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                if(((Gdx.input.getX() <= back1.getWidth() + posofx)&&
                        (Gdx.input.getX() >= posofx)) &&

                        ((Gdx.input.getY() <= back1.getHeight() + posofy - kost))&&
                        (Gdx.input.getY() >= posofy - kost)){
                    Moved = true;
                } else {
                    Moved = false;
                }
                return false;
            }

            @Override
            public boolean scrolled(int amount) { return false; }

        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(title,
                Gdx.graphics.getWidth() / 2 - title.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 + 250 - title.getHeight() / 2,
                title.getWidth(),
                title.getHeight() );

        batch.draw(text,
                posoftextx,
                posoftexty,
                text.getWidth(),
                text.getHeight() );

        if(!Moved){
            batch.draw(back1,
                    posofx,
                    posofy,
                    back1.getWidth(),
                    back1.getHeight());
        } else if (Pressed && Moved) {
            batch.draw(back2,
                    posofx,
                    posofy,
                    back2.getWidth(),
                    back2.getHeight());
        } else {
            batch.draw(back3,
                    posofx,
                    posofy,
                    back3.getWidth(),
                    back3.getHeight());
        }

        if(Gdx.input.isButtonPressed(0)){
            if (((Gdx.input.getX() <= back1.getWidth() + posofx)&&
                    (Gdx.input.getX() >= posofx)) &&

                    ((Gdx.input.getY() <= back1.getHeight() + posofy - kost))&&
                    (Gdx.input.getY() >= posofy - kost)){
                main.setScreen(main.menu);
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {
        sound.dispose();
    }

}

