package com.usil.mariobros.utils.Objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Luis on 23/11/2016.
 */

public abstract class PhysicGameObject extends GameObject {
    protected World world;
    protected Vector2 velocity;
    public Body b2body;
    public PhysicGameObject(World world, TextureAtlas atlas) {
        super(atlas);
        velocity = new Vector2();
        this.world = world;
        defineBody();
    }

    public void draw(Batch batch){
        if(!destroyed)
            super.draw(batch);
    }


    public void reverseVelocity(boolean x, boolean y){
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }

    public void destroy(){
        setToDestroy = true;
    }

    public abstract void defineBody();
}
