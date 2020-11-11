package com.jpwp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class Gra extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture basket;
	private Texture bg;
	private Texture wood;
	private Collision koszyk;
	private Collision Bg;
	private Collision Wood;
	private Collision screen;
	private State state = State.run;
	private float timer;
	private int score = 0;
	private int streak = 0;
	private int health = 3;
	private int level = 1;
	private int total_score;
	private double czas = 1.5;
	private double move = 15;
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
		screen = new Collision(wood, 0, 100);
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

	public enum State {
		pause,
		run,
		resume,
		end
	}

	public void menu()
	{
		batch.begin();
		screen.draw(batch);
		batch.end();
	}

	public void levels()
	{
		Food.predkosc += 1;
		czas = czas/1.1;
		level++;
		move += 2;
		menu();
	}

	public void ending()
	{
		batch.begin();
		screen.draw(batch);
		font.draw(batch, "Twoj wynik: "+total_score+"", 600, 600);
		font.draw(batch, "Zycia: "+ 0 +"", 200, 50);
		batch.end();

	}

	public void over()
	{
		for (int i = 0; i < lista.size(); i++) {

			if(lista.get(i).y < 100)
			{
				remov.add(lista.get(i));
				if(lista.get(i).healthy)
				{
					streak = 0;
					health--;
					if(health <= 0)
					{
						ending();
					}
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
						total_score++;
					}
					if (score>20)
					{
						score = 0;
						levels();
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
		switch (state)
		{
			case run:
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
				font.draw(batch, "Poziom: "+level+"", 350, 50);
				batch.end();
				if(health <= 0)
				{
					this.state = State.end;
				}
				break;

			case pause:
				update();
				background();
				scoreboard();
				menu();
				break;

			case end:
				ending();
				break;

			case resume:
				this.state = State.run;
				break;

			default:
				this.state = State.pause;
				break;
		}
	}

	private void update()
	{
		if(Gdx.input.isKeyPressed(Input.Keys.A) && koszyk.x > 0 )
		{
			koszyk.x -= move;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D) && koszyk.x < (1280-koszyk.width))
		{
			koszyk.x += move;
		}

		timer += Gdx.graphics.getDeltaTime();
		if(timer > czas)
		{
			boolean healthy = MathUtils.random(100 ) <= 66;
			Texture texture;
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
