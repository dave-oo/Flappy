package com.mygdx.world;

import com.mygdx.gameobjects.FlappyBird;
import com.mygdx.gameobjects.FlappyScrollHandler;

import sun.security.action.PutAllAction;

public class FlappyWorld {
    private FlappyBird fBird;
    private FlappyScrollHandler flappyScrollHandler;

    public FlappyWorld(int midPointY) {
        fBird = new FlappyBird(33, midPointY - 5, 17, 12);
        flappyScrollHandler = new FlappyScrollHandler(midPointY + 66);
    }

    public void update(float delta) {
        fBird.update(delta);
        flappyScrollHandler.update(delta);
    }

    public FlappyBird getBird() {
        return fBird;
    }

    public FlappyScrollHandler getFlappyScrollHandler() {
        return flappyScrollHandler;
    }
}
