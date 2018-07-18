package com.sgstudio.sgs02.utils.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class KeyManager {
	/** @return boolean */
	public boolean getPressedLeft(){
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT))
			return true;
		else
			return false;
	}
	/** @return boolean */
	public boolean getPressedRight(){
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT))
			return true;
		else
			return false;
	}
	/** @return boolean */
	public boolean getPressedUp(){
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP))
			return true;
		else
			return false;
	}
	/** @return boolean */
	public boolean getPressedDown(){
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN))
			return true;
		else
			return false;
	}
	/** @return boolean */
	public boolean getPressedEscape(){
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			return true;
		else
			return false;
	}

	/** @return boolean */
	public boolean getPressedSpace(){
		if(Gdx.input.isKeyPressed(Keys.SPACE))
			return true;
		else
			return false;
	}
	/** @return boolean */
	public boolean getPressedE() {
		if(Gdx.input.isKeyPressed(Keys.E))
			return true;
		else
			return false;
	}
	
	public boolean getJustPressedE() {
		if(Gdx.input.isKeyJustPressed(Keys.E))
			return true;
		else
			return false;
	}
	
	public boolean getJustPressedUp() {
		if(Gdx.input.isKeyJustPressed(Keys.UP))
			return true;
		else
			return false;
	}
	
	public boolean getJustPressedDown() {
		if(Gdx.input.isKeyJustPressed(Keys.DOWN))
			return true;
		else
			return false;
	}
	
	public boolean getJustPressedLeft() {
		if(Gdx.input.isKeyJustPressed(Keys.LEFT))
			return true;
		else
			return false;
	}
	
	public boolean getJustPressedRight() {
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT))
			return true;
		else
			return false;
	}
	
	public boolean getJustPressedT() {
		if(Gdx.input.isKeyJustPressed(Keys.T))
			return true;
		else
			return false;
	}
}
