package com.usil.mariobros.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.usil.mariobros.Config;
import com.usil.mariobros.MarioBros;
import com.usil.mariobros.Sprites.Enemies.Enemy;
import com.usil.mariobros.Sprites.Items.Item;
import com.usil.mariobros.Sprites.Mario;
import com.usil.mariobros.Sprites.Other.FireBall;
import com.usil.mariobros.Sprites.TileObjects.InteractiveTileObject;

/**
 * Created by Luis on 9/4/15.
 */
public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case Config.MARIO_HEAD_BIT | Config.BRICK_BIT:
            case Config.MARIO_HEAD_BIT | Config.COIN_BIT:
                if(fixA.getFilterData().categoryBits == Config.MARIO_HEAD_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit((Mario) fixA.getUserData());
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit((Mario) fixB.getUserData());
                break;
            case Config.ENEMY_HEAD_BIT | Config.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == Config.ENEMY_HEAD_BIT)
                    ((Enemy)fixA.getUserData()).hitOnHead((Mario) fixB.getUserData());
                else
                    ((Enemy)fixB.getUserData()).hitOnHead((Mario) fixA.getUserData());
                break;
            case Config.ENEMY_BIT | Config.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == Config.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case Config.MARIO_BIT | Config.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == Config.MARIO_BIT)
                    ((Mario) fixA.getUserData()).hit((Enemy)fixB.getUserData());
                else
                    ((Mario) fixB.getUserData()).hit((Enemy)fixA.getUserData());
                break;
            case Config.ENEMY_BIT | Config.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).hitByEnemy((Enemy)fixB.getUserData());
                ((Enemy)fixB.getUserData()).hitByEnemy((Enemy)fixA.getUserData());
                break;
            case Config.ITEM_BIT | Config.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == Config.ITEM_BIT)
                    ((Item)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Item)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case Config.ITEM_BIT | Config.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == Config.ITEM_BIT)
                    ((Item)fixA.getUserData()).use((Mario) fixB.getUserData());
                else
                    ((Item)fixB.getUserData()).use((Mario) fixA.getUserData());
                break;
            case Config.FIREBALL_BIT | Config.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == Config.FIREBALL_BIT)
                    ((FireBall)fixA.getUserData()).setToDestroy();
                else
                    ((FireBall)fixB.getUserData()).setToDestroy();
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
