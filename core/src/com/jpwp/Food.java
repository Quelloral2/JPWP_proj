package com.jpwp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Food extends Collision{

    public static float predkosc = 1;
    public boolean healthy;

    public Food(Texture texture, boolean healthy)
    {
        super(texture);
        this.healthy = healthy;
        this.y = 720;
        this.x = MathUtils.random(1280);
    }

    @Override
    public void draw(SpriteBatch batch) {
        y = y - predkosc;
        super.draw(batch);
    }
}
