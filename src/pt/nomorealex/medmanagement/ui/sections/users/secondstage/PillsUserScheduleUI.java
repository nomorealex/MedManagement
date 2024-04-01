package pt.nomorealex.medmanagement.ui.sections.users.secondstage;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.ui.sections.CreditsUI;

public class PillsUserScheduleUI extends BorderPane {
    ServiceAPI dataModel;
    Label warningLabel;
    TableView tableView;
    Button confirmButton;
    VBox vb;

    public PillsUserScheduleUI(ServiceAPI serviceAPI) {
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        warningLabel = new Label("Users who need to take medication");

        tableView = new TableView<>();
        TableColumn<String,String> Utente = new TableColumn<String,String>("User");
        Utente.setCellValueFactory(new PropertyValueFactory<String,String>("string"));
        TableColumn<String,String> Medicamento = new TableColumn<String,String>("Pill");
        Medicamento.setCellValueFactory(new PropertyValueFactory<String,String>("string"));
        TableColumn<String,String> hora = new TableColumn<String,String>("Time");
        hora.setCellValueFactory(new PropertyValueFactory<String,String>("string"));
        tableView.setPlaceholder(new Label("No Data"));
        tableView.setPrefHeight(Integer.MAX_VALUE);
        tableView.getColumns().add(Utente);
        tableView.getColumns().add(Medicamento);
        tableView.getColumns().add(hora);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        confirmButton = new Button("Confirm");
        HBox hb = new HBox(confirmButton);
        hb.setAlignment(Pos.CENTER_RIGHT);

        vb = new VBox(warningLabel,tableView,hb);
        vb.setPadding(new Insets(20));
        vb.setSpacing(15);
        this.setCenter(vb);
        this.setBottom(new CreditsUI());
    }

    private void registerHandlers() {
        dataModel.addListener("all",event -> Platform.runLater(this::update));

    }

    private void update() {

    }
}
