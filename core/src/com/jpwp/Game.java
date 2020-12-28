package com.jpwp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Game extends Stage {

    public Game()
    {
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Button button = new TextButton("Menu",mySkin,"small");
        button.setSize(100,50);
        button.setPosition(1180,0);
        button.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            {
                Gra.state = 2;
            }
        });
        addActor(button);

    }
    @Override
    public void draw()
    {
        super.draw();
    }

}
