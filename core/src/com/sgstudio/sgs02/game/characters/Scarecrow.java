package com.sgstudio.sgs02.game.characters;

public class Scarecrow {
	private int range;
	private int x;
	private int y;
	
	void SetX(int x){
		this.x = x;
		}
	void SetY(int y){
		this.y = y;
		}
	void SetRange(int range){
		this.range = range;
		}
	
	int GetX(){
		return x;
		}
	int GetY(){
		return y;
		}
	int GetRange(){
		return range;
		}
}
