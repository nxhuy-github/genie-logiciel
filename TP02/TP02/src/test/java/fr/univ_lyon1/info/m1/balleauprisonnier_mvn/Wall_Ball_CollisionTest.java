package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;

import static org.junit.Assert.*;
import javafx.scene.image.Image;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model.Cloud;
import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model.Projectile;
import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model.Walls;

public class Wall_Ball_CollisionTest {


    Walls wall; //
    Projectile ball;
    @Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();


    @Before
    public void init(){
        wall = new Walls(600,600); //wals with screen 600 x 600
        ball = new Projectile(null,100,100,100,new Image("assets/ball.png"),"bottom");

   }
    @Test
    public void testnotCollision() {
        ball.setX(100);
        ball.setY(100) ; // set ball in the different x,y with wall (100,100)
        assertEquals(false,wall.isHitted(ball)); //expected = false;
    }


    @Test
    public void testNormalCollisionTopWall() {
        ball.setX(100);
        ball.setY(0) ; // set ball interacting with top wall
        assertEquals(true,wall.isHitted(ball)); // expected = true;
    }


    @Test
    public void testNormalCollisionLeftWall() {
        ball.setX(0);
        ball.setY(100) ; // set ball interacting with left wall
        assertEquals(true,wall.isHitted(ball)); // expected = true;
    }


    @Test
    public void testNormalCollisionRightWall() {
        ball.setX(600);
        ball.setY(100) ; // set ball interacting with right wall
        assertEquals(true,wall.isHitted(ball)); // expected = true;
    }


    @Test
    public void testNormalCollisionBotWall() {
        ball.setX(100);
        ball.setY(600) ; // set ball interacting with bot wall
        assertEquals(true,wall.isHitted(ball)); // expected = true;
    }


}
