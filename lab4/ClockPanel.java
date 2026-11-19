package lab4;

import javax.swing.*;
import java.awt.*;

public class ClockPanel extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int CENTER_X = WIDTH / 2;
    private static final int CENTER_Y = HEIGHT / 2;
    private static final int FACE_RADIUS = 180;

    private static final int TICK_COUNT=12;
    private static final int TICK_LENGTH = 10;
    private static final float TICK_WIDTH = 4f;
    private static final float FACE_BORDER_WIDTH = 6f;
    private static final int NUMBER_RADIUS = 140;
    private static final int NUMBER_FONT_SIZE = 22;

    // Hands
    private static final int HOUR_LENGTH = 90;
    private static final float HOUR_WIDTH = 8f;
    private static final int MINUTE_LENGTH = 130;
    private static final float MINUTE_WIDTH = 5f;
    private static final int SECOND_LENGTH = 155;
    private static final float SECOND_WIDTH = 2f;
    private static final int HUB_RADIUS = 8;

    private static final int DATE_Y = 450;
    private static final int DATE_FONT_SIZE = 20;
    private static final double FULL_CIRCLE=2*Math.PI;
    private static final int REFRESH_INTERVAL_MS=500;

    private static final Color BACKGROUND_COLOR = new Color(240,240,240);
    private static final Color FACE_COLOR = new Color(200,200,200);
    private static final Color TICK_COLOR = Color.DARK_GRAY;
    private static final Color BORDER_COLOR = Color.DARK_GRAY;
    private static final Color HOUR_COLOR=Color.green;
    private static final Color MINUTE_COLOR=Color.orange;
    private static final Color SECOND_COLOR=Color.darkGray;

    public ClockPanel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(BACKGROUND_COLOR);
        startTimer();
    }

    private void startTimer(){
        Timer timer = new Timer(REFRESH_INTERVAL_MS,e -> repaint());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        ClockTime time = new ClockTime();
        drawFace(g2d);
        drawDial(g2d);
        drawHand(g2d,HOUR_LENGTH,HOUR_WIDTH,time.getHourAngle(),HOUR_COLOR);
        drawHand(g2d,MINUTE_LENGTH,MINUTE_WIDTH,time.getMinuteAngle(),MINUTE_COLOR);
        drawHand(g2d,SECOND_LENGTH,SECOND_WIDTH,time.getSecondAngle(),SECOND_COLOR);

    }

    private Point pointOnCircle(double radius, double angle){
        int x = CENTER_X+(int)Math.round(radius*Math.sin(angle));
        int y = CENTER_Y+(int)Math.round(radius*Math.cos(angle));
        return new Point(x, y);
    }

    private void drawFace(Graphics2D g2){
        int diameter = 2*FACE_RADIUS;
        int left=CENTER_X-FACE_RADIUS;
        int top=CENTER_Y-FACE_RADIUS;

        g2.setColor(FACE_COLOR);
        g2.fillOval(left,top,diameter,diameter);

        g2.setColor(BORDER_COLOR);
        g2.setStroke(new BasicStroke(FACE_BORDER_WIDTH));
        g2.drawOval(left,top,diameter,diameter);
    }

    private void drawTick(Graphics2D g2, double angle) {
        Point outer = pointOnCircle(FACE_RADIUS - FACE_BORDER_WIDTH, angle);
        Point inner = pointOnCircle(FACE_RADIUS - FACE_BORDER_WIDTH - TICK_LENGTH, angle);

        g2.setColor(TICK_COLOR);
        g2.setStroke(new BasicStroke(TICK_WIDTH, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        g2.drawLine(inner.x, inner.y, outer.x, outer.y);
    }

    private void drawDial(Graphics2D g2){
        for (int i = 0; i<TICK_COUNT; i++){
            drawTick(g2, FULL_CIRCLE*i/TICK_COUNT);
        }
    }

    private void drawHand(Graphics2D g2,int length,float width,double angle,Color color){
        Point tip = pointOnCircle(length, angle);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(width));
        g2.drawLine(CENTER_X,CENTER_Y,tip.x,tip.y);
    }

}
