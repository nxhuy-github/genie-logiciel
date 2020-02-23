package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;

import static org.junit.Assert.*;
import javafx.scene.image.Image;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model.Cloud;
import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model.Player;
import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model.Projectile;

public class Ball_Obstacle_CollisionTest {

    Cloud cloud; // obstacle is a cloud
    Projectile ball;
    @Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();


    @Before
    public void init(){
        cloud = new Cloud(new Image("assets/cloud.png"),100,100);
        ball = new Projectile(null,100,100,100,new Image("assets/ball.png"),"bottom");

   }
    @Test
    public void testNormalCollision() {
        ball.setX(100);
        ball.setY(100) ; // set ball in the same x,y with cloud (100,100)
        assertEquals(true,cloud.isHitted(ball)); //expected = true;
    }


    @Test
    public void testNotCollision() {
        ball.setX(10);
        ball.setY(10) ; // set ball in different x,y with cloud (100,100) vs (10,10)
        assertEquals(false,cloud.isHitted(ball)); // expected = false;
    }

}
