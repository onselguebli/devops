package com.example.demo1;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.example.demo1.SpaceInvaders.WIDTH;
import static org.junit.jupiter.api.Assertions.*;

class SpaceInvadersTest {

    // BeforeAll annotation indicates that this method should be executed once before any tests in the class
    @BeforeAll
    static void initJFX() {
        new JFXPanel(); // Initialize JavaFX
    }

    // Create an instance of the SpaceInvaders class for testing
    private final SpaceInvaders spaceInvaders = new SpaceInvaders();

    // Test the setup method in the SpaceInvaders class
    @Test
    void testSetup() {
        spaceInvaders.setup();

        // Assert that essential components are initialized correctly
        assertNotNull(spaceInvaders.univ);
        assertNotNull(spaceInvaders.shots);
        assertNotNull(spaceInvaders.Bombs);
        assertNotNull(spaceInvaders.player);
        assertEquals(0, spaceInvaders.score);
    }
/*
    @Test
    void testSetupInvalid() {
        spaceInvaders.setup();

        // Intentionally making the setup fail for demonstration purposes
        assertNull(spaceInvaders.univ);
        assertNotNull(spaceInvaders.shots);
        assertNotNull(spaceInvaders.Bombs);
        assertNotNull(spaceInvaders.player);
        // Intentionally making the setup fail for demonstration purposes
        assertNotEquals(1, spaceInvaders.score);
    }

*/
    // Test collision between rockets where they don't collide
    /*
    @Test
    void testRocketDoesntCollide() {
        SpaceInvaders.Rocket rocket1 = spaceInvaders.new Rocket(0, 0, 30, null);
        SpaceInvaders.Rocket rocket2 = spaceInvaders.new Rocket(40, 0, 30, null);

        assertTrue(rocket1.colide(rocket2));
    }*/

    // Test collision between rockets where they collide
    @Test
    void testRocketCollide() {
        SpaceInvaders.Rocket rocket1 = spaceInvaders.new Rocket(0, 0, 30, null);
        SpaceInvaders.Rocket rocket2 = spaceInvaders.new Rocket(0, 0, 30, null);

        assertTrue(rocket1.colide(rocket2));
    }

    // Test collision between a shot and a rocket
    @Test
    void testShotCollide() {
        SpaceInvaders.Rocket rocket = spaceInvaders.new Rocket(0, 0, 30, null);
        SpaceInvaders.Shot shot = spaceInvaders.new Shot(0, 0);

        assertTrue(shot.colide(rocket));
    }

    // Test non-collision between a shot and a rocket
    /*
    @Test
    void testShotDoesntCollide() {
        SpaceInvaders.Rocket rocket = spaceInvaders.new Rocket(0, 0, 30, null);
        SpaceInvaders.Shot shot = spaceInvaders.new Shot(15, 0);

        assertFalse(shot.colide(rocket));
    }
*/
    // Test the distance calculation method
    @Test
    void testDistance() {
        int distance = spaceInvaders.distance(0, 0, 3, 4);
        assertEquals(5, distance);
    }

    // Test the distance calculation method with intentional failure
    /*
    @Test
    void testDistanceFail() {
        int distance = spaceInvaders.distance(0, 0, 3, 4);

        // Intentionally modify the expected value to make the test fail
        assertEquals(6, distance);
    }
*/
    // Test the creation of a new bomb
    @Test
    void testNewBomb() {
        spaceInvaders.setup();

        SpaceInvaders.Bomb bomb = spaceInvaders.newBomb();
        assertNotNull(bomb);
        assertTrue(bomb.posX >= 50 && bomb.posX <= WIDTH - 50);
        assertEquals(0, bomb.posY);
    }
    /*
    @Test
    void testNewBombInvalid() {
        spaceInvaders.setup();

        // Test newBomb method
        SpaceInvaders.Bomb bomb = spaceInvaders.newBomb();
        assertNull(bomb); // Intentionally making it fail
        assertTrue(bomb.posX >= 50 && bomb.posX <= WIDTH - 50);
        assertEquals(1, bomb.posY); // Intentionally making it fail
    }*/

    // Test the creation of the Universe object
    @Test
    void testUniverseCreation() {
        spaceInvaders.setup();

        SpaceInvaders.Universe universe = spaceInvaders.new Universe();
        assertNotNull(universe);
        assertTrue(universe.posX >= 0 && universe.posX <= WIDTH);
        assertEquals(0, universe.posY);
    }
    /*
    @Test
    void testUniverseCreationInvalid() {
        spaceInvaders.setup();

        // Test the creation of the Universe object
        SpaceInvaders.Universe universe = spaceInvaders.new Universe();
        assertNull(universe); // Intentionally making it fail
        assertTrue(universe.posX >= 0 && universe.posX <= WIDTH);
        assertEquals(1, universe.posY); // Intentionally making it fail
    }
*/

    // Test shooting with intentional failure in the expected values
    /*
    @Test
    void testPlayerShootFail() {
        spaceInvaders.setup();

        SpaceInvaders.Shot shot = spaceInvaders.player.shoot();
        assertNotNull(shot);

        // Intentionally modify the expected values to make the test fail
        assertEquals(spaceInvaders.player.posX + spaceInvaders.player.size / 2 - SpaceInvaders.Shot.size / 2 + 10, shot.posX);
        assertEquals(spaceInvaders.player.posY - SpaceInvaders.Shot.size + 5, shot.posY);
    }
*/
    // Test shooting with intentional failure in the expected values
    @Test
    void testPlayerShoot() {
        spaceInvaders.setup();

        SpaceInvaders.Shot shot = spaceInvaders.player.shoot();
        assertNotNull(shot);

        // Intentionally modify the expected values to make the test fail
        assertNotEquals(spaceInvaders.player.posX + spaceInvaders.player.size / 2 - SpaceInvaders.Shot.size / 2, shot.posX + 10);
        assertNotEquals(spaceInvaders.player.posY - SpaceInvaders.Shot.size, shot.posY + 5);
    }
    // Test the Bomb update method
    @Test
    void testBombUpdate() {
        spaceInvaders.setup();
        SpaceInvaders.Bomb bomb = spaceInvaders.newBomb();

        int initialPosY = bomb.posY;
        bomb.update();

        if (bomb.exploding) {
            assertEquals(0, bomb.posY); // If bomb is exploding, posY should be reset to 0
        } else {
            assertEquals(initialPosY + bomb.SPEED, bomb.posY); // If not exploding, posY should increase by SPEED
        }
    }

    // Test Shot update method
    @Test
    void testShotUpdate() {
        SpaceInvaders.Shot shot = spaceInvaders.new Shot(0, 0);
        int initialPosY = shot.posY;
        shot.update();
        assertEquals(initialPosY - shot.speed, shot.posY); // posY should decrease by speed after the update
    }

    // Test the Rocket explode method
    @Test
    void testRocketExplode() {
        SpaceInvaders.Rocket rocket = spaceInvaders.new Rocket(0, 0, 30, null);
        rocket.explode();
        assertTrue(rocket.exploding);
        assertEquals(-1, rocket.explosionStep); // explosionStep should be set to -1
    }



    // Test the main method
    @Test
    void testMain() {
        assertDoesNotThrow(() -> SpaceInvaders.main(new String[]{}));
    }
}