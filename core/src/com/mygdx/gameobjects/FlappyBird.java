package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class FlappyBird {
    private Vector2 position, velocity, acceleration;
    private float rotation;
    private int height, width;
    private Circle boundingCircle;

    public FlappyBird(float x, float y, int w, int h) {
        width = w;
        height = h;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        boundingCircle = new Circle();
    }

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) velocity.y = 200;

        position.add(velocity.cpy().scl(delta));

        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

        if (velocity.y < 0) {
            rotation -= 600 * delta;

            if (rotation < -20) {
                rotation = -20;
            }
        }

        if (isFalling()) {
            rotation += 200 * delta;

            if (rotation > 40) {
                rotation = 40;
            }
        }
    }

    public void onTap() {
        velocity.y = -140;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    public boolean shouldNotFlap() {
        return velocity.y > 70;
    }
}
