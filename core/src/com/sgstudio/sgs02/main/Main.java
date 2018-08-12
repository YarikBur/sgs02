package com.sgstudio.sgs02.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.game.AboutSGS;
import com.sgstudio.sgs02.game.MyGame;
import com.sgstudio.sgs02.game.Settings;
import com.sgstudio.sgs02.game.Test;
import com.sgstudio.sgs02.menu.Menu;
import com.sgstudio.sgs02.utils.audio.Audio;

public class Main extends Game {

    Audio audio;

    public MyGame game;
    public Menu menu;
    public Test test;
    //public Defeat defeat;
    //public Victory victory;
    public AboutSGS aboutsgstudio;
    public Settings settings;
    //public Tutorial tutorial;

    private SpriteBatch batch;

    @Override
    public void create() {
        audio = new Audio();

        batch = new SpriteBatch();


        game = new MyGame(this);
        menu = new Menu(this);
        test = new Test(this);
        settings = new Settings(this);
        aboutsgstudio = new AboutSGS(this);

        setScreen(menu);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }

    public SpriteBatch getBatch() {
        return batch;
    }

}

