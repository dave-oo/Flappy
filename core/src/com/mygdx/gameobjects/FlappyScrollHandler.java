package com.mygdx.gameobjects;

public class FlappyScrollHandler {
    private FlappyGrass frontGrass, backGrass;
    private FlappyPipe pipe1, pipe2, pipe3;

    public static final int SPEED_SCROLL = -59, GAP_PIPE = 49;

    public FlappyScrollHandler(float positionY) {
        frontGrass = new FlappyGrass(0, positionY, 143, 11, SPEED_SCROLL);
        backGrass = new FlappyGrass(frontGrass.getTailX(), positionY, 143, 11, SPEED_SCROLL);
        pipe1 = new FlappyPipe(210, 0, 22, 60, SPEED_SCROLL, positionY);
        pipe2 = new FlappyPipe(pipe1.getTailX() + GAP_PIPE, 0, 22, 60, SPEED_SCROLL, positionY);
        pipe3 = new FlappyPipe(pipe2.getTailX() + GAP_PIPE, 0, 22, 60, SPEED_SCROLL, positionY);
    }

    public void update(float delta) {
        frontGrass.update(delta);
        backGrass.update(delta);
        pipe1.update(delta);
        pipe2.update(delta);
        pipe3.update(delta);

        if (pipe1.isScrolledLeft) {
            pipe1.reset(pipe3.getTailX() + GAP_PIPE);
        }

        if (pipe2.isScrolledLeft) {
            pipe2.reset(pipe1.getTailX() + GAP_PIPE);
        }

        if (pipe3.isScrolledLeft) {
            pipe3.reset(pipe2.getTailX() + GAP_PIPE);
        }

        if (frontGrass.isScrolledLeft) {
            frontGrass.reset(backGrass.getTailX());
        }

        if (backGrass.isScrolledLeft) {
            backGrass.reset(frontGrass.getTailX());
        }
    }

    public FlappyPipe getPipe1() {
        return pipe1;
    }

    public FlappyPipe getPipe2() {
        return pipe2;
    }

    public FlappyPipe getPipe3() {
        return pipe3;
    }

    public FlappyGrass getFrontGrass() {
        return frontGrass;
    }

    public FlappyGrass getBackGrass() {
        return backGrass;
    }
}
