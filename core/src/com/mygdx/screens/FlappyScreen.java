package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.helpers.InputHandler;
import com.mygdx.world.FlappyRenderer;
import com.mygdx.world.FlappyWorld;

public class FlappyScreen implements Screen {
    private FlappyWorld flappyWorld;
    private FlappyRenderer flappyRenderer;
    private float runTime = 0;

    public FlappyScreen() {
        float sWidth = Gdx.graphics.getWidth();
        float sHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = sHeight / (sWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        flappyWorld = new FlappyWorld(midPointY);
        flappyRenderer = new FlappyRenderer(flappyWorld, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHandler(flappyWorld.getBird()));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        flappyWorld.update(delta);
        flappyRenderer.render(runTime);
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
