package com.sgstudio.sgs02.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sgstudio.sgs02.game.AboutSGS;
import com.sgstudio.sgs02.game.MyGame;
import com.sgstudio.sgs02.game.Test;
import com.sgstudio.sgs02.menu.Menu;

public class Main extends Game {

    public MyGame game;
    public Menu menu;
    public Test test;
    //public Defeat defeat;
    //public Victory victory;
    public AboutSGS aboutsgstudio;
    //public Tutorial tutorial;

    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        game = new MyGame(this);
        menu = new Menu(this);
        test = new Test(this);
        aboutsgstudio = new AboutSGS(this);


        setScreen(game);
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose(){

    }

    public SpriteBatch getBatch(){ return batch; }

}

