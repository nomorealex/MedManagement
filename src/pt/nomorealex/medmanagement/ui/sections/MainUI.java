package pt.nomorealex.medmanagement.ui.sections;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;
import pt.nomorealex.medmanagement.ui.factories.LabelFactory;

public class MainUI extends BorderPane {
    ServiceAPI dataModel;
    Label mainLabel;

    public MainUI(ServiceAPI serviceAPI) {
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        mainLabel = LabelFactory.createLabel(null, "Main Page", Color.DARKBLUE,Pos.CENTER,0);
        this.setCenter(mainLabel);
    }

    private void registerHandlers() {
        dataModel.addListener("all",event -> Platform.runLater(this::update));
    }

    private void update() {
        if (dataModel.getState() != ServiceAPIStates.MAINSTATE) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
