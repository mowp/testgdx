package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    ShapeRenderer sR;
    int playerX;
    int ammoX;
    int playerY;
    int ammoY;
    int playerW = 50;
    int playerH = 15;
    int direction = 1;
    int ammoMove = 1;

    @Override
    public void create() {
        sR = new ShapeRenderer();
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && playerX>0){
            playerX-=5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && playerX+playerW<width){
            playerX+=5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            ammoX = playerX + playerW/2;
            ammoY = playerY+playerH;
            ammoMove = 0;
        }
        if (ammoMove == 1){
            System.out.println("Ammo is stationary");
        }
        if (ammoMove == 0){
            ammoY +=5;
        }


        sR.begin(ShapeRenderer.ShapeType.Filled);
            sR.setColor(Color.BLACK);
            sR.rect(playerX,playerY,playerW,playerH);
            sR.circle(ammoX,ammoY,5,5);
        sR.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
