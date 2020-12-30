package com.jpwp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Menu extends Stage {


    public Menu(Texture texture)
    {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        Image background = new Image(texture);

        table.add(background);
        addActor(table);

        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Button button1 = new TextButton("Nowa gra",mySkin,"small");
        Button button2 = new TextButton("Wyjdz",mySkin,"small");
        Button button3 = new TextButton("Gra edukacyjna 'Zdrowe zywienie'",mySkin,"small");
        button1.setSize(100,50);
        button1.setPosition(590,400);
        button2.setSize(100,50);
        button2.setPosition(590,300);
        button3.setSize(300,50);
        button3.setPosition(490,550);
        button1.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            {
                Gra.state = 6;
            }
        });
        button2.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            {
                System.exit(0);
            }
        });
        addActor(button1);
        addActor(button2);
        addActor(button3);

    }
    @Override
    public void draw()
    {
        super.draw();
    }

}
