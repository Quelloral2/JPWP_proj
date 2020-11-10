package com.jpwp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import org.graalvm.compiler.loop.MathUtil;

import java.util.ArrayList;

public class Gra extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture basket;
	private Texture bg;
	private Texture wood;
	private Collision koszyk;
	private Collision Bg;
	private Collision Wood;
	private float timer;
	private int score;
	private int streak;
	private int health = 3;
	BitmapFont font;
	ArrayList<Food> lista = new ArrayList<>();
	ArrayList<Food> remov = new ArrayList<>();
	Texture[] good, bad;

	@Override
	public void create () {
		font = new BitmapFont();
		basket = new Texture("basket.png");
		bg = new Texture("bg.png");
		wood = new Texture("wood.png");
		good = new Texture[]{new Texture("apple.png"), new Texture("banana.png"), new Texture("carrot.png") };
		bad = new Texture[]{new Texture("burger.png"), new Texture("chips.png") };
		batch = new SpriteBatch();
		koszyk = new Collision(basket);
		Bg = new Collision(bg);
		Wood = new Collision(wood, 0, -520);
		koszyk.x = 640-(koszyk.width/2);
		koszyk.y = 100;
	}

	public void background()
	{
		batch.begin();
		Bg.draw(batch);
		batch.end();

	}
	public void scoreboard()
	{
		batch.begin();
		Wood.draw(batch);
		batch.end();
	}

	public void over()
	{
		for (int i = 0; i < lista.size(); i++) {

			if(lista.get(i).y < 0)
			{
				remov.add(lista.get(i));
				if(lista.get(i).healthy)
				{
					streak = 0;
				}
			}


			if(lista.get(i).overlaps(koszyk))
			{
				remov.add(lista.get(i));
				if(lista.get(i).healthy) {
					score++;
					streak++;
					if (streak > 3)
					{
						score++;
					}
				}
				else
				{
					streak = 0;
					health--;
				}
			}
		}
		lista.removeAll(remov);
		remov.clear();
	}


	@Override
	public void render () {
		over();
		update();
		background();
		scoreboard();
		batch.begin();
		koszyk.draw(batch);
		for (int i = 0; i < lista.size(); i++) {
			lista.get(i).draw(batch);
		}
		font.draw(batch, "Wynik: "+score+"", 50, 50);
		font.draw(batch, "Zycia: "+health+"", 200, 50);


		batch.end();

	}

	private void update()
	{
		if(Gdx.input.isKeyPressed(Input.Keys.A) && koszyk.x > 0 )
		{
			koszyk.x -=15;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D) && koszyk.x < (1280-koszyk.width))
		{
			koszyk.x +=15;
		}

		timer += Gdx.graphics.getDeltaTime();
		if(timer > 1)
		{
			boolean healthy = MathUtils.random(100 ) <= 66;
			Texture texture = null;
			if(healthy)
			{
				texture = good[MathUtils.random(good.length-1)];
			}
			else
			{
				texture = bad[MathUtils.random(bad.length-1)];
			}
			lista.add(new Food (texture, healthy));
			timer = 0;
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		basket.dispose();
		bg.dispose();
		wood.dispose();
	}
}
