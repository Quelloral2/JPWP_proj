package com.jpwp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Food extends Collision{

    public static float predkosc = 1;
    public boolean healthy;

    /**
     * metoda do losowania punktu startowego spadajacego obiektu
     * @param texture tekstura obiektu
     * @param healthy boolean healthy mowi programowi, czy dany obiekt jest zaliczany do zdrowego czy niezdrowego jedzenia
     */
    public Food(Texture texture, boolean healthy)
    {
        super(texture);
        this.healthy = healthy;
        this.y = 720;
        this.x = MathUtils.random(1260);
    }

    /**
     * metoda do wyliczenia pozycji obiektu podczas spadania
     * @param batch tekstura z ktora dany obiekt zostanie narysowany
     */
    @Override
    public void draw(SpriteBatch batch) {
        y = y - predkosc;
        super.draw(batch);
    }
}
