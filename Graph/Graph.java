package Graph;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
/**
 * 
 * @author Teeds - Theo K
 */
public class Graph extends JPanel {
    GraphXAxis x_axis;
    GraphYAxis y_axis;
    GraphArea graph;
    Color background_color = new Color(0,0,0);
    int roundness = 10;
    public static int INITIAL_WIDTH;
    public static int INITIAL_HEIGHT;

    /**
     * Creates a graph object as a jpanel
     */
    public Graph() {
        graph = new GraphArea(this);
        x_axis = new GraphXAxis();
        y_axis = new GraphYAxis();
        setOpaque(false);
        setLayout(new BorderLayout());
        add(y_axis, BorderLayout.WEST);
        add(graph, BorderLayout.CENTER);
        add(x_axis, BorderLayout.SOUTH);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(background_color);
        graphics.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);
    }

    /**
     * @param roundness the roundness of the graph
     */
    public void setRoundness(int roundness) {
        this.roundness = roundness;
        repaint();
    }

    /**
     * 
     * @param color the color of the background
     */
    public void setBackgroundColor(Color color) {
        this.background_color = color;
        repaint();
    }

    /**
     * Adds a section to the X Axis
     * 
     * @param name the name of the category
     */
    public void addSectionToXAxis(String name) {
        x_axis.addSection(name);
        revalidate();
        repaint();
    }

    /**
     * @param color the color of the X axis labels
     */
    public void setXAxisLabelColor(Color color) {
        x_axis.massChangeLabelColor(color);
    }

    /**
     * @param color the color of the Y axis labels
     */
    public void setYAxisLabelColor(Color color) {
        y_axis.massChangeLabelColor(color);
    }


    /**
     * Adds a row to the Y axis
     * 
     * @param value the value to add to the Y axis
     */
    public void addSectionToYAxis(int value) {
        y_axis.addSection(value);
        revalidate();
        repaint();
    }

    /**
     * Sets the inital values for the width and height
     */
    public void setInitialValues(int width, int height) {
        Graph.INITIAL_WIDTH = width;
        Graph.INITIAL_HEIGHT = height;
    }

    /**
     * @return get the X Axis object
     */
    public GraphXAxis getXAxis() {
        return x_axis;
    }

    /**
     * @return get the Y axis object
     */
    public GraphYAxis getYAxis() {
        return y_axis;
    }

    /**
     * Get the graphing area object
     * 
     * @return the graphing area object
     */
    public GraphArea getGraphingArea() {
        return graph;
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(1000,700));
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        Graph graph = new Graph();
        // container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.add(graph);
        frame.add(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.pack();
        frame.setVisible(true);


        graph.addSectionToXAxis("Jan");
        graph.addSectionToXAxis("Feb");
        graph.addSectionToXAxis("Mar");
        graph.addSectionToXAxis("Apr");
        graph.addSectionToXAxis("May");
        graph.addSectionToXAxis("Jun");
        graph.addSectionToXAxis("July");
        graph.addSectionToXAxis("Aug");
        graph.addSectionToXAxis("Sep");
        graph.addSectionToXAxis("Oct");
        graph.addSectionToXAxis("Nov");
        graph.addSectionToXAxis("Dec");

        graph.addSectionToYAxis(0);
        graph.addSectionToYAxis(100);
        graph.addSectionToYAxis(200);
        graph.addSectionToYAxis(300);
        graph.addSectionToYAxis(400);
        graph.addSectionToYAxis(500);
        graph.addSectionToYAxis(600);
        graph.addSectionToYAxis(700);
        graph.addSectionToYAxis(800);
        graph.addSectionToYAxis(900);
        graph.addSectionToYAxis(1000);

        graph.getGraphingArea().addPoint("Jan",5);
        graph.getGraphingArea().addPoint("Feb",300);
        graph.getGraphingArea().addPoint("Mar",100);
        graph.getGraphingArea().addPoint("Apr",250);
        graph.getGraphingArea().addPoint("May",100);
        graph.getGraphingArea().addPoint("Jun",200);
        graph.getGraphingArea().addPoint("July",100);
        graph.getGraphingArea().addPoint("Aug",500);
        graph.getGraphingArea().addPoint("Sep",250);
        graph.getGraphingArea().addPoint("Oct",360);
        graph.getGraphingArea().addPoint("Nov",100);
        graph.getGraphingArea().addPoint("Dec",50);
        graph.setRoundness(10);
        // graph.setInitialValues(500,500);
        graph.getGraphingArea().start(0.1, 1);
    }
}