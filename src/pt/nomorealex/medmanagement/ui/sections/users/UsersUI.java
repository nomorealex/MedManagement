package pt.nomorealex.medmanagement.ui.sections.users;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;
import pt.nomorealex.medmanagement.ui.factories.LabelFactory;
import pt.nomorealex.medmanagement.ui.resources.ImageManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class UsersUI extends BorderPane {
    ServiceAPI dataModel;

    Label usersLabel;
    HBox hbHistorico;
    Button btnHistorico;
    HBox hBoxHeader;
    TextField tfPesquisa;
    TableView<String> tableView;
    ContextMenu contextMenu;
    TableColumn<String,String> CC;
    TableColumn<String,String> Nome;
    TableColumn<String,String> Idade;
    TableColumn<String,String> TelEm;
    ComboBox comboBox;
    Button btnAddUtente, btnTomaMed;
    Stage stage1,stage2;
    MenuItem detalhes,editar_utente,remover_utente;
    Optional<ButtonType> result;
    List<String> aux=null;
    public UsersUI(ServiceAPI serviceAPI) {
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }



    private void createViews() {
        usersLabel = LabelFactory.createLabel("Special","Users (dev)",Color.DARKBLUE,null,0);

        Image imgHistoric = ImageManager.loadImageSize("base-de-dados.png",32,32,false,false);

        btnHistorico = new Button("Users History");
        btnHistorico.setGraphic(new ImageView(imgHistoric));

        hbHistorico = new HBox(btnHistorico);
        hbHistorico.setAlignment(Pos.CENTER_RIGHT);
        hbHistorico.setPadding(new Insets(0,20,0,0));

        hBoxHeader = new HBox(usersLabel, hbHistorico);
        HBox.setHgrow(hbHistorico, Priority.ALWAYS);

        Image imgAddUtente = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../resources/images/add-friend.png")),32,32,false,false);
        btnAddUtente = new Button("Add User");
        btnAddUtente.setGraphic(new ImageView(imgAddUtente));

        Image imgTomaMed = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../resources/images/medicina.png")),32,32,false,false);
        btnTomaMed = new Button("Pills Schedule");
        btnTomaMed.setGraphic(new ImageView(imgTomaMed));

        HBox buttonsBox = new HBox(btnAddUtente, btnTomaMed);
        buttonsBox.setSpacing(10);
        buttonsBox.setAlignment(Pos.TOP_LEFT);

        tfPesquisa = new TextField();
        tfPesquisa.setPromptText("Search...");
        tfPesquisa.setPrefWidth(Integer.MAX_VALUE);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Search by CC",
                        "Search by Name"
                );
        comboBox = new ComboBox(options);
        comboBox.setMinWidth(150);
        comboBox.setValue(options.get(0));


        HBox hb = new HBox(comboBox, tfPesquisa);
        hb.setSpacing(30);

        tableView = new TableView<>();
        CC = new TableColumn<String,String>("CC");
        CC.setCellValueFactory(new PropertyValueFactory<String,String>("CC"));
        Nome = new TableColumn<String,String>("Name");
        Nome.setCellValueFactory(new PropertyValueFactory<String,String>("Nome"));
        Idade = new TableColumn<String,String>("Age");
        Idade.setCellValueFactory(new PropertyValueFactory<String,String>("idade"));
        TelEm = new TableColumn<String,String>("Emergency \nphone");
        TelEm.setCellValueFactory(new PropertyValueFactory<String,String>("TelefoneEme"));
        tableView.setPlaceholder(new Label("No Data"));
        tableView.setMaxHeight(500);
        tableView.getColumns().add(CC);
        tableView.getColumns().add(Nome);
        tableView.getColumns().add(Idade);
        tableView.getColumns().add(TelEm);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        contextMenu = new ContextMenu();
        detalhes = new MenuItem("details");
        editar_utente = new MenuItem("Edit User");
        remover_utente = new MenuItem("Remove User");
        contextMenu.getItems().addAll(detalhes, editar_utente, remover_utente);
        tableView.setContextMenu(contextMenu);

        VBox Arrange = new VBox(hBoxHeader,buttonsBox,hb,tableView);
        buttonsBox.setSpacing(10);
        Arrange.setPadding(new Insets(10,10,0,20));
        Arrange.setAlignment(Pos.TOP_LEFT);
        Arrange.setSpacing(20);
        this.setCenter(Arrange);

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
