import java.awt.*;
import javax.swing.JComponent;

public class HexagonComponent_qwnz51 extends JComponent
{
    private Shape shape;
    private int xCoord;
    private int yCoord;

    public HexagonComponent_qwnz51(Shape shape, Color color)
    {
        this.shape = shape;
        revalidate();
        repaint();
        setForeground(color);
        setOpaque(false);
    }
    
    //Ensure the bounds are that of the hex (for Interacitvity and MouseAdapter)
    @Override
    public Dimension getPreferredSize()
    {
        Insets ins = getInsets();
        Rectangle bounds = shape.getBounds();
        int width = ins.left + ins.right + bounds.width;
        int height = ins.top + ins.bottom + bounds.height;
        return new Dimension(width, height);
    }
    
    //Paint chosen shape i.e. hexagon
    @   Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();       
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);        
        Rectangle bounds = shape.getBounds();
        Insets ins = getInsets();
        g2d.translate(ins.left - bounds.x, ins.top - bounds.y);
        g2d.fill(shape);
        g2d.dispose();
    }
    
    //For MouseAdapter (i.e. Interactivity)
    @Override
    public boolean contains(int x, int y)
    {
        Rectangle bounds = shape.getBounds();
        Insets ins = getInsets();
        int translateX = x + bounds.x - ins.left;
        int translateY = y + bounds.y - ins.top;
        return shape.contains(translateX, translateY);
    }

    //For setting the coordinates of the hexagon on the board(from MouseAdapter)
    public void setCoords(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }    

    public int getXCoord()
    {
        return xCoord;
    }

    public int getYCoord()
    {
        return yCoord;
    }
}