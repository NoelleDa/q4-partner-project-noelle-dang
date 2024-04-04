import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private String[][] pressedGrid = new String[11][11];

    //the button for this listener
    JButton theButton;

    //constructor
    public ButtonListener(JButton button){
        this.theButton = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        theButton.setBackground(new Color(0,255,0));
        theButton.setText("X");
        theButton.setEnabled(false);
        System.out.println(e.getActionCommand());


    }


}
