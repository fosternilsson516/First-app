import java.awt.*;

import java.awt.event.*;

import javax.swing.*;


class Redball extends JComponent implements ActionListener, MouseMotionListener {

    private int ballx = 150;
    private int bally = 30;
    private int paddlex = 0;
    private int ballySpeed = 7;
    private int ballxSpeed = 6;
    public int score = 0;
    private int scorefinal;
    public int bestscore;
    public boolean gameOver;


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {

        //draw the sky
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 800, 600);

        g.setColor(Color.ORANGE);
        g.fillRect(0, 550, 800, 100);

        //draw the paddel
        g.setColor(Color.black);
        g.fillRect(paddlex, 500, 100, 20);

        //draw the ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballx, bally, 30, 30);


        //score
        if (score >= 5) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", 8, 50));
            g.drawString(String.valueOf(score), 15, 80);
        } else {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 8, 50));
            g.drawString(String.valueOf(score), 15, 80);
        }
        // start && gameOver
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 8, 50));

        if (gameOver) {
            g.drawString(" Best Score :" + scorefinal, 35, 200);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ballx = ballx + ballxSpeed;
        bally = bally + ballySpeed;


        // Window Down
        if (ballx >= paddlex && ballx <= paddlex + 100 && bally >= 475) {
            ballySpeed = -7;
            score++;
        }

        if (bally >= 700 ) {
            score = 0;
            bally = 30;
            gameOver = true;
        }

        // Window up
        if (bally <= 0) {
            ballySpeed = 7;
        }

        // Window right
        if (ballx >= 775) {
            ballxSpeed = -5;
        }

        // Window left
        if (ballx <= 0) {
            ballxSpeed = 5;
        }


        //**********************************************************************
        bestscore = score;

        if (scorefinal > bestscore) {
            scorefinal = scorefinal;
        } else {
            scorefinal = bestscore;
            scorefinal = score;
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        paddlex = e.getX() - 50;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

}
