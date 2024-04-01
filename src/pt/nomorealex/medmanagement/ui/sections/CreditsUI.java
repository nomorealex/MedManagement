package pt.nomorealex.medmanagement.ui.sections;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class CreditsUI extends HBox {
    public CreditsUI() {
        createViews();
    }
    private void createViews() {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));
        setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label lbOut = new Label("Powered by nomorealex");
        lbOut.setId("credits");
        lbOut.setAlignment(Pos.CENTER);
        lbOut.setMinWidth(100);
        this.getChildren().add(lbOut);
    }
}
