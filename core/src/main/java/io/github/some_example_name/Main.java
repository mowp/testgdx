package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
    int x;
    int x2 = 250;
    int y;
    int y2;
    int w = 25;
    int w2 = 25;
    int h = 25;
    int h2 = 25;
    int direction = 1;
    int direction2 = 1;

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
        if (direction == 1){
            x+=5;
        }
        if (direction == 0){
            x-=5;
        }
        if (x>width-w){
            direction = 0;
        }
        if (x<0){
            direction = 1;
        }
        if (direction2 == 1){
            x2+=5;
        }
        if (direction2 == 0){
            x2-=5;
        }
        if (x2>width-w2){
            direction2 = 0;
        }
        if (x2<0){
            direction2 = 1;
        }
        if (x+w==x2){
            direction = 0;
        }
        if (x==x+w){
            direction2 = 1;
        }

        sR.begin(ShapeRenderer.ShapeType.Filled);
            sR.setColor(Color.BLACK);
            sR.rect(x,y,w,h);
            sR.rect(x2,y2,w2,h2);
        sR.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
//does this thing even work?