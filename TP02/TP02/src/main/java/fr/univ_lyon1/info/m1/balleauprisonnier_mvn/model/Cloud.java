package fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javafx.animation.PathTransition;
import javafx.scene.image.*;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
public class Cloud extends ImageView implements Hitable {
    Image image;
    double x;
    double y;
    Random ran = new Random();
    boolean blockL = false;
    boolean blockR = false ;
    boolean ini = true;
    public Cloud(Image image,double inX, double inY)
    {
        this.image = image;
        x = inX;
        y = inY;
        this.setImage(this.image);
        this.setX(x);
        this.setY(y);
    }

    @Override
    public boolean isHitted(ImageView b) {

        double width = 64;
        double height = 64;

        Rectangle2D rp = new Rectangle2D.Double(this.x, this.y, width/2, height/2);

        Rectangle2D rb = new Rectangle2D.Double(b.getX(), b.getY(),64/2, 64/2);

        if(rp.intersects(rb)){
            return true;
        }else{
            return false;
        }
    }



    public void move()
    {

        Random rand = new Random();
        int  n = rand.nextInt(2) + 1;      
        if(ini)
        {
            x += n;
        }
        if( x <= 0 )
        {
            blockL = true;
            blockR = false;
            ini = false ;
        }
        if( x >= 550)
        {
            blockR =true;
            blockL = false;
            ini = false ;
        }
         if(blockR)
         {
             x -= n;
         }

         if(blockL)
         {
             x += n;
         }
        this.setX((double)x);

    }

}
