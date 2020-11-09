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
	private Collision Koszyk;
	private Collision BG;
	private Collision Jablko;

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
		Koszyk = new Collision(basket);
		BG = new Collision(bg);
		Jablko	= new Collision(apple);
		Koszyk.x = 50;
		Koszyk.y = 100;
		Jablko.x = 300;
		Jablko.y = 100;
		Koszyk.height = Koszyk.getTexture().getHeight();
		Koszyk.width = Koszyk.getTexture().getWidth();
		Jablko.height = Koszyk.getTexture().getHeight();
		Jablko.width = Koszyk.getTexture().getWidth();
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
		batch.draw(Koszyk.getTexture(), Koszyk.x, Koszyk.y);
		batch.draw(Jablko.getTexture(), Jablko.x, Jablko.y);
		batch.end();
	}

	private void update()
	{
		if(Gdx.input.isKeyPressed(Input.Keys.A))
		{
			Koszyk.x -=10;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D))
		{
			Koszyk.x +=10;
		}

		if(Koszyk.overlaps(Jablko))
		{
			Gdx.app.exit();
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
