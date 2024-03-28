package pt.nomorealex.medmanagement.ui.sections.users;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;


public class UsersUI extends BorderPane {
    ServiceAPI dataModel;

    Label lb;
    public UsersUI(ServiceAPI serviceAPI) {
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }



    private void createViews() {
        lb = new Label("Users Page - IN DEVELOPMENT.....");
        lb.setAlignment(Pos.CENTER);
        this.setCenter(lb);
    }

    private void registerHandlers() {
        dataModel.addListener("all",event -> Platform.runLater(this::update));
    }

    private void update(){
        if (dataModel.getState() != ServiceAPIStates.USERSSTATE) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }


}
