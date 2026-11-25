package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Table extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

    private final int   TABLE_WIDTH    = 300;
    private final int   TABLE_HEIGHT   = 500;
    private final int   WALL_THICKNESS = 20;
    private final Color COLOR          = Color.green;
    private final Color WALL_COLOR     = Color.black;
    private       Ball  ball1, ball2;
    private final Timer simulationTimer;

    
    Table() {
        
        setPreferredSize(new Dimension(TABLE_WIDTH + 2 * WALL_THICKNESS,
                                       TABLE_HEIGHT + 2 * WALL_THICKNESS));
        createInitialBalls();
        
        addMouseListener(this);
        addMouseMotionListener(this);

        simulationTimer = new Timer((int) (1000.0 / Billiard.UPDATE_FREQUENCY), this);
    }

    private void createInitialBalls(){
        final Coord firstInitialPosition = new Coord(100, 100);
        final Coord secondInitialPosition = new Coord(200, 200);
        ball1 = new Ball(firstInitialPosition);
        ball2 = new Ball(secondInitialPosition);
    }
    
    public void actionPerformed(ActionEvent e) {          // Timer event
        ball1.move();
        ball2.move();
        ball1.wallCollision(WALL_THICKNESS, TABLE_HEIGHT, TABLE_WIDTH);
        ball2.wallCollision(WALL_THICKNESS, TABLE_HEIGHT, TABLE_WIDTH);
        ball1.ballsCollided(ball2);    
        repaint();
        if (!ball1.isMoving() && !ball2.isMoving()) {
            simulationTimer.stop();
        }
    }

    public void mousePressed(MouseEvent event) {
        Coord mousePosition = new Coord(event);
        ball1.setAimPosition(mousePosition);
        ball2.setAimPosition(mousePosition);
        repaint();                                          //  To show aiming line
    }

    public void mouseReleased(MouseEvent e) {
        ball1.shoot();
        ball2.shoot();
        ball1.wallCollision(WALL_THICKNESS, TABLE_HEIGHT, TABLE_WIDTH);
        ball2.wallCollision(WALL_THICKNESS, TABLE_HEIGHT, TABLE_WIDTH);
        ball1.ballsCollided(ball2);
        if (!simulationTimer.isRunning()) {
            simulationTimer.start();      
        }
    }

    public void mouseDragged(MouseEvent event) {
        Coord mousePosition = new Coord(event);
        ball1.updateAimPosition(mousePosition);
        ball2.updateAimPosition(mousePosition);
        repaint();
    }

    // Obligatory empty listener methods
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2D = (Graphics2D) graphics;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // This makes the graphics smoother
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2D.setColor(WALL_COLOR);
        g2D.fillRect(0, 0, TABLE_WIDTH + 2 * WALL_THICKNESS, TABLE_HEIGHT + 2 * WALL_THICKNESS);

        g2D.setColor(COLOR);
        g2D.fillRect(WALL_THICKNESS, WALL_THICKNESS, TABLE_WIDTH, TABLE_HEIGHT);

        ball1.paint(g2D);
        ball2.paint(g2D);
    }
}  // end class Table
