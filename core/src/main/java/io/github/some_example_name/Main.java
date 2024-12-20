package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private ShapeRenderer sR;
    int playerX, playerY;
    int playerW = 16, playerH = 16;
    float rotationAngle;
    
    // Mouse position
    int mouseX, mouseY;

    // Movement and jumping
    int speed = 5;
    int jumpHeight = 10;
    int gravity = -1;
    int velocityY = 0;
    boolean isJumping = false;
    int groundLevel = 0;

    // Line to draw
    boolean isLineActive = false;

    @Override
    public void create() {
        sR = new ShapeRenderer();
        playerX = Gdx.graphics.getWidth() / 2 - playerW / 2;
        playerY = groundLevel;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        if (Gdx.input.isKeyPressed(Input.Keys.A) && playerX > 0) {
            playerX -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && playerX + playerW < width) {
            playerX += speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !isJumping) {
            velocityY = jumpHeight;
            isJumping = true;  
        }

        // gravity
        if (isJumping) {
            velocityY += gravity;
            playerY += velocityY;

            if (playerY <= groundLevel) {
                playerY = groundLevel;
                isJumping = false;
                velocityY = 0;
            }
        }

        // Get the mouse position
        mouseX = Gdx.input.getX();
        mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();  // convert y to screen coords

        // Calculate the angle from the center of the player to the mouse
        float deltaX = mouseX - (playerX + playerW / 2);  // diff x from player centre to mouse
        float deltaY = mouseY - (playerY + playerH / 2);  // diff y from player centre to mouse
        rotationAngle = (float) Math.atan2(deltaY, deltaX);  // angle in rad

        float lineLength = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY); //length of line

        System.out.println("Mouse X: " + mouseX + " Mouse Y: " + mouseY);
        System.out.println("Delta X: " + deltaX + " Delta Y: " + deltaY);
        System.out.println("Angle: " + rotationAngle);  // ang in rad
        System.out.println("Line Length: " + lineLength);

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            isLineActive = true;
        } else {
            isLineActive = false;
        }

        sR.begin(ShapeRenderer.ShapeType.Filled);
        sR.setColor(Color.BLACK);
        sR.rect(playerX, playerY, playerW, playerH);
        
        if (isLineActive) {
            float startX = playerX + playerW / 2;  // top mid x
            float startY = playerY + playerH;  // top mid y

            sR.setColor(Color.RED);
            sR.line(startX, startY, mouseX, mouseY);  //draw
        }

        sR.end();
    }

    @Override
    public void dispose() {
        sR.dispose();
    }
}
