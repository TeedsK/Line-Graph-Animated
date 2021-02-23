package Graph;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
/**
 * 
 * @author Theo K - Teeds
 */
public class GraphArea extends JPanel{
    ArrayList<PointInfo> points = new ArrayList<PointInfo>();
    Color Graph_Lines = new Color(100,100,100);
    Color Graph_Points = new Color(0,0,0);
    Color Graph_Background_Lines = new Color(200,200,200);
    Graph graph;
    BasicStroke stroke = new BasicStroke(2.0f);
    /**
     * The graph area
     * @param graph the graph parent
     */
    public GraphArea(Graph graph) {
        this.graph = graph;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    /**
     * Sets the color of the lines between the points
     * @param color the line color
     */
    public void setGraphLineColor(Color color) {
        this.Graph_Lines = color;
        repaint();
    }

    /**
     * Sets the color of the points 
     * @param color the point color
     */
    public void setGraphPointColor(Color color) {
        this.Graph_Points = color;
        repaint();
    }

    /**
     * Sets the color of the background lines 
     * @param color the background lines color
     */
    public void setGraphBackgroundLinesColor(Color color) {
        this.Graph_Background_Lines = color;
    }

    /**
     * Adds a point to the graph
     * 
     * @param x the X cordinate
     * @param y the Y cordinate
     */
    public void addPoint(int x, int y) {
        points.add(new PointInfo(graph, x, y));
        repaint();
    }

    /**
     * Adds a point to the graph
     * 
     * @param section the X section to set the point to
     * @param y the Y cordinate
     */
    public boolean addPoint(String section, int y) {
        int position = graph.getXAxis().getPositionOfSection(section);
        if(position != -1) {
            points.add(new PointInfo(graph, position, y));
            repaint();
        }
        return false;
    }

    /**
     * set a custom stroke size for the lines
     * 
     * @param size the size of the stroke of the lines
     */
    public void setStrokeSize(float size) {
        this.stroke = new BasicStroke(size);
        repaint();
    }

    /**
     * Resets the points on the graph
     */
    public void reset() {
        for(PointInfo point : points) {
            point.setLineX(-1);
            point.setLineY(-1);
        }
    }

    /**
     * Starts the animation process to connect the points
     */
    public void start() {
        animation(0.01, 10);
    }

    /**
     * Starts the animation process to connect the points
     * @param amountToAdd the value to add to the line per iteration
     */
    public void start(double amountToAdd) {
        animation(amountToAdd, 10);
    }

    /**
     * Starts the animation process to connect the points
     * @param amountToAdd the value to add to the line per iteration
     * @param sleepTime the amount of time to sleep per iteration
     */
    public void start(double amountToAdd, int sleepTime) {
        animation(amountToAdd, sleepTime);
    }

    private void animation(double multiplier, int sleepTime) {
        Thread t = new Thread() {
            public void run() {
                for(int p = 0; p < points.size() - 1; p++) {
                    double times = 0.0;
                    while(times <= 1.0) {
                        int y1 = getHeight() - points.get(p).getY();
                        int x1 = points.get(p).getX();
                        int y2 = getHeight() - points.get(p + 1).getY();
                        int x2 = points.get(p + 1).getX();
                        double slope = getSlope(x1, y1, x2, y2);
                        double yIntercept = getYInterceptValue(slope, x1, y1);
                        double position = ((x2 - x1) * times) + x1;
                        points.get(p).setLineX((int) getXAxisPortionOfLine(x1, y1, x2, y2, times));
                        points.get(p).setLineY((int) getYAxisPortionOfLine(slope, yIntercept, position));
                        repaint();
                        try {
                            Thread.sleep(sleepTime);
                        } catch(Exception err1) {}
                        times+=0.01;
                    }
                } 
            }
        }; t.start();
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(stroke);
        graphics.setColor(Graph_Background_Lines);
        for(int x = 0; x < graph.getYAxis().getTotalRows(); x++) {
            graphics.fillRoundRect(0, (int) (x / (graph.getYAxis().getTotalRows() * 1.0) * graph.getGraphingArea().getHeight()) + 7, getWidth(), 2, 2, 2);
        }

        //Paints the lines between points
        for(int t = 0; t < points.size() - 1; t++) {
            if(points.get(t).getLineX() != -1) {
                int y = getHeight() - points.get(t).getY();
                int x = points.get(t).getX();
                int pointX = points.get(t).getLineX();
                int pointY = getHeight() - points.get(t).getLineY();
                graphics.setColor(Graph_Lines);
                graphics.drawLine(x, y, pointX, pointY);
            }
        }

        //Paints the points
        for(int t = 0; t < points.size(); t++) {
            if(points.get(t).getLineX() != -1) {
                int circle_size = 8;
                int y = getHeight() - points.get(t).getY();
                int x = points.get(t).getX();
                graphics.setColor(Graph_Points);
                graphics.fillRoundRect(x - (circle_size / 2), y - (circle_size / 2), circle_size, circle_size, 10, 10);
            }
        }
    }

    /**
     * Get the Y Axis given the X cordinate
     * 
     * @param slope the slope of the line
     * @param yIntercept the y intercept of the line
     * @param x the X value
     * @return the y value
     */
    private double getYAxisPortionOfLine(double slope, double yIntercept, double x) {
        return (((slope) * x)) + yIntercept;
    }

    /**
     * Gets the slope of the line
     * 
     * @param x1 the X cordinate of the first point
     * @param y1 the Y cordinate of the first point
     * @param x2 the X cordinate of the second point
     * @param y2 the Y cordinate of the second point
     * @return the slope of the line
     */
    private double getSlope(int x1, int y1, int x2, int y2) {
        return (y1 - (y2 * 1.0)) / (x2 - (x1 * 1.0));
    }

    /**
     * Gets the X part given the percentage of the line completition
     * 
     * @param x1 the X cordinate of the first point
     * @param y1 the Y cordinate of the first point
     * @param x2 the X cordinate of the second point
     * @param y2 the Y cordinate of the second point
     * @param percentage the percentage of the line to complete
     * @return the X point compared to the percentage  
     */
    private double getXAxisPortionOfLine(int x1, int y1, int x2, int y2, double percentage) {
        return ((int) ((x2 - x1) * percentage)) + x1;
    }

    /**
     * Gets the Y intercept of the line
     * 
     * @param slope the slope of the line
     * @param x a X point that is on the line
     * @param y a Y point that is on the line
     * @return the Y intercept
     */
    private double getYInterceptValue(double slope, int x, int y) {
        return ((getHeight() - y) - (slope * x));
    }
}
