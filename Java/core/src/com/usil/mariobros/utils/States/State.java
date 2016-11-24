package com.usil.mariobros.utils.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Luis on 23/11/2016.
 */

public class State implements Screen {
    protected Viewport viewport;
    protected OrthographicCamera gamecam;
    protected SpriteBatch batch;
    public State(SpriteBatch batch){
        gamecam = new OrthographicCamera();
        this.batch= batch;
    }
    @Override
    public void show() {

    }

    public void handleInput(float dt){}

    public void update(float dt) {
        handleInput(dt);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
