package com.usil.mariobros.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.usil.mariobros.Config;
import com.usil.mariobros.MarioBros;
import com.usil.mariobros.Scenes.Hud;
import com.usil.mariobros.Sprites.Enemies.Enemy;
import com.usil.mariobros.Sprites.Items.Item;
import com.usil.mariobros.Sprites.Items.ItemDef;
import com.usil.mariobros.Sprites.Items.Mushroom;
import com.usil.mariobros.Sprites.Mario;
import com.usil.mariobros.Tools.B2WorldCreator;
import com.usil.mariobros.Tools.WorldContactListener;
import com.usil.mariobros.utils.States.PhysicsState;
import com.usil.mariobros.utils.States.State;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Luis on 8/14/15.
 */
public class PlayScreen extends PhysicsState{
    private MarioBros game;
    private TextureAtlas atlas;
    public static boolean alreadyDestroyed = false;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    public static PlayScreen instance;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private B2WorldCreator creator;
    private Mario player;
    private Music music;
    private Array<Item> items;
    private LinkedBlockingQueue<ItemDef> itemsToSpawn;


    public PlayScreen(SpriteBatch batch){
        super(batch);
        atlas = new TextureAtlas("Mario_and_Enemies.pack");
        gamecam = new OrthographicCamera();

        gamePort = new FitViewport(Config.V_WIDTH / Config.PPM, Config.V_HEIGHT / Config.PPM, gamecam);

        hud = new Hud(batch);
        maploader = new TmxMapLoader();
        map = maploader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1  / Config.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        creator = new B2WorldCreator(this);
        player = new Mario(this);
        world.setContactListener(new WorldContactListener());
        music = MarioBros.manager.get("audio/music/mario_music.ogg", Music.class);
        music.setLooping(true);
        music.setVolume(0.3f);
        items = new Array<Item>();
        itemsToSpawn = new LinkedBlockingQueue<ItemDef>();
        instance = this;
    }

    public void spawnItem(ItemDef idef){
        itemsToSpawn.add(idef);
    }


    public void handleSpawningItems(){
        if(!itemsToSpawn.isEmpty()){
            ItemDef idef = itemsToSpawn.poll();
            if(idef.type == Mushroom.class){
                items.add(new Mushroom(this, idef.position.x, idef.position.y));
            }
        }
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(player.currentState != Mario.State.DEAD) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                player.jump();
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
                player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
                player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }

    }

    public void update(float dt){
        super.update(dt);
        handleSpawningItems();

        player.update(dt);
        for(Enemy enemy : creator.getEnemies()) {
            enemy.update(dt);
            if(enemy.getX() < player.getX() + 224 / Config.PPM) {
                enemy.b2body.setActive(true);
            }
        }

        for(Item item : items)
            item.update(dt);

        hud.update(dt);

        if(player.currentState != Mario.State.DEAD) {
            gamecam.position.x = player.b2body.getPosition().x;
        }

        gamecam.update();
        renderer.setView(gamecam);

    }


    @Override
    public void render(float delta) {
        super.render(delta);
        renderer.render();
        b2dr.render(world, gamecam.combined);
        batch.setProjectionMatrix(gamecam.combined);
        batch.begin();
        player.draw(batch);
        for (Enemy enemy : creator.getEnemies())
            enemy.draw(batch);
        for (Item item : items)
            item.draw(batch);
        batch.end();
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(gameOver()){
            MarioBros.instance.setScreen(new GameOverScreen(batch));
            dispose();
        }

    }

    public boolean gameOver(){
        if(player.currentState == Mario.State.DEAD && player.getStateTimer() > 3){
            return true;
        }
        return false;
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

    }

    public TiledMap getMap(){
        return map;
    }
    public World getWorld(){
        return world;
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

    public Hud getHud(){ return hud; }
}
