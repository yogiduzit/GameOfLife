package src;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;



public final class Main
{
    private static final Toolkit TOOLKIT;
    
    static
    {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    private Main()
    {
    }

    public static void main(final String[] argv)
    {

    	
        final GameFrame frame;
        final World     world;


        RandomGenerator.reset();
        world = new World(30, 30);
        // Populates the world
        world.init();
        
        frame = new GameFrame(world);
        position(frame);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Populate the frame with world
        
        frame.init();
        frame.setVisible(true);
    }

    private static void position(final GameFrame frame)
    {
        final Dimension size;

        size = calculateScreenArea(0.80f,
                                   0.80f);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }

    public static Point centreOnScreen(final Dimension size)
    {
        final Dimension screenSize;

        if(size == null)
        {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width) / 2,
                          (screenSize.height - size.height) / 2));
    }

    public static Dimension calculateScreenArea(final float widthPercent,
                                                final float heightPercent)
    {
        final Dimension screenSize;
        final Dimension area;
        final int       width;
        final int       height;
        final int       size;

        if((widthPercent <= 0.0f) || (widthPercent > 100.0f))
        {
            throw new IllegalArgumentException("widthPercent cannot be " + 
                                               "<= 0 or > 100 - got: " +
                                               widthPercent);
        }

        if((heightPercent <= 0.0f) || (heightPercent > 100.0f))
        {
            throw new IllegalArgumentException("heightPercent cannot be " + 
                                               "<= 0 or > 100 - got: " +
                                               heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width      = (int)(screenSize.width * widthPercent);
        height     = (int)(screenSize.height * heightPercent);
        size       = Math.min(width,
                              height);
        area       = new Dimension(size,
                                   size);

        return (area);
    }
}
