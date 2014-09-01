package mapper;

import javax.swing.JFrame;

/**
 *
 * @author Suhail
 */
public class TubeMapper extends JFrame {
    
    public TubeMapper() throws Exception {        
        this.setTitle("Tube Mapper Application");
        this.setSize(1607, 950);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        TubeMapCanvas canvas = new TubeMapCanvas();
        add("Center", canvas);

    }
    
    public static void main(String[] args) {
        try {
            TubeMapper m = new TubeMapper();
            m.setVisible(true);
        } catch (Exception ex) {
            System.out.println("Could not load application: " + ex.getMessage());
        }
    }
}
