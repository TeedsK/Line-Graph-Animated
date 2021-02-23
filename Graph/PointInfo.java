package Graph;

import java.io.Serializable;

/**
 * @author Theo k - teeds
 */
public class PointInfo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6122078601012867081L;
    int x;
    int y;
    int lineX = -1;
    int lineY = -1;
    Graph graph;
    /**
     * @param graph the graph object
     * @param x the x of where the point is
     * @param the y of where the point is
     */
    public PointInfo(Graph graph, int x, int y) {
        this.graph = graph;
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return the x location on the graph
     */
    public int getX() {
        return (int) ((x / graph.getXAxis().getMaximumValue()) * graph.getGraphingArea().getWidth());
    }

    /**
     * @return the y location on the graph
     */
    public int getY() {
        return (int) ((y / graph.getYAxis().getMaximumValue()) * graph.getGraphingArea().getHeight());
    }

    /**
     * @return gets the X location of the line
     */
    public int getLineX() {
        if(Graph.INITIAL_WIDTH != 0) {
            return (int) ((lineX / (Graph.INITIAL_WIDTH * 1.0)) * graph.getGraphingArea().getWidth());
        }
        return lineX;
    }

    /**
     * @return gets the Y location of the line
     */
    public int getLineY() {
        if(Graph.INITIAL_HEIGHT != 0) {
            return (int) ((lineY / (Graph.INITIAL_HEIGHT * 1.0)) * graph.getGraphingArea().getHeight());
        }
        return lineY;
    }

    /**
     * @param value sets the X location of the line
     */
    public void setLineX(int value) {
        lineX = value;
    }

    /**
     * @param value sets the Y location of the line
     */
    public void setLineY(int value) {
        lineY = value;
    }
}
