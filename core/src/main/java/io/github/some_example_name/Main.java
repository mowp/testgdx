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
    int speed = 5;  // Move speed (horizontal)
    int jumpHeight = 10;
    int gravity = -1;
    int velocityY = 0;  // Vertical velocity for jumping + gravity
    boolean isJumping = false;  // Check if player is in the air
    int groundLevel = 0;

    // Line to draw
    boolean isLineActive = false;

    @Override
    public void create() {
        sR = new ShapeRenderer();
        playerX = Gdx.graphics.getWidth() / 2 - playerW / 2;  // Start the player in the center
        playerY = groundLevel;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);  // Clear the screen with a background color

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        // Player movement (left + right with arrow keys)
        if (Gdx.input.isKeyPressed(Input.Keys.A) && playerX > 0) {
            playerX -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && playerX + playerW < width) {
            playerX += speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !isJumping) {
            velocityY = jumpHeight;  // Set initial velocity when jumping
            isJumping = true;  // Tell program player is in the air
        }

        // Gravity simulation
        if (isJumping) {
            velocityY += gravity;  // Apply gravity over time
            playerY += velocityY;  // Change position based on velocity

            // Prevent going past ground level
            if (playerY <= groundLevel) {
                playerY = groundLevel;  // Reset position to ground level
                isJumping = false;  // Tell program player is on the ground
                velocityY = 0;  // Reset vertical velocity
            }
        }

        // Get the mouse position
        mouseX = Gdx.input.getX();
        mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();  // Convert Y to match screen coordinates

        // Calculate the angle from the center of the player to the mouse
        float deltaX = mouseX - (playerX + playerW / 2);  // Difference in X between the player center and mouse
        float deltaY = mouseY - (playerY + playerH / 2);  // Difference in Y between the player center and mouse
        rotationAngle = (float) Math.atan2(deltaY, deltaX);  // Calculate the angle in radians

        // Output the mouse position, delta, and angle (for debugging)
        System.out.println("Mouse X: " + mouseX + " Mouse Y: " + mouseY);
        System.out.println("Delta X: " + deltaX + " Delta Y: " + deltaY);
        System.out.println("Angle: " + rotationAngle);  // Angle in radians

        // Check for mouse click (left-click to draw the line)
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            isLineActive = true;
        } else {
            isLineActive = false;
        }

        // Start drawing the player and line
        sR.begin(ShapeRenderer.ShapeType.Filled);
        sR.setColor(Color.BLACK);
        sR.rect(playerX, playerY, playerW, playerH);  // Draw the player as a rectangle
        
        // If the line is active (mouse is clicked), draw the line from the top middle of the player to the mouse
        if (isLineActive) {
            float startX = playerX + playerW / 2;  // Top-middle X of the player
            float startY = playerY + playerH;  // Top-middle Y of the player

            // Draw the line from the top-middle of the player to the mouse position
            sR.setColor(Color.RED);
            sR.line(startX, startY, mouseX, mouseY);  // Draw the line
        }

        sR.end();
    }

    @Override
    public void dispose() {
        sR.dispose();
    }
}
