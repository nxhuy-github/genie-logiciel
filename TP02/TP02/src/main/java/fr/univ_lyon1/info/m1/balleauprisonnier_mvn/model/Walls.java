package fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model;

import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
public class Walls implements Hitable{

    Vector<Rectangle> rect;
    public Walls(double W, double H)
    {
        rect = new Vector<Rectangle>();

        Rectangle rect1 = new Rectangle(0,0,10,H);
        rect1.setStroke(Color.BLACK);
        rect.add(rect1);

        Rectangle rect2 = new Rectangle(0,0,W,10);
        rect2.setStroke(Color.BLACK);
        rect.add(rect2);

        Rectangle rect3 = new Rectangle(W,0,10,H);
        rect3.setStroke(Color.BLACK);
        rect.add(rect3);

        Rectangle rect4 = new Rectangle(0,H,W,10);
        rect4.setStroke(Color.BLACK);
        rect.add(rect4);
    }
    @Override
    public boolean isHitted(ImageView b) {
        // TODO Auto-generated method stub
        Rectangle2D rb = new Rectangle2D.Double(b.getX(), b.getY(), 32, 32);
        for(int i = 0 ; i < rect.size() ; i++ )
        {
            Rectangle2D rp = new Rectangle2D.Double(rect.get(i).getX(), rect.get(i).getY(), rect.get(i).getWidth(), rect.get(i).getHeight());

            if(rp.intersects(rb))
                return true;

        }

        return false;
    }

    public Vector<Rectangle> getRect()
    {
        return this.rect;
    }

}
