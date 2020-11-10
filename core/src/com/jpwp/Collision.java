package com.jpwp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Collision extends Rectangle {

    private Texture texture;

    public Collision(Texture texture, float x , float y)
    {
        super();
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    public Collision(Texture texture)
    {
        this(texture, 0,0);
    }
    public Texture getTexture()
    {
        return texture;
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, x, y);
    }

}
