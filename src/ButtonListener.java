import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    //the button for this listener
    JButton theButton;

    //constructor
    public ButtonListener(JButton button){
        this.theButton = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        theButton.setText("X");
        theButton.setEnabled(false);
        theButton.setBackground(new Color(0,255,0));

    }
}
