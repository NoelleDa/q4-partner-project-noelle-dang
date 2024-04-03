import javax.swing.*;
import java.awt.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class PixilicateGUI extends JFrame {

    private int screenWidth = 800;
    private int screenHeight = 800;
    //constructor - create the GUI
    public PixilicateGUI(){
        //call the super class helper methods
        //to customize the view
        super.setTitle("Pixilicate");
        super.setSize(screenWidth,screenHeight);
        //we will practice using the layout managers
        //which will govern how widgets are placed on the screen
        super.setLayout(new BorderLayout());
        //add widgets
        /*
        1) create a JPanel (section GUI into parts
        2) create a widget to add to the JPanel
        3) add the JPanel to the Jframe
         */
        JPanel topPanel = new JPanel(); //panel to hold widgets
        topPanel.add(new JLabel("Click the Buttons to play"));
        super.add(topPanel,BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(); //panel to hold widgets
        bottomPanel.add(new JLabel("Coded by: Noelle Dang"));
        super.add(bottomPanel,BorderLayout.SOUTH);

        //adding a widget with interaction too
        JPanel centerPanel = new JPanel();

        //example of gridlayour
        centerPanel.setLayout(new GridLayout(8,8));
        //create a total of 64 buttons to add to hte panel
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                JButton button = new JButton("");
                centerPanel.add(button);

                //connects button to a listener to listen for click events
                button.addActionListener( new ButtonListener(button) );
            }
        }

        //add the panel to the view
        super.add(centerPanel, BorderLayout.CENTER);

        //end of adding widgets

        //set default action for  the x button
        //the program continues running w/o the GUI if you forget this
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //once you're done setting up the GUI, set it to visible
        super.setVisible(true);
    }

}