package com.jpwp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Food extends Collision{

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
        y--;
        super.draw(batch);
    }
}
