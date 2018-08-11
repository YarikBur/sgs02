package com.sgstudio.sgs02.game.characters;

public class Hero {
	private int potatoes;
	private int velocity;
	private int x;
	private int y;

	void SetX(int x){
		this.x = x;
		}
	void SetY(int y){
		this.y = y;
		}
	void SetPotatoes(int potatoes){
		this.potatoes = potatoes;
	}
	void SetVelocity(int velocity){
		this.velocity = velocity;
	}
	
	int GetX(){
		return x;
		}
	int GetY(){
		return y;
		}
	int GetPotatoes(){
		return potatoes;
	}
	int GetVelocity(){
		return velocity;
	}
}
