package com.sgstudio.sgs02.utils;

public class Sheep {
	private int mass;
	private int velocity;
	private int x;
	private int y;
	
	void SetX(int x){
		this.x = x;
		}
	void SetY(int y){
		this.y = y;
		}
	void SetMass(int mass){
		this.mass = mass;
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
	int GetMass(){
		return mass;
	}
	int GetVelocity(){
		return velocity;
	}

	
}
