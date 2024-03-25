package pt.nomorealex.medmanagement.ui;

import javafx.scene.layout.BorderPane;
import pt.nomorealex.medmanagement.model.ServiceAPI;

public class RootPane2 extends BorderPane {
    ServiceAPI dataModel;
    public RootPane2(ServiceAPI serviceAPI)  {
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
    }
    private void registerHandlers() {;
    }
    private void update() {}
}
