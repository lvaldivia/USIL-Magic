package com.usil.mariobros.utils.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Luis on 23/11/2016.
 */

public class PhysicsState extends State {

    protected World world;
    protected Vector2 gravity;
    protected Box2DDebugRenderer b2dr;
    public PhysicsState(SpriteBatch batch){
        super(batch);
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        world.step(1 / 60f, 6, 2);
    }
}
