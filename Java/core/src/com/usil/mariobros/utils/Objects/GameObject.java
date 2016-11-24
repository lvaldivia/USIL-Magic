package com.usil.mariobros.utils.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Luis on 23/11/2016.
 */

public abstract class GameObject extends Sprite {
    protected  Array<TextureRegion> frames;
    protected Vector2 position;
    protected TextureAtlas atlas;
    protected boolean setToDestroy;
    protected boolean destroyed;
    protected float stateTime;

    public GameObject(){
        super();
        stateTime = 0;
        setToDestroy = false;
        destroyed = false;
        frames =new Array<TextureRegion>();
        position = new Vector2();
    }

    public GameObject(TextureAtlas atlas){
        this.atlas = atlas;
        stateTime = 0;
        setToDestroy = false;
        destroyed = false;
        frames =new Array<TextureRegion>();
        position = new Vector2();
    }

    public void update(float dt){
        position.x=getX();
        position.y=getY();
    }

    public abstract TextureRegion getFrame(float dt);
}
