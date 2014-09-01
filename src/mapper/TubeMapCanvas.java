package mapper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Suhail
 */
public class TubeMapCanvas extends JPanel implements MouseListener {
    
    private BufferedImage image;
    private TubeMapLocator locator;
    private Object [] stationList;
    
    public TubeMapCanvas() throws Exception {
        this.locator = new TubeMapLocator();
        HashMap<String, String> stations = this.locator.getStations();
        Set<String> stationKeys = stations.keySet();
        stationList = stationKeys.toArray();
        Arrays.sort(stationList);
        
        // Add the Mouse Listeners
        addMouseListener(this);
        
        // Draw the image of the tube map
        image = ImageIO.read(new File("tube.png"));
    }

    public void paintComponent(Graphics g) {        
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g);
        
        // Draw the image of the tube map
        // According to http://www.mkyong.com/java/how-to-resize-an-image-in-java/
        // this improves the rendering too!
        g2.drawImage(image, 0, 0, null);
        
        // Loop through all our elements and draw a filled oval
        g2.setColor(Color.GREEN);
        for (TubeMapPoint p : locator.getPoints()) {
            g2.drawOval(p.x - 10, p.y - 10, 20, 20);
            g2.fillOval(p.x - 10, p.y - 10, 20, 20);
        }
        
        g2.dispose();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        String s = (String)JOptionPane.showInputDialog(null, "Choose Stations", 
                    "Stations", JOptionPane.PLAIN_MESSAGE, null, 
                    stationList, stationList[0]);
        
        if (s != null) {
            String code = locator.getStations().get(s);
            
            TubeMapPoint p = new TubeMapPoint();
            p.stationCode = code;
            p.x = e.getX();
            p.y = e.getY();
            
            locator.addPoint(p);
            System.out.println(s + " added at location " + p.x + ", " + p.y);
        }
        
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Do nothing
    }
}
