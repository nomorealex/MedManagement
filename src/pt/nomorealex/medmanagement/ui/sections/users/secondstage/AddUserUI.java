package pt.nomorealex.medmanagement.ui.sections.users.secondstage;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.ui.RefPane;
import pt.nomorealex.medmanagement.ui.factories.SceneFactory;
import pt.nomorealex.medmanagement.ui.factories.StageFactory;
import pt.nomorealex.medmanagement.ui.resources.ImageConstants;
import pt.nomorealex.medmanagement.ui.resources.ImageManager;
import pt.nomorealex.medmanagement.ui.sections.CreditsUI;
import pt.nomorealex.medmanagement.ui.structures.StageData;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class AddUserUI extends BorderPane {
    private static final int NR_FIELDS = 5;
    ServiceAPI dataModel;
    Stage thirdStage;
    Label[] userIdentifiersLabels;
    Label birthDate;
    Label gender;
    TextField[] userIdentifiersTextFields;
    DatePicker birthDatePicker;
    Calendar calendar;
    ComboBox<String> combo;
    TableView tvUtente;

    Label alergias,doencas;
    ListView<String> lAUtente, lDUtente;
    ObservableList<String> names;
    ObservableList<String> names1;

    Image img1;
    //ImageView view1,view2,view3;
    Button view1,view2,view3;


    Button apply,cancel;
    HBox ButtonApplyCancel;



    HBox hb1,hb2,hb3,hb4,hb5,hb6,hb7,hb8,hb9,hb10,hb11,hb12;
    VBox vb1,vb2,vb3,vb4,vb5,vb6;

    ScrollPane scrollPane;



    public AddUserUI(ServiceAPI serviceAPI) {
        this.dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        scrollPane = new ScrollPane();
        scrollPane.focusTraversableProperty().set(false);

        userIdentifiersLabels = new Label[NR_FIELDS];

        userIdentifiersTextFields = new TextField[NR_FIELDS];

        for(int i=0;i<NR_FIELDS;i++){
            userIdentifiersLabels[i] = new Label();
            userIdentifiersTextFields[i] = new TextField();
            userIdentifiersTextFields[i].setPrefWidth(Integer.MAX_VALUE);
            userIdentifiersTextFields[i].focusTraversableProperty().set(false);
            userIdentifiersTextFields[i].setPrefHeight(20);
        }
        userIdentifiersLabels[0].setText("Name:");
        userIdentifiersLabels[0].setMinWidth(80);
        userIdentifiersLabels[1].setText("CC:");
        userIdentifiersLabels[1].setMinWidth(50);
        userIdentifiersLabels[2].setText("Email:");
        userIdentifiersLabels[2].setMinWidth(75);
        userIdentifiersLabels[3].setText("Emergency phone:");
        userIdentifiersLabels[3].setMinWidth(220);
        userIdentifiersLabels[4].setText("Occupation:");
        userIdentifiersLabels[4].setMinWidth(110);
        userIdentifiersTextFields[4].setPromptText("(Optional)");
        birthDate = new Label("Date of Birth:");
        gender = new Label("Gender:");

        birthDatePicker = new DatePicker();
        birthDatePicker.focusTraversableProperty().set(false);
        birthDatePicker.setEditable(false);
        birthDatePicker.setDayCellFactory(d ->{
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisable(item.isAfter(LocalDate.now()));
                }
            };
        });

        LocalDate value = birthDatePicker.getValue();
        try {
            ZoneId zoneId = ZoneId.systemDefault();
            Date date = Date.from(value.atStartOfDay(zoneId).toInstant());
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }catch (NullPointerException e){}


        combo = new ComboBox<String>();
        combo.setPromptText("Male");
        combo.setValue("Male");
        ObservableList<String> list = combo.getItems();
        list.add("Female");
        list.add("Female");

        hb1 = new HBox(userIdentifiersLabels[0], userIdentifiersTextFields[0]);
        hb2 = new HBox(userIdentifiersLabels[1], userIdentifiersTextFields[1]);
        hb3 = new HBox(userIdentifiersLabels[2], userIdentifiersTextFields[2]);
        hb4 = new HBox(userIdentifiersLabels[3], userIdentifiersTextFields[3]);
        hb5 = new HBox(userIdentifiersLabels[4], userIdentifiersTextFields[4]);
        hb6 = new HBox(birthDate, birthDatePicker);
        hb7 = new HBox(gender,combo);

        vb1 = new VBox(hb7,hb5);
        vb1.setPrefWidth(Integer.MAX_VALUE);
        vb1.setSpacing(15);

        vb2 = new VBox(hb1,hb2,hb6,hb3,hb4);
        vb2.setPrefWidth(Integer.MAX_VALUE);
        vb2.setSpacing(15);

        hb8 = new HBox(vb2,vb1);
        hb8.setPadding(new Insets(20));
        hb8.setSpacing(15);

        img1 = ImageManager.loadImageSize(ImageConstants.PLUSSIGN.getName(),24,24,false,false);
        view1 = new Button();
        view1.setStyle("-fx-background-color: none");
        view1.setGraphic(new ImageView(img1));



        hb10 = new HBox(view1);
        hb10.setAlignment(Pos.CENTER_RIGHT);

        tvUtente = new TableView<>();
        TableColumn<String,String> medicamento = new TableColumn<String,String>("Pill");
        medicamento.setCellValueFactory(new PropertyValueFactory<String,String>("tempMed"));
        TableColumn<String,String> obs = new TableColumn<String,String>("Time");
        obs.setCellValueFactory(new PropertyValueFactory<String,String>("tempHoras"));
        tvUtente.setPlaceholder(new Label("No Data"));
        tvUtente.setMaxHeight(300);
        tvUtente.getColumns().add(medicamento);
        tvUtente.getColumns().add(obs);
        tvUtente.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        vb6 = new VBox(hb10,tvUtente);


        view2 = new Button();
        view2.setStyle("-fx-background-color: none");
        view2.setGraphic(new ImageView(img1));


        alergias = new Label("Allergies");
        hb11 = new HBox(alergias,view2);
        hb11.setAlignment(Pos.CENTER);

        lAUtente = new ListView<String>();
        names = FXCollections.observableArrayList ();
        lAUtente.setItems(names);
        lAUtente.setMaxHeight(50);
        lAUtente.setPrefWidth(Integer.MAX_VALUE);
        vb4 = new VBox(hb11,lAUtente);


        view3 = new Button();
        view3.setStyle("-fx-background-color: none");
        view3.setGraphic(new ImageView(img1));

        doencas = new Label("Illnesses");
        hb12 = new HBox(doencas,view3);
        hb12.setAlignment(Pos.CENTER);

        lDUtente = new ListView<String>();
        names1 =FXCollections.observableArrayList ();
        lDUtente.setItems(names1);
        lDUtente.setMaxHeight(50);
        lDUtente.setPrefWidth(Integer.MAX_VALUE);
        vb5 = new VBox(hb12,lDUtente);

        hb9 = new HBox(vb4,vb5);
        hb9.setSpacing(20);
        hb9.setAlignment(Pos.CENTER);

        apply = new Button("Apply");
        apply.focusTraversableProperty().set(false);
        apply.setStyle("-fx-background-color: rgba(57,217,57,0.6);-fx-border-color: grey");

        cancel = new Button("Cancel");
        cancel.focusTraversableProperty().set(false);
        cancel.setStyle("-fx-background-color: rgba(217,70,70,0.59);-fx-border-color: grey");

        ButtonApplyCancel = new HBox(apply,cancel);
        ButtonApplyCancel.setSpacing(10);
        ButtonApplyCancel.setAlignment(Pos.CENTER_RIGHT);
        ButtonApplyCancel.setPadding(new Insets(0,20,0,0));

        vb3 = new VBox(hb8,vb6,hb9,ButtonApplyCancel);
        vb3.setPadding(new Insets(20));
        vb3.setSpacing(15);

        scrollPane.setContent(vb3);
        scrollPane.fitToWidthProperty().set(true);
        this.setCenter(scrollPane);
        this.setBottom(new CreditsUI());
    }



    private void registerHandlers() {
        dataModel.addListener("all",event -> Platform.runLater(this::update));


        view1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                thirdStage = StageFactory.configurateStage(new StageData(800,300, getScene().getWindow(), Modality.WINDOW_MODAL,"PM - Add User Pills","pills.png"));
                thirdStage.setResizable(false);
                thirdStage.setScene(SceneFactory.configurateScene(RefPane.ADDUSERPILLPANE,dataModel));
                thirdStage.show();
            }
        });

        view2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                thirdStage = StageFactory.configurateStage(new StageData(800,300, getScene().getWindow(), Modality.WINDOW_MODAL,"PM - Add User Allergies","pills.png"));
                thirdStage.setResizable(false);
                thirdStage.setScene(SceneFactory.configurateScene(RefPane.ADDUSERALLERGIESPANE,dataModel));
                thirdStage.show();
            }
        });
        view3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                thirdStage = StageFactory.configurateStage(new StageData(800,300, getScene().getWindow(), Modality.WINDOW_MODAL,"PM - Add User Illness","pills.png"));
                thirdStage.setResizable(false);
                thirdStage.setScene(SceneFactory.configurateScene(RefPane.ADDUSERILLNESSPANE,dataModel));
                thirdStage.show();
            }
        });

        cancel.setOnAction(event->{
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.close();
        });


    }

    private void update() {

    }

}
