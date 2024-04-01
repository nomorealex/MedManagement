package pt.nomorealex.medmanagement.ui.sections.users;

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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;
import pt.nomorealex.medmanagement.ui.RefPane;
import pt.nomorealex.medmanagement.ui.factories.LabelFactory;
import pt.nomorealex.medmanagement.ui.factories.SceneFactory;
import pt.nomorealex.medmanagement.ui.factories.StageFactory;
import pt.nomorealex.medmanagement.ui.resources.ImageConstants;
import pt.nomorealex.medmanagement.ui.resources.ImageManager;
import pt.nomorealex.medmanagement.ui.structures.StageData;


public class UsersUI extends BorderPane {
    ServiceAPI dataModel;
    Stage secondStage;
    Label usersLabel;
    Button historicButton;
    Button userAddButton;
    Button pillsScheduleButton;
    HBox historicHbox;
    HBox headerHbox;
    TextField searchTextfield;
    TableView<String> usersTable;
    TableColumn<String,String> cc;
    TableColumn<String,String> name;
    TableColumn<String,String> age;
    TableColumn<String,String> emergencyPhone;
    ComboBox<String> searchOptions;
    ContextMenu usersContextMenu;
    MenuItem detailsUserContextMenuItem;
    MenuItem editUserContextMenuItem;
    MenuItem removeUserContextMenuItem;


    public UsersUI(ServiceAPI serviceAPI) {
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }



    private void createViews() {
        usersLabel = LabelFactory.createLabel("Special","Users",Color.DARKBLUE,null,0);

        Image imgHistoric = ImageManager.loadImageSize(ImageConstants.DATABASE.getName(), 32,32,false,false);

        historicButton = new Button("Users History");
        historicButton.setGraphic(new ImageView(imgHistoric));

        historicHbox = new HBox(historicButton);
        historicHbox.setAlignment(Pos.CENTER_RIGHT);
        historicHbox.setPadding(new Insets(0,20,0,0));

        headerHbox = new HBox(usersLabel, historicHbox);
        HBox.setHgrow(historicHbox, Priority.ALWAYS);

        Image imgAddUtente = ImageManager.loadImageSize(ImageConstants.ADDPERSON.getName(), 32,32,false,false);
        userAddButton = new Button("Add User");
        userAddButton.setGraphic(new ImageView(imgAddUtente));

        Image imgTomaMed = ImageManager.loadImageSize(ImageConstants.PILLSSCHEDULE.getName(), 32,32,false,false);
        pillsScheduleButton = new Button("Pills Schedule");
        pillsScheduleButton.setGraphic(new ImageView(imgTomaMed));

        HBox buttonsBox = new HBox(userAddButton, pillsScheduleButton);
        buttonsBox.setSpacing(10);
        buttonsBox.setAlignment(Pos.TOP_LEFT);

        searchTextfield = new TextField();
        searchTextfield.setPromptText("Search...");
        searchTextfield.setPrefWidth(Integer.MAX_VALUE);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Search by CC",
                        "Search by Name"
                );
        searchOptions = new ComboBox(options);
        searchOptions.setMinWidth(150);
        searchOptions.setValue(options.get(0));


        HBox hb = new HBox(searchOptions, searchTextfield);
        hb.setSpacing(30);

        usersTable = new TableView<>();
        cc = new TableColumn<String,String>("CC");
        cc.setCellValueFactory(new PropertyValueFactory<String,String>("string"));
        name = new TableColumn<String,String>("Name");
        name.setCellValueFactory(new PropertyValueFactory<String,String>("string"));
        age = new TableColumn<String,String>("Age");
        age.setCellValueFactory(new PropertyValueFactory<String,String>("string"));
        emergencyPhone = new TableColumn<String,String>("Emergency \nphone");
        emergencyPhone.setCellValueFactory(new PropertyValueFactory<String,String>("string"));
        usersTable.setPlaceholder(new Label("No Data"));
        usersTable.setMaxHeight(500);
        usersTable.getColumns().add(cc);
        usersTable.getColumns().add(name);
        usersTable.getColumns().add(age);
        usersTable.getColumns().add(emergencyPhone);
        usersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        usersContextMenu = new ContextMenu();
        detailsUserContextMenuItem = new MenuItem("Details");
        editUserContextMenuItem = new MenuItem("Edit User");
        removeUserContextMenuItem = new MenuItem("Remove User");
        usersContextMenu.getItems().addAll(detailsUserContextMenuItem, editUserContextMenuItem, removeUserContextMenuItem);
        usersTable.setContextMenu(usersContextMenu);

        VBox Arrange = new VBox(headerHbox,buttonsBox,hb, usersTable);
        buttonsBox.setSpacing(10);
        Arrange.setPadding(new Insets(10,10,0,20));
        Arrange.setAlignment(Pos.TOP_LEFT);
        Arrange.setSpacing(20);
        this.setCenter(Arrange);

    }

    private void registerHandlers() {
        dataModel.addListener("all",event -> Platform.runLater(this::update));

        userAddButton.setOnAction(actionEvent -> {
            secondStage = StageFactory.configurateStage(new StageData(950,600,this.getScene().getWindow(), Modality.WINDOW_MODAL,"PM - Add User",ImageConstants.PILLS.getName()));
            secondStage.setScene(SceneFactory.configurateScene(RefPane.ADDUSERPANE,dataModel));
            secondStage.show();
        });

        pillsScheduleButton.setOnAction(actionEvent -> {
            secondStage = StageFactory.configurateStage(new StageData(950,600,this.getScene().getWindow(), Modality.WINDOW_MODAL,"PM - Pills User Schedule",ImageConstants.PILLS.getName()));
            secondStage.setScene(SceneFactory.configurateScene(RefPane.PILLSUSERSSCHEDULEPANE,dataModel));
            secondStage.show();
        });

        historicButton.setOnAction(actionEvent -> {
            secondStage = StageFactory.configurateStage(new StageData(950,600,this.getScene().getWindow(), Modality.WINDOW_MODAL,"PM - Users Historic",ImageConstants.PILLS.getName()));
            secondStage.setScene(SceneFactory.configurateScene(RefPane.HISTORICUSERPANE,dataModel));
            secondStage.show();
        });
    }

    private void update(){
        if (dataModel.getState() != ServiceAPIStates.USERSSTATE) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
