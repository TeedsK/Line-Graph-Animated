package Graph;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
/**
 * @author Theo K - Teeds
 */
public class Axis extends JPanel {
    Color foreground_color = new Color(255,255,255);
    ArrayList<JLabel> labels = new ArrayList<JLabel>();
    ArrayList<JLabel> Y_Axis_Labels = new ArrayList<JLabel>();
    public Axis() {
        setOpaque(false);
    }
    
    /**
     * @param label the name to add to the row/column
     */
    public void addSection(String label) {
        createLabel(label, false);
    }

    /**
     * @param value the value to add to the row/column
     */
    public void addSection(int value) {
        createLabel(Integer.toString(value), true);
    }

    /**
     * Sorts the Y axis from highest to lowest
     */
    public void sortYAxis() {
        removeAll();
        for(int x = Y_Axis_Labels.size(); x > 0; x--) {
            add(Y_Axis_Labels.get(x - 1));
            add(Box.createVerticalGlue());
        }
    }

    /**
     * @param color sets the foreground color
     */
    public void massChangeLabelColor(Color color) {
        for(JLabel label : labels) {
            label.setForeground(new Color(255,255,255));
        }
        foreground_color = color;
    }

    /**
     * Creates the label jlabel 
     * 
     * @param text the text of the jlabel
     * @param Y_Axis if the jlabel is being added to the Y axis
     */
    private void createLabel(String text, boolean Y_Axis) {
        JLabel label = new JLabel(text);
        labels.add(label);
        label.setForeground(foreground_color);
        Fonts.Methods.setFont(label);
        Fonts.Methods.setFontSize(label, 11.4f);
        if(Y_Axis) {
            Y_Axis_Labels.add(label);
            sortYAxis();
        } else {
            add(label);
            add(Box.createHorizontalGlue());
        }
    }

    /**
     * @return the labels
     */
    public ArrayList<JLabel> getLabels() {
        return labels;
    }
}
