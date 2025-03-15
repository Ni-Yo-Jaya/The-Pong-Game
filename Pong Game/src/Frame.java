import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Frame extends JFrame{

    GamePanel panel;
    Frame(){
        panel=new GamePanel();
        this.add(panel);  //add the GamePanel to Frame making it part of it
        this.setTitle("Pong Game");  //Set the title
        this.setBackground(Color.black); //Set the background colour
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Ensure the program exits when the window is closed
        this.pack(); //Automatically sizes the frame to fit the preferd size fo its component file(Game Panel)
        this.setVisible(true); //makes the frame variable visible on the screen
        this.setLocationRelativeTo(null); //centers the frame on the window

    }

}
