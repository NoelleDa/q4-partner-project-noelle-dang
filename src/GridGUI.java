import javax.swing.*;
import java.awt.*;

public class GridGUI extends JFrame {
    //change of the GridClass
    private int screenWidth = 800;
    private int screenHeight = 800;
    public GridGUI(){
        //JFrame settings
        //setting up JFrame
        JFrame f = new JFrame("PixelMatch");
        f.setTitle("PixelMatch");
        f.setBackground(Color.BLACK);
        f.setSize(screenWidth,screenHeight);
        f.setLayout( new BorderLayout() );
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        //end of Frame settings
    }



}
