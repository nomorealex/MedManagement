package pt.nomorealex.medmanagement.ui.sections.orders;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;
import pt.nomorealex.medmanagement.ui.factories.LabelFactory;
import pt.nomorealex.medmanagement.ui.resources.ImageManager;

public class OrdersUI extends BorderPane {
    ServiceAPI dataModel;
    Label ordersLabel;


    TextField tf;

    Button lb0,lb1,lb2;
    Stage stageEncomenda;

    TableView<String> tableView;
    TableColumn<String,Long> ID;
    TableColumn<String,String> Nome;
    TableColumn<String,String> DataEncomenda;

    MenuItem detalhes,editar_utente,remover_utente;
    public OrdersUI(ServiceAPI serviceAPI){
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        ordersLabel = LabelFactory.createLabel("Special","Orders", Color.DARKBLUE, null, 0);

        HBox hBox = new HBox(ordersLabel);
        hBox.setSpacing(250);



        Image ButtonAddOrder = ImageManager.loadImageSize("plus1.png",32,32,false,false);
        lb1 = new Button("Add Order");
        lb1.setGraphic(new ImageView(ButtonAddOrder));

        Image ButtonEntryOrder = ImageManager.loadImageSize("base-de-dados.png",32,32,false,false);
        lb2 = new Button("Order Entry");
        lb2.setGraphic(new ImageView(ButtonEntryOrder));

        HBox buttonsBox = new HBox(lb1,lb2);
        buttonsBox.setSpacing(10);
        buttonsBox.setAlignment(Pos.TOP_LEFT);


        final ContextMenu contextMenu = new ContextMenu();
        detalhes = new MenuItem("Details");
        editar_utente = new MenuItem("Edit User");
        remover_utente = new MenuItem("Remove User");
        contextMenu.getItems().addAll(detalhes, editar_utente, remover_utente);




        tableView = new TableView<>();
        ID = new TableColumn<String,Long>("Order ID");
        ID.setCellValueFactory(new PropertyValueFactory<>("IDEncomenda"));
        Nome = new TableColumn<String, String>("Name");
        Nome.setCellValueFactory(new PropertyValueFactory<String,String>("nomeMedicamento"));
        DataEncomenda = new TableColumn<String,String>("Quantity");
        DataEncomenda.setCellValueFactory(new PropertyValueFactory<String,String>("Quantidade"));
        tableView.setPlaceholder(new Label("No Data"));
        tableView.setMaxHeight(500);
        tableView.getColumns().add(ID);
        tableView.getColumns().add(Nome);
        tableView.getColumns().add(DataEncomenda);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        VBox Arrange = new VBox(hBox,buttonsBox,tableView);
        buttonsBox.setSpacing(10);
        Arrange.setPadding(new Insets(10,10,0,20));
        Arrange.setAlignment(Pos.TOP_LEFT);
        Arrange.setSpacing(20);
        this.setCenter(Arrange);

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
