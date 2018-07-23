package com.sgstudio.sgs02.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Класс для управления эффектами
 * @author Yarik
 * @version 0.1
 */
public class Particle {
	private ParticleEffect particle;
	
	/**
	 * Инициализация класса
	 * @param particle - Название эффекта
	 */
	public Particle(String particle){
		this.particle = new ParticleEffect();
		this.particle.load(Gdx.files.internal("effects/p/" + particle), Gdx.files.internal("effects/img"));
		this.particle.start();
	}
	
	/**
	 * Устанавливает координаты эффекта
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.particle.setPosition(x, y);
	}
	
	/**
	 * Рисует эффект
	 * @param batch
	 * @param delta
	 */
	public void draw(SpriteBatch batch, float delta) {
		this.particle.draw(batch, delta);
	}
	
	/**
	 * Выдет эффект
	 * @return ParticleEffect
	 */
	public ParticleEffect getParticle() {
		return this.particle;
	}
}
