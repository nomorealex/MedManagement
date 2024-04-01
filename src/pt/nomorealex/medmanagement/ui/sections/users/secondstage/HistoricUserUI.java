package pt.nomorealex.medmanagement.ui.sections.users.secondstage;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.nomorealex.medmanagement.model.ServiceAPI;

public class HistoricUserUI extends BorderPane {
    private static final int NR_FIELDS = 5;
    ServiceAPI dataModel;
    ListView<String> lvHistoric;
    VBox vb;

    public HistoricUserUI(ServiceAPI serviceAPI) {
        this.dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        lvHistoric = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList ();
        lvHistoric.setItems(items);
        lvHistoric.setPrefHeight(Integer.MAX_VALUE);


        vb = new VBox(lvHistoric);
        vb.setPadding(new Insets(20));
        vb.setSpacing(15);
        this.setCenter(vb);
    }

    private void registerHandlers() {
        dataModel.addListener("all",event -> Platform.runLater(this::update));

    }

    private void update() {
        
    }
}
