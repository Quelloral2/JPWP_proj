package com.jpwp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Collision extends Rectangle {

    private Texture texture;

    /**
     * metoda sluzaca do ustalenia szerokosci i wysokosci obiektu
     * @param texture tekstura ktora dany obiekt uzywa
     * @param x szerokosc tekstury
     * @param y wysokosc tekstury
     */
    public Collision(Texture texture, float x , float y)
    {
        super();
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    /**
     * metoda do inicjalizacji tekstur obiektow do ktorych nie potrzeba ustalac szerokosci i wysokosci
     * @param texture tekstura obiektu
     */
    public Collision(Texture texture)
    {
        this(texture, 0,0);
    }

    /**
     * metoda sluzaca do rysowania obiektu
     * @param batch narysowanie obiektu wraz z jego tekstura
     */
    public void draw(SpriteBatch batch){
        batch.draw(texture, x, y);
    }

}
