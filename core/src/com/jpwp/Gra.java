package com.jpwp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gra extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture basket;
	private Texture apple;
	private Texture banana;
	private Texture bg;
	private Texture burger;
	private Texture carrot;
	private Texture chips;
	private Texture wood;
	private Collision Obiekt1;
	private Collision BG;
	
	@Override
	public void create () {
		basket = new Texture("basket.png");
		apple = new Texture("apple.png");
		banana = new Texture("banana.png");
		bg = new Texture("bg.png");
		burger = new Texture("burger.png");
		carrot = new Texture("carrot.png");
		chips = new Texture("chips.png");
		wood = new Texture("wood.png");

		batch = new SpriteBatch();
		Obiekt1 = new Collision(basket);
		BG = new Collision(bg);
		Obiekt1.x = 50;
		Obiekt1.y = 100;
	}

	public void Background()
	{
		batch.begin();
		batch.draw(BG.getTexture(), 0, 0);
		batch.end();
	}

	@Override
	public void render () {
		update();
		Background();
		batch.begin();
		batch.draw(Obiekt1.getTexture(), Obiekt1.x, Obiekt1.y);
		batch.end();
	}

	private void update()
	{
		if(Gdx.input.isKeyPressed(Input.Keys.A))
		{
			Obiekt1.x -=10;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D))
		{
			Obiekt1.x +=10;
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		basket.dispose();
		apple .dispose();
		banana.dispose();
		bg.dispose();
		burger.dispose();
		carrot.dispose();
		chips.dispose();
		wood.dispose();
	}
}
