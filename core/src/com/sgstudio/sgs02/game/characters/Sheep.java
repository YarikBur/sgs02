package com.sgstudio.sgs02.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.sgstudio.sgs02.main.Main;
import com.sgstudio.sgs02.utils.Tiles;


public class Sheep {
    private int mass;
    private float speed = 1;
    private float x;
    private float y;
    private float img_width = 222 / 5;
    private float img_height = 152 / 5;
    private float center_x;
    private float center_y;

    public float stateTime;

    private Texture texture;
    public Tiles tiles;
    public Animation<TextureRegion> anim;

    private float lostX;
    private float lostY;

    private float vect_x;
    private float vect_y;

    private int counter;

    private SpriteBatch batch;
    private Main main;
    
    private float angle = 0;
	private Sprite sprite;
//	final private double x_center = 1080;
//  final private double y_center = 1085;
    final private double radius = 910;
    int[] spawnX = {1075, 1260 , 1410, 1480, 1480, 1390, 1215, 1025, 840, 670, 600, 550, 600, 710 ,870};
	int[] spawnY = {1515, 1450 , 1300, 1115, 910, 720, 600, 550, 590, 630, 850, 1040, 1230, 1380 ,1500};
	int RANDOM_NUMBER;

    public int Spawn(int RANDOM_NUMBER) {
    	this.RANDOM_NUMBER = (int) (14 * Math.random());
    	return this.RANDOM_NUMBER;
    }

    public Sheep(Main main, SpriteBatch batch) {
    	tiles = new Tiles();
    	tiles.createAtlas("Models/sheep_animation.png", 2, 1);
    	Texture sheep = new Texture("Models/sheep_animation.png");
    	TextureRegion[][] tmp = TextureRegion.split(sheep, sheep.getWidth(), sheep.getHeight()/2);
    	TextureRegion[] walk = new TextureRegion[2];
    	walk[0] = tmp[0][0];
    	walk[1] = tmp[1][0];
    	anim = new Animation<TextureRegion>(0.45f, walk);
    	stateTime=0f;
    	
        main.mainLevel.count_sheep += 1;
        texture = new Texture("Models/sheep_one.png");
        sprite = new Sprite(texture);
        Spawn(RANDOM_NUMBER);
        x = spawnX[RANDOM_NUMBER];
        y = spawnY[RANDOM_NUMBER];
 /*     x = 1500 * (float) Math.random();
        y = 1500 * (float) Math.random();
        if ((Math.pow(x - main.test.x_center - 10 , 2) + Math.pow(y - main.test.y_center - 10 , 2)) >= Math.pow(radius , 2)){
        	if ((x < 1520) && (x > 570)){
        		if ((x - main.test.x_center) < 0 ){
        			x = (float) ((main.test.x_center - radius) * Math.random());
        		} else {
        			x = (float) (main.test.x_center + radius + 580 * Math.random());
        		}
        	}
        	if ((y < 1520) && (y > 650)){
        		if ((y - main.test.y_center) < 0 ){
        			y = (float) ((main.test.y_center - radius) * Math.random());
        		} else {
        			y = (float) (main.test.y_center + radius + 580 * Math.random());
        		}
        	}
        }*/
        this.batch = batch;
        this.main = main;

        this.lostX = main.test.x_center;
        this.lostY = main.test.y_center;

        /*
        TODO Add field vect_x and vect_y and change moving to % of vector
        One time (in C-tor) save vector, after that go on it
        */
        this.vect_x = this.lostX - this.x;
        this.vect_y = this.lostY - this.y;

        this.center_x = x + img_width / 2;
        this.center_y = y + img_height / 2;


        this.mass = 1;
    }

    public void render() {
    	stateTime += Gdx.graphics.getDeltaTime();
    	TextureRegion currentFrame = anim.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, x, y, img_width/2, img_height/2, img_width, img_height, 1, 1, angle * MathUtils.radiansToDegrees + 180);
    }

    public void update() {
        this.center_x = x + img_width / 2;
        this.center_y = y + img_height / 2;

        if (Math.sqrt(Math.pow(this.x - lostY, 2) + Math.pow(this.y - lostY, 2)) > 550) {
            this.updateVect();
        }

        this.x += this.vect_x * 0.001 * speed ;
        this.y += this.vect_y * 0.001 * speed ;
        
        angle = MathUtils.atan2(this.vect_y, this.vect_x);
    }

    public void updateVect() {
        this.vect_x = this.lostX - this.x;
        this.vect_y = this.lostY - this.y;
    }

    public void reverseVect() {
        this.vect_x *= -1;
        this.vect_y *= -1;
    }

    public void SetX(int x) {
        this.x = x;
    }

    public void SetY(int y) {
        this.y = y;
    }

    public void SetMass(int mass) {
        this.mass = mass;
    }

    public void SetSpeed(double speed) {
        this.speed = (float)speed;
    }

    public float GetX() {
        return this.x;
    }

    public float GetY() {
        return y;
    }

    public int GetMass() {
        return mass;
    }

    public float GetSpeed() {
        return this.speed;
    }

    public void joinSheeps() {
        this.img_height *= 1.2;
        this.img_width *= 1.2;
    }

    public float getImgWidth() {
        return this.img_width;
    }

    public float getImgHeight() {
        return this.img_height;
    }

    public float getCenter_x() {
        return this.center_x;
    }

    public float getCenter_y() {
        return this.center_y;
    }
}
