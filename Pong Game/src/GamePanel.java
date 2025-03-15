import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    // JFrame provides a window for the game which display buttons and other staff.
    //it is a part of javax.swing.*
    //Runnable is an interface to create a new thread. It allows the gamePanel to run separately as  a thread

    static final int GAME_WIDTH=1000; //here we make the game_width static to belong to the class rather than to an object and used final key word to prevent modifications
    static final int GAME_HEIGHT=(int)(GAME_WIDTH * (0.555));
    static final Dimension SCREEN_SIZE= new Dimension(GAME_WIDTH,GAME_HEIGHT);//this is a class from java.awt
    static final int BALL_DIAMETER =20;
    static final int PADDLE_WIDTH =25;
    static final int PADDLE_HEIGHT =100;


    Thread gameThread;
    Ball ball;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Score score;




    GamePanel(){

        newPaddle();
        newBall();
        score=new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusCycleRoot(true);

        this.setFocusable(true);
        /*This makes the GamePanel capable of receiving keyboard input.
        If a component is not focusable, it won't receive key events even if you add a key listener.
        By default, JPanel is not focusable, so calling this is necessary.

         */

        this.requestFocusInWindow();
        /*This requests focus on the GamePanel, ensuring it starts capturing key events immediately.
        Without this, another component (like a button, text field, or even the window itself) might be focused instead, causing your key presses to be ignored.
        Calling this ensures that GamePanel is the active component when the game starts.

         */
        this.addKeyListener(new AL()); //Attach key listener to Game Panel

        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.black);

        gameThread=new Thread(this);
        gameThread.start();

    }

    public void newBall(){
        random = new Random();
        ball=new Ball(GAME_WIDTH/2-BALL_DIAMETER/2,random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);


    }

    public void newPaddle(){
        paddle1 =new Paddle(0,GAME_HEIGHT/2 - PADDLE_HEIGHT / 2,PADDLE_WIDTH , PADDLE_HEIGHT ,1);
        paddle2 =new Paddle(GAME_WIDTH - PADDLE_WIDTH ,GAME_HEIGHT / 2 - PADDLE_HEIGHT/2 , PADDLE_WIDTH , PADDLE_HEIGHT ,2);

    }

    public void paint(Graphics g){

        //super.paint(g);
        image=createImage(getWidth(),getHeight()); //This creates an off-screen image buffer the same size as the component (JPanel or JFrame).
        graphics=image.getGraphics(); //Retrieves a Graphics object from the off-screen image buffer to draw onto the off-screen image.Graphics class,
                                // which serves as the fundamental interface for all graphical rendering operations in the Java AWT (Abstract Window Toolkit) and Swing libraries
        draw(graphics);                     //render the graphic to shapes,text....
        g.drawImage(image,0,0,this); //

    }

    public void draw(Graphics g){
        /* here the paddle1 and paddle2 are instance of a class which have draw(Graphics g) method

         */
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);


    }

    public void move(){
        /* Here we call the move method in the paddle class for each paddle.
           So we have insert a move method in the game loop. when it runs move method
           of the game panel is called and that method calls paddle move method indirectly.

         */
        paddle1.move();
        paddle2.move();
        ball.move();

    }

    public void checkCollision(){

        /* in the game panel y=0 and x=0 locates in the top left corner of the window
        and y increase downwards while x increases towards right
         */

        //bounce the ball at the window edges
        if(ball.y <= 0 )
            ball.setYDirection(-ball.yVelocity);

        if(ball.y >= (GAME_HEIGHT-BALL_DIAMETER))
            ball.setYDirection(-ball.yVelocity);


        //stops paddles at window edge
        if(paddle1.y<=0)
            paddle1.y=0;

        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y= GAME_HEIGHT-PADDLE_HEIGHT;

        if(paddle2.y<=0)
            paddle2.y=0;

        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y= GAME_HEIGHT-PADDLE_HEIGHT;

        //hitting the ball on the paddle1
        if(ball.intersects(paddle1)) {
            /*check if the ball collide with tha paddle
              method from the Rectangle Class
             */
            ball.xVelocity=Math.abs(ball.xVelocity); //Ensures that xVelocity (the ball's speed in the x-direction) is positive, so the ball always moves away from paddle1 after the collision.
            ball.xVelocity++; //this is optional. Increase the velocity after each collision making the game more harder

            /* This is also optional. Increase the y directional speed.
             */

            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //hitting the ball on the paddle2
        if(ball.intersects(paddle2)) {
            /*check if the ball collide with tha paddle
              method from the Rectangle Class
             */
            ball.xVelocity=Math.abs(ball.xVelocity); //Ensures that xVelocity (the ball's speed in the x-direction) is positive, so the ball always moves away from paddle1 after the collision.
            ball.xVelocity++; //this is optional. Increase the velocity after each collision making the game more harder

            /* This is also optional. Increase the y directional speed.
             */

            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }


        //give a player one point and create new paddles and a new ball
        if(ball.x <= 0) {
            score.player2++;
            newPaddle();
            newBall();
            System.out.println("Player 2 score: "+score.player2);
        }

        if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
            score.player1++;
            newPaddle();
            newBall();
            System.out.println("Player 1 score: "+score.player1);
        }

    }

    public void run(){
        //game loop

        long lastTime = System.nanoTime(); //store the current time in nanoseconds since the JVM starts...
                                            //System.currentTimeMillis() – Real-World Clock Time...measure the real world time since Jan 1 of 1970
        double amountOfTicks=60.0; // shows that the game is updating 60 times per second.. this ensures the game runs at a constant speed,
                                    // no matter how fast the computation is.

        double ns=1000000000 / amountOfTicks; //converts the nanoseconds per frame...here after 16.67 ms the game must be updated.
        double delta=0; //keep track how much time has passed

        while(true){
            long now=System.nanoTime();
            delta += (now-lastTime)/ns;//how much time has passed relative to one frame
            lastTime=now; //update the last recorded time

            if(delta >=1){
                move(); //update move
                checkCollision(); //check collisions
                repaint();
                delta --; //Reduces delta by 1( so it only runs per frame)



            }

        }

    }

    public class AL extends KeyAdapter{
        //Action Listener which is a built-in abstract class in part of the Java.awt.event package
        //used to listen to the key presses(KeyEvent) in a GUI program



        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }
    }
}
