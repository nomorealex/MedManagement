package pt.nomorealex.medmanagement.ui.sections;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;

public class MainUI extends BorderPane {
    ServiceAPI dataModel;
    Label lb;

    public MainUI(ServiceAPI serviceAPI) {
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        lb = new Label("Main Page - IN DEVELOPMENT.....");
        lb.setAlignment(Pos.CENTER);
        this.setCenter(lb);
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


    private String getMonth(int month) {
        return switch (month){
            case 1 -> "Jan";
            case 2 -> "Feb";
            case 3 -> "Mar";
            case 4 -> "Apr";
            case 5 -> "May";
            case 6 -> "Jun";
            case 7 -> "Jul";
            case 8 -> "Aug";
            case 9 -> "Sep";
            case 10 -> "Oct";
            case 11 -> "Nov";
            case 12 -> "Dec";
            default -> "";
        };
    }
}
