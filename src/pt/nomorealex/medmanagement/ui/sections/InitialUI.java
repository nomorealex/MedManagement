package pt.nomorealex.medmanagement.ui.sections;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.model.fsm.StatesAPI;

public class InitialUI extends BorderPane {

    ServiceAPI dataModel;
    Button btnStart,btnExit;
    Label lb;

    public InitialUI(ServiceAPI serviceAPI) {
        this.dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        lb = new Label("Pills Management");
        lb.setTextFill(Color.DARKBLUE);
        lb.setId("Special");
        btnStart = new Button("Next");
        btnStart.setMinWidth(100);
        btnExit  = new Button("Exit");
        btnExit.setMinWidth(100);
        VBox vBox = new VBox(lb,btnStart,btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        this.setCenter(vBox);
    }

    private void registerHandlers() {
        //dataModel.addPropertyChangeListener(evt -> { update(); });

        /*
        btnStart.setOnAction( event -> {
            dataModel.next();
        });
        btnExit.setOnAction( event -> {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirmação de Saída!");
            a.setHeaderText(null);
            a.setContentText("Deseja mesmo sair?");
            final Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
            try {
                stage.getIcons().add(new Image(this.getClass().getResource("./resources/images/pills.png").toString()));
            }catch (NullPointerException e){}
            a.showAndWait();
            if(a.getResult() == ButtonType.OK) {
                model.serializaObjeto();
                stage.close();
                Platform.exit();
                System.exit(0);
            }
        });*/

    }

    private void update() {

        if (dataModel.getState() != StatesAPI.INICIALSTATE) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
