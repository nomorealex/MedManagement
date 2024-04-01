package pt.nomorealex.medmanagement.ui.sections.pills;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

import java.util.List;

public class PillsUI extends BorderPane {
    ServiceAPI dataModel;
    Label pillsLabel;

    TableView<String> tableView;

    TableColumn<String,String> ID;
    TableColumn<String,String> Nome;
    TableColumn<String,String> QtStock;


    TextField tfPesquisa;
    ComboBox comboBox;

    Button lb0,lb1,lb2;

    ContextMenu contextMenu;
    MenuItem detalhes,editar_medicamento,remover_medicamento;
    Stage stage1;

    List<String> aux=null;

    public PillsUI(ServiceAPI serviceAPI){
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        pillsLabel = LabelFactory.createLabel("Special", "Pills", Color.DARKBLUE, null, 0);

        HBox hBox = new HBox(pillsLabel);
        //hBox.setSpacing(250);

        Image ButtonAddUser = ImageManager.loadImageSize("plus1.png",32,32,false,false);
        lb1 = new Button("Add Pill");
        lb1.setGraphic(new ImageView(ButtonAddUser));

        HBox buttonsBox = new HBox(lb1);
        buttonsBox.setSpacing(10);
        buttonsBox.setAlignment(Pos.TOP_LEFT);

        tfPesquisa = new TextField();
        tfPesquisa.setPromptText("Search...");
        tfPesquisa.setPrefWidth(Integer.MAX_VALUE);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Search by ID",
                        "Search by Name"
                );
        comboBox = new ComboBox(options);
        comboBox.setMinWidth(150);
        comboBox.setValue(options.get(0));



        HBox hb = new HBox(comboBox,tfPesquisa);
        hb.setSpacing(30);


        tableView = new TableView();

        ID = new TableColumn<String,String>("ID");
        ID.setCellValueFactory(new PropertyValueFactory<String,String>("ID"));
        Nome = new TableColumn<String,String>("Name");
        Nome.setCellValueFactory(new PropertyValueFactory<String,String>("Nome"));
        QtStock = new TableColumn<String,String>("Qt.stock");
        QtStock.setCellValueFactory(new PropertyValueFactory<String,String>("stock"));
        tableView.setPlaceholder(new Label("No Data"));
        tableView.setMaxHeight(600);
        tableView.getColumns().add(ID);
        tableView.getColumns().add(Nome);
        tableView.getColumns().add(QtStock);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        contextMenu = new ContextMenu();
        detalhes = new MenuItem("Details");
        editar_medicamento = new MenuItem("Edit Pill");
        remover_medicamento = new MenuItem("Remove Pill");
        contextMenu.getItems().addAll(detalhes, editar_medicamento, remover_medicamento);
        tableView.setContextMenu(contextMenu);

        VBox Arrange = new VBox(hBox,buttonsBox,hb,tableView);
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
        if (dataModel.getState() != ServiceAPIStates.PILLSSTATE) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }

}
