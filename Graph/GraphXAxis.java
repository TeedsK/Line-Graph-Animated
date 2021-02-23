package Graph;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Dimension;
/**
 * @author Theo K - Teeds
 */
public class GraphXAxis extends Axis {
    int maximumValue = 0;
    public GraphXAxis() {
        setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        setMinimumSize(new Dimension(0, 40));
        setPreferredSize(new Dimension(1,40));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    /**
     * @param value the maximum value
     */
    public void setMaximumValue(int value) {
        this.maximumValue = value;
    }

    /**
     * Adds a section to the x axis
     * 
     * @param label the name of the label
     */
    public void addSection(String label) {
        super.addSection(label);
        maximumValue++;
    }

    /**
     * @return the maximum amount of labels
     */
    public double getMaximumValue() {
        return maximumValue;
    }

    /**
     * Gets the position of the section by its name
     * 
     * @param section the name of the section
     * @return the position of it
     */
    public int getPositionOfSection(String section) {
        for(int x = 0; x < maximumValue; x++) {
            if(super.getLabels().get(x).getText().equals(section)) {
                return x;
            }
        }
        return -1;
    }

}
