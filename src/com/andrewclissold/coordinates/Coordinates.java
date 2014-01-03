package com.andrewclissold.coordinates;

import java.lang.Math;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Coordinates implements ApplicationListener {

    TextureRegion turretImage;
    OrthographicCamera camera;
    SpriteBatch batch;
    Rectangle turret;

    int WIDTH = 800;
    int HEIGHT = 480;
    double topRightAngle, topLeftAngle, bottomLeftAngle, bottomRightAngle;

    @Override
    public void create() {
        Texture turretTexture
            = new Texture(Gdx.files.internal("turret.png"));
        turretImage = new TextureRegion(turretTexture, 0, 0, 32, 32);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        batch = new SpriteBatch();

        turret = new Rectangle();
        turret.x = WIDTH / 2 - turretImage.getRegionWidth() / 2;
        turret.y = HEIGHT / 2 - turretImage.getRegionHeight() / 2;
        turret.width = turretImage.getRegionWidth();
        turret.height = turretImage.getRegionHeight();

        topRightAngle = Math.atan2((HEIGHT-(HEIGHT/2)), WIDTH/2);
        topLeftAngle = Math.atan2((HEIGHT-(HEIGHT/2)), -1*WIDTH/2);
        bottomLeftAngle = -1*topLeftAngle;
        bottomRightAngle = -1*topRightAngle;

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(turretImage, turret.x, turret.y);
        batch.end();

        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        int originX = x - WIDTH / 2;
        int originY = y + HEIGHT / 2;

        double angle = Math.atan2((HEIGHT-originY), originX);
        if (angle >= topRightAngle && angle < topLeftAngle) {
            turretImage.setRegionX(128);
        }
        if (angle >= topLeftAngle || angle < bottomLeftAngle) {
            turretImage.setRegionX(64);
        }
        if (angle >= bottomLeftAngle && angle < bottomRightAngle) {
            turretImage.setRegionX(0);
        }
        if (angle >= bottomRightAngle && angle <= 0
                || angle < topRightAngle && angle > 0) {
            turretImage.setRegionX(192);
        }
        turretImage.setRegionWidth(32);
        turretImage.setRegionHeight(32);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resize(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        topRightAngle = Math.atan2((HEIGHT-(HEIGHT/2)), WIDTH/2);
        topLeftAngle = Math.atan2((HEIGHT-(HEIGHT/2)), -1*WIDTH/2);
        bottomLeftAngle = -1*topLeftAngle;
        bottomRightAngle = -1*topRightAngle;
    }

    @Override
    public void resume() {

    }

}
