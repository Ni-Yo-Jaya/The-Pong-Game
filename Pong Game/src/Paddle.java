import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle{
    int id;
    int yVelocity;
    int speed=5; //the number indicates the no of pixels to be moved


    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id=id;
    }

    public void keyPressed(KeyEvent e){
        if(id==1) {
            if (e.getKeyCode() == KeyEvent.VK_W) { //if the W key is pressed
                setYDirection(-speed);//move the paddle upwards in a specific speed
                //this.move();
            }

            if (e.getKeyCode() == KeyEvent.VK_S) { //if the S key is pressed
                setYDirection(speed); //move downwards
                //this.move();
            }
        }

        else{
            if(e.getKeyCode()==KeyEvent.VK_UP){ //if the W key is pressed
                    setYDirection(-speed); //move the paddle upwards in a specific speed
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){ //if the S key is pressed
                    setYDirection(speed); //move downwards
                    move();
                }

        }


    }

    public void keyReleased(KeyEvent e){
        if(id==1) {
            if (e.getKeyCode() == KeyEvent.VK_W) { //if the W key is pressed
                setYDirection(0);//move the paddle upwards in a specific speed
                //move();
            }

            if (e.getKeyCode() == KeyEvent.VK_S) { //if the S key is pressed
                setYDirection(0); //move downwards
                //move();
            }
        }

        else {
                if(e.getKeyCode()==KeyEvent.VK_UP){ //if the W key is pressed
                    setYDirection(0); //move the paddle upwards in a specific speed
                    //move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){ //if the S key is pressed
                    setYDirection(0); //move downwards
                    //move();
                }

        }

    }

    public void setYDirection(int yDirection){
        yVelocity = yDirection;


    }
    public void move(){
        y = y + yVelocity;

    }
    public void draw(Graphics g) { //Graphic g is an object used for drawing which is a part of awt
        if (id == 1) {                       //you can draw shapes, text, images etc.
            g.setColor(Color.blue);
            g.fillRect(x, y, width, height);
        }
        else {                       //you can draw shapes, text, images etc.
            g.setColor(Color.red);
            g.fillRect(x, y, width, height);

        }




    }
}
