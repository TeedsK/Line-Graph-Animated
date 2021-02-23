package Graph;

import java.awt.Dimension;
import javax.swing.BoxLayout;
/**
 * @author Theo K - Teeds
 */
public class GraphYAxis extends Axis {
    int totalRows = 0;
    int maximumValue = 0;
    public GraphYAxis() {
        super();
        setMinimumSize(new Dimension(40, 1));
        setPreferredSize(new Dimension(40, 1));
        setMaximumSize(new Dimension(40, Integer.MAX_VALUE));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * @return the maximum amount of rows 
     */
    public double getTotalRows() {
        return totalRows;
    }

    /**
     * @return the maximum amount value
     */
    public double getMaximumValue() {
        return maximumValue;
    }

    /**
     * @param value the value of the Y axis 
     */
    @Override
    public void addSection(int value) {
        super.addSection(value);
        if(value > maximumValue) {
            maximumValue = value;
        }
        totalRows++;
    }
}
