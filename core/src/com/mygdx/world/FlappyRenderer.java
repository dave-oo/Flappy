package com.mygdx.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.gameobjects.FlappyBird;
import com.mygdx.gameobjects.FlappyGrass;
import com.mygdx.gameobjects.FlappyPipe;
import com.mygdx.gameobjects.FlappyScrollHandler;
import com.mygdx.helpers.AssetLoader;

import sun.font.TextRecord;

public class FlappyRenderer {
    private FlappyWorld myWorld;
    private OrthographicCamera oCamera;
    private ShapeRenderer sRenderer;
    private SpriteBatch sBatch;
    private FlappyScrollHandler flappyScrollHandler;
    private FlappyGrass frontGrass, backGrass;
    private FlappyPipe pipe1, pipe2, pipe3;

    private int gameHeight, midPointY;

    private FlappyBird fBird;
    private TextureRegion background, grass, birdMid, birdDown, birdUp, skullDown, skullUp, bar;
    private Animation birdAnim;

    public FlappyRenderer(FlappyWorld flappyWorld, int gameHeigth, int midPointY) {
        myWorld = flappyWorld;
        this.gameHeight = gameHeigth;
        this.midPointY = midPointY;

        oCamera = new OrthographicCamera();
        oCamera.setToOrtho(true, 136, 204);

        sBatch = new SpriteBatch();
        sBatch.setProjectionMatrix(oCamera.combined); 

        sRenderer = new ShapeRenderer();
        sRenderer.setProjectionMatrix(oCamera.combined);

        initItems();
    }

    private void initItems() {
        fBird = myWorld.getBird();
        background = AssetLoader.background;
        grass = AssetLoader.grass;
        birdMid = AssetLoader.bird;
        birdDown = AssetLoader.birdDown;
        birdUp = AssetLoader.birdUp;
        skullDown = AssetLoader.skullDown;
        skullUp = AssetLoader.skullUp;
        bar = AssetLoader.bar;
        birdAnim = AssetLoader.birdAnimation;
        flappyScrollHandler = myWorld.getFlappyScrollHandler();
        frontGrass = flappyScrollHandler.getFrontGrass();
        backGrass = flappyScrollHandler.getBackGrass();
        pipe1 = flappyScrollHandler.getPipe1();
        pipe2 = flappyScrollHandler.getPipe2();
        pipe3 = flappyScrollHandler.getPipe3();
    }

    private void drawGrass() {
        sBatch.draw(grass, frontGrass.getX(), frontGrass.getY(), frontGrass.getWidth(), frontGrass.getHeight());
        sBatch.draw(grass, backGrass.getX(), backGrass.getY(), backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        sBatch.draw(skullUp, pipe1.getX() - 1, pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        sBatch.draw(skullDown, pipe1.getX() - 1, pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        sBatch.draw(skullUp, pipe2.getX() - 1, pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        sBatch.draw(skullDown, pipe2.getX() - 1, pipe2.getY() + pipe2.getHeight() + 45, 24, 14);


        sBatch.draw(skullUp, pipe3.getX() - 1, pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        sBatch.draw(skullDown, pipe3.getX() - 1, pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        sBatch.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(), pipe1.getHeight());
        sBatch.draw(bar, pipe1.getX(),pipe1.getY() + pipe1.getHeight() + 45, pipe1.getWidth(),midPointY + 66 - (pipe1.getHeight() + 45));

        sBatch.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(), pipe2.getHeight());
        sBatch.draw(bar, pipe2.getX(),pipe2.getY() + pipe2.getHeight() + 45, pipe2.getWidth(),midPointY + 66 - (pipe2.getHeight() + 45));

        sBatch.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(), pipe3.getHeight());
        sBatch.draw(bar, pipe3.getX(),pipe3.getY() + pipe3.getHeight() + 45, pipe3.getWidth(),midPointY + 66 - (pipe3.getHeight() + 45));
    }

    public void render(float runTime) {
        sRenderer.begin(ShapeRenderer.ShapeType.Filled);

        sRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        sRenderer.rect(0, 0, 136, midPointY + 66);

        sRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        sRenderer.rect(0, midPointY + 66, 136, 11);

        sRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        sRenderer.rect(0, midPointY + 77, 136, 52);

        sRenderer.end();

        sBatch.begin();
        sBatch.disableBlending();
        sBatch.draw(background, 0, midPointY + 23, 136, 43);

        drawGrass();
        drawPipes();

        sBatch.enableBlending();

        drawSkulls();

        if (fBird.shouldNotFlap()) {
            sBatch.draw(
                    birdMid,
                    fBird.getX(),
                    fBird.getY(),
                    fBird.getWidth() / 2f,
                    fBird.getHeight() / 2f,
                    fBird.getWidth(),
                    fBird.getHeight(),
                    1,
                    1,
                    fBird.getRotation()
            );
        } else {
            sBatch.draw(
                    (TextureRegion) birdAnim.getKeyFrame(runTime),
                    fBird.getX(),
                    fBird.getY(),
                    fBird.getWidth() / 2f,
                    fBird.getHeight() / 2f,
                    fBird.getWidth(),
                    fBird.getHeight(),
                    1,
                    1,
                    fBird.getRotation()
            );
        }

        sBatch.end();

        sRenderer.begin(ShapeRenderer.ShapeType.Filled);

        sRenderer.setColor(Color.RED);

        sRenderer.circle(fBird.getBoundingCircle().x, fBird.getBoundingCircle().y, fBird.getBoundingCircle().radius);

        sRenderer.rect(pipe1.getBarUp().x, pipe1.getBarUp().y, pipe1.getBarUp().width, pipe1.getBarUp().height);
        sRenderer.rect(pipe2.getBarUp().x, pipe2.getBarUp().y, pipe2.getBarUp().width, pipe2.getBarUp().height);
        sRenderer.rect(pipe3.getBarUp().x, pipe3.getBarUp().y, pipe3.getBarUp().width, pipe3.getBarUp().height);

        // Bar down for pipes 1 2 and 3
        sRenderer.rect(pipe1.getBarDown().x, pipe1.getBarDown().y, pipe1.getBarDown().width, pipe1.getBarDown().height);
        sRenderer.rect(pipe2.getBarDown().x, pipe2.getBarDown().y, pipe2.getBarDown().width, pipe2.getBarDown().height);
        sRenderer.rect(pipe3.getBarDown().x, pipe3.getBarDown().y, pipe3.getBarDown().width, pipe3.getBarDown().height);

        // Skull up for Pipes 1 2 and 3
        sRenderer.rect(pipe1.getSkullUp().x, pipe1.getSkullUp().y, pipe1.getSkullUp().width, pipe1.getSkullUp().height);
        sRenderer.rect(pipe2.getSkullUp().x, pipe2.getSkullUp().y, pipe2.getSkullUp().width, pipe2.getSkullUp().height);
        sRenderer.rect(pipe3.getSkullUp().x, pipe3.getSkullUp().y, pipe3.getSkullUp().width, pipe3.getSkullUp().height);

        // Skull down for Pipes 1 2 and 3
        sRenderer.rect(pipe1.getSkullDown().x, pipe1.getSkullDown().y, pipe1.getSkullDown().width, pipe1.getSkullDown().height);
        sRenderer.rect(pipe2.getSkullDown().x, pipe2.getSkullDown().y, pipe2.getSkullDown().width, pipe2.getSkullDown().height);
        sRenderer.rect(pipe3.getSkullDown().x, pipe3.getSkullDown().y, pipe3.getSkullDown().width, pipe3.getSkullDown().height);

        sRenderer.end();
    }
}
