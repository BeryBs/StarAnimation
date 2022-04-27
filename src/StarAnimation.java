
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class StarAnimation extends JPanel implements ActionListener {

    Timer timer;
    // Point array for drawing a star
    private final double points[][] = {

            { 0, 85 }, { 75, 75 }, { 100, 10 }, { 125, 75 }, { 200, 85 }, { 150, 125 }, { 160, 190 }, { 100, 150 },

            { 40, 190 }, { 50, 125 }, { 0, 85 } };

    // Variables to change size of the star
    private double scale = 1;
    private double delta = 0.01;

    // Default Constructor
    public StarAnimation() {
        //create timer and start it
        timer = new Timer(10, this);
        timer.setInitialDelay(190);
        timer.start();
    }

    public void paint(Graphics g)

    {
        //The two colours (red and green) for the gradient paint.
        Color c1 = new Color(0.0f,0.0f,1.0f);
        Color c2 = new Color(1.0f,1.0f,1.0f);
        Paint p = new GradientPaint(0.0f, 0.0f, Color.getHSBColor(0.50f, 0.3f, 0.2f), 0, getHeight(),
                Color.getHSBColor(0.60f, 0.8f, 0.7f));

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(p);

        // rectangle background
        g2d.fillRect(0, 0, getWidth(), getHeight());
        GeneralPath path = new GeneralPath();
        path.moveTo(points[0][0], points[0][1]);


        // lineTo() initializes coordinates and connect star coordinates
        for (int i = 1; i < points.length; i++) {
            path.lineTo(points[i][0], points[i][1]);
        }

        path.closePath();

        // create AffineTransform object
        AffineTransform aff = new AffineTransform();
        aff.translate((getWidth() / 4) - (path.getBounds().getWidth() * (scale)) / 4,
                (getHeight() / 4) - (path.getBounds().getHeight() * (scale)) / 4);
        aff.scale(scale, scale);

        Shape mysh = aff.createTransformedShape(path);


        g2d.setColor(Color.magenta);
        g2d.fill(mysh);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new StarAnimation());
        f.setSize(400, 400);

        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // Changing the scale variable by using delta
        scale += delta;

        // If scale < 0.01 or 0.99 < scale you can change the sign of delta
        // So that it grows when it's small and shrinks when it's big
        // Add delta to scale and repaint
        if (scale < 0.01 || scale > 0.99) {
            delta *= -1;
            scale += delta;
        }
        // repainting
        repaint();
    }
}