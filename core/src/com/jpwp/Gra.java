package com.jpwp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
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
	private float timer;
	public static int score;
	public static int streak;
	public static int health;
	public static int level;
	public static int total_score;
	public static double czas;
	public static double move;
	public static int state = 3;
	BitmapFont font;
	ArrayList<Food> lista = new ArrayList<>();
	ArrayList<Food> remov = new ArrayList<>();
	Texture[] good, bad;
	Menu menu;
	Pause pause;
	Game game;
	Nxtlvl next;

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
		menu = new Menu(wood);
		pause = new Pause(wood);
		game = new Game();
		next = new Nxtlvl(wood);
	}

	public void background()
	{
		batch.begin();
		Bg.draw(batch);
		batch.end();
	}

	public void start()
	{
		score = 0;
		streak = 0;
		health = 3;
		level = 1;
		total_score = 0;
		czas = 1.5;
		move = 15;
		Food.predkosc = 1;
	}

	public void scoreboard()
	{
		batch.begin();
		Wood.draw(batch);
		batch.end();
	}

	public void next_level()
	{
		score = 0;
		batch.begin();
		font.draw(batch, "Twoj wynik: "+total_score+"", 600, 600);
		font.draw(batch, "Zycia: "+ health +"", 550, 600);
		batch.end();
	}

	public void ending()
	{
		batch.begin();
		font.draw(batch, "Twoj wynik: "+total_score+"", 600, 500);
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
				}
			}


			if(lista.get(i).overlaps(koszyk))
			{
				remov.add(lista.get(i));
				if(lista.get(i).healthy) {
					score++;
					streak++;
					total_score++;
					if (streak > 3)
					{
						score++;
						total_score++;
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
		Gdx.gl.glClearColor( 0, 1, 0, 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

		switch (state)
		{
			case 1:
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
					state = 5;
				}
				else if (score>1)
				{
					state = 4;
				}
				game.draw();
				Gdx.input.setInputProcessor(game);

				break;

			case 2:
				pause.draw();
				Gdx.input.setInputProcessor(pause);
				break;

			case 3:
				menu.draw();
				Gdx.input.setInputProcessor(menu);
				break;

			case 4:
				next.draw();
				Gdx.input.setInputProcessor(next);
				next_level();
				remov.clear();
				lista.clear();
				break;

			case 5:
				menu.draw();
				Gdx.input.setInputProcessor(menu);
				ending();
				remov.clear();
				lista.clear();
				break;

			case 6:
				start();
				remov.clear();
				lista.clear();
				state = 1;
				break;
			default:
				state = 3;
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
