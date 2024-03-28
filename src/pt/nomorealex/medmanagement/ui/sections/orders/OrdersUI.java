package pt.nomorealex.medmanagement.ui.sections.orders;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;

public class OrdersUI extends BorderPane {
    ServiceAPI dataModel;
    Label lb;
    public OrdersUI(ServiceAPI serviceAPI){
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        lb = new Label("Orders Page - IN DEVELOPMENT.....");
        lb.setAlignment(Pos.CENTER);
        this.setCenter(lb);
    }

    private void registerHandlers() {
        dataModel.addListener("all",event -> Platform.runLater(this::update));
    }

    private void update() {
        if (dataModel.getState() != ServiceAPIStates.ORDERSSTATE) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }

}
