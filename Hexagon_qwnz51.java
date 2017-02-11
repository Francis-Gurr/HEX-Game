import java.awt.*;
import java.awt.geom.*;

public class Hexagon_qwnz51
{
    public static Shape radiusShape(int radius)
     {
        //Create hexagon shape
        Polygon hexagon = new Polygon();
        for (int i = 0; i < 6; i++)
        {
            double rad = Math.toRadians(i*360/6);
            double x = Math.cos(rad)*radius;
            double y = Math.sin(rad)*radius;
            hexagon.addPoint((int)x, (int)y);
        }
        Rectangle bounds = hexagon.getBounds();
        //Rotate so that the point of the hexagon is at the top (as is the standard for most Hex boards)
        hexagon.translate(-bounds.x, -bounds.y);        
        double rad = Math.toRadians(90);
        double x = bounds.width/2;
        double y = bounds.height/2;
        AffineTransform at = AffineTransform.getRotateInstance(rad, x, y);
        Shape pointyHex = at.createTransformedShape(hexagon);
        return pointyHex;
    }
}