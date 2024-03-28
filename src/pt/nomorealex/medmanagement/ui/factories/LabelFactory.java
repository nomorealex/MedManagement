package pt.nomorealex.medmanagement.ui.factories;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class LabelFactory {
    private LabelFactory(){}

    public static Label createLabel(String id, String textLabel, Color textFill, Pos alignment, double minWidth){
        textFill = validateTextFill(textFill);
        alignment = validateAlignment(alignment);
        minWidth = validateMinWidth(minWidth);
        Label label = new Label(textLabel);
        label.setId(id);
        label.setTextFill(textFill);
        label.setAlignment(alignment);
        label.setMinWidth(minWidth);
        return label;
    }

    private static Color validateTextFill(Color textFill) {
        return textFill != null ? textFill : Color.BLACK;
    }

    private static Pos validateAlignment(Pos alignment) {
        return alignment != null ? alignment : Pos.CENTER_LEFT;
    }

    private static double validateMinWidth(double minWidth) {
        if (minWidth < 0) {
            throw new IllegalArgumentException("minWidth should be greater than or equal to 0");
        } else if (minWidth == 0) {
            return Region.USE_COMPUTED_SIZE;
        }
        return minWidth;
    }
}
