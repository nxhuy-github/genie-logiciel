package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model.Player;
import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model.Projectile;
import javafx.scene.image.*;


public class Player_vs_Ball_CollisionTest {

    Player player;
    Projectile ball;
    @Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();


    @Before
    public void init(){
        player = new Player(null,"blue",100,100,"top", "assets/PlayerRed.png");
        ball = new Projectile(null,100,100,100,new Image("assets/ball.png"),"bottom");

   }
    @Test
    public void testNormalCollision() { //

        ball.setX(100);
        ball.setY(100); // ball in the same coordinate with player, different side

        assertEquals(true,player.isHitted(ball)); // collision = true


    }


    @Test
    public void testNotCollision() { //

        ball.setX(10);
        ball.setY(10); // ball in the different coordinate with player, different side

        assertEquals(false,player.isHitted(ball)); // collision = false
    }


    @Test
    public void testCollisionAllies() { // test collsion between 2 objects not enemy
        // ball and player in the same side (top)
        ball = new Projectile(null,100,100,100,new Image("assets/ball.png"),"top");
        ball.setX(100);
        ball.setY(100); // set ball in the same coordinate with player

        assertEquals(true,player.isHitted(ball)); // collision always false
        // they're not enemies


    }



}
