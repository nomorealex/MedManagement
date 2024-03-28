package pt.nomorealex.medmanagement.ui.sections;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;
import pt.nomorealex.medmanagement.ui.factories.LabelFactory;

public class InitialUI extends BorderPane {

    ServiceAPI dataModel;
    Button buttonStart,buttonExit;
    Label initialLabel;

    public InitialUI(ServiceAPI serviceAPI) {
        this.dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        initialLabel = LabelFactory.createLabel("Special","Pills Management", Color.DARKBLUE, null, 0 );
        buttonStart = new Button("Next");
        buttonStart.setMinWidth(100);
        buttonExit = new Button("Exit");
        buttonExit.setMinWidth(100);

        VBox verticalContainer = new VBox(initialLabel,buttonStart,buttonExit);
        verticalContainer.setAlignment(Pos.CENTER);
        verticalContainer.setSpacing(50);
        this.setCenter(verticalContainer);
    }

    private void registerHandlers() {

        dataModel.addListener("all",event -> Platform.runLater(this::update));

        buttonStart.setOnAction( event -> {
            dataModel.next();
        });

    }

    private void update() {
        if (dataModel.getState() != ServiceAPIStates.INICIALSTATE) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
