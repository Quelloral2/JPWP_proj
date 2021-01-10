package com.jpwp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Nxtlvl extends Stage {

    TextButton button4, button5, button6;

    /**
     * ustawienie wszystkich potrzebnych informacji na ekranie
     * @param texture tekstura ktora bedzie uzywana przez program jako tlo
     */
    public Nxtlvl(Texture texture)
    {
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        Image background = new Image(texture);

        table.add(background);
        addActor(table);
        //ustawienie tekstu wszystkich guzikow
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Button button1 = new TextButton("Nastepny poziom",mySkin,"small");
        Button button2 = new TextButton("Nowa gra",mySkin,"small");
        Button button3 = new TextButton("Wyjdz",mySkin,"small");
        button4 = new TextButton("Twoj wynik: "+Gra.total_score+"",mySkin,"small");
        button5 = new TextButton("Zycia: "+ Gra.health +"",mySkin,"small");
        button6 = new TextButton("Poziom: "+ Gra.level +"",mySkin,"small");
        //ustawienie pozycji guzikow
        button1.setSize(200,50);
        button1.setPosition(540,400);
        button2.setSize(100,50);
        button2.setPosition(590,300);
        button3.setSize(100,50);
        button3.setPosition(590,200);
        button4.setSize(150,50);
        button4.setPosition(565,600);
        button5.setSize(100,50);
        button5.setPosition(590,500);
        button6.setSize(100,50);
        button6.setPosition(590,550);
        button1.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            { //przejscie do nastepnego poziomu
                if (Gra.level < 10 )
                {
                    Gra.state = 1;
                    Food.predkosc += 1;
                    Gra.czas = Gra.czas/1.1;
                    Gra.level++;
                    Gra.move += 2;
                    Gra.streak = 0;
                }
                else
                {
                    Gra.state = 5;
                }
            }
        });
        button2.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            { //wlaczenie nowej gry

                Gra.state = 6;
            }
        });
        button3.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            { //wyjscie z programu
                System.exit(0);
            }
        });
        addActor(button1);
        addActor(button2);
        addActor(button3);
        addActor(button4);
        addActor(button5);
        addActor(button6);
    }

    /**
     * narysowanie wszystkich tekstur i aktualizacja tekstu guzikow
     */
    @Override
    public void draw()
    {
        button4.setText("Twoj wynik: "+Gra.total_score);
        button5.setText("Zycia: "+ Gra.health);
        button6.setText("Poziom: "+ Gra.level);
        super.draw();
    }

}
