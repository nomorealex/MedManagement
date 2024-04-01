package pt.nomorealex.medmanagement.ui.sections;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;
import pt.nomorealex.medmanagement.ui.factories.LabelFactory;
import pt.nomorealex.medmanagement.ui.resources.ImageConstants;
import pt.nomorealex.medmanagement.ui.resources.ImageManager;

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
        Label dev = LabelFactory.createLabel("labelnome","In development(Not linked to data)",Color.DARKBLUE,null,0);
        buttonStart = new Button("Next");
        buttonStart.setMinWidth(100);
        buttonExit = new Button("Exit");
        buttonExit.setMinWidth(100);

        VBox verticalContainer = new VBox(initialLabel,dev,buttonStart,buttonExit);
        verticalContainer.setAlignment(Pos.CENTER);
        verticalContainer.setSpacing(50);
        this.setCenter(verticalContainer);
    }

    private void registerHandlers() {

        dataModel.addListener("all",event -> Platform.runLater(this::update));

        buttonStart.setOnAction( event -> {
            dataModel.next();
        });

        buttonExit.setOnAction( event -> {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm exit!");
            a.setHeaderText(null);
            a.setContentText("Do you really want to exit?");
            final Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
            try {
                Image image = ImageManager.loadImage(ImageConstants.PILLS.getName());
                stage.getIcons().add(image);
            }catch (NullPointerException e){}
            a.showAndWait();
            if(a.getResult() == ButtonType.OK){
                stage.close();
                Platform.exit();
                System.exit(0);
            }
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
