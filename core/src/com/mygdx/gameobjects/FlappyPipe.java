package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class FlappyPipe extends FlappyScrollable {
    private Random random;

    private Rectangle skullUp, skullDown, barUp, barDown;
    public static final int VERTICAL_GAP = 45, SKULL_WIDTH = 24, SKULL_HEIGHT = 11;
    private float groundY;


    public FlappyPipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);

        this.groundY = groundY;
        random = new Random();
        skullDown = new Rectangle();
        skullUp = new Rectangle();
        barDown = new Rectangle();
        barUp = new Rectangle();
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + VERTICAL_GAP, width,groundY - (position.y + height + VERTICAL_GAP));

        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y, SKULL_WIDTH, SKULL_HEIGHT);
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);

        height = random.nextInt(90) + 15;
    }

    public Rectangle getSkullUp() {
        return skullUp;
    }

    public Rectangle getSkullDown() {
        return skullDown;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Rectangle getBarDown() {
        return barDown;
    }
}
