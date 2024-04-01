package pt.nomorealex.medmanagement.ui.sections.users.secondstage;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.ui.resources.ImageConstants;
import pt.nomorealex.medmanagement.ui.resources.ImageManager;

public class EditUserUI extends BorderPane {
    private static final int NR_FIELDS = 5;
    ServiceAPI dataModel;
    Stage stage2;
    Label lbUtente[];
    Label dataNasc, sexo;
    TextField tfUtente[];
    DatePicker datePicker;
    ComboBox<String> combo;
    TableView tvUtente;

    Label alergias, doencas;
    ListView<String> lAUtente, lDUtente;
    ObservableList<String> names;
    ObservableList<String> names1;
    ContextMenu contextMenu, contextMenu1, contextMenu2;
    MenuItem remover, remover1, remover2;

    Image img1;
    Button view1,view2,view3;


    Button apply, cancel;
    HBox ButtonApplyCancel;

    HBox hb1, hb2, hb3, hb4, hb5, hb6, hb7, hb8, hb9, hb10, hb11, hb12;
    VBox vb1, vb2, vb3, vb4, vb5, vb6;

    ScrollPane scrollPane;

    public EditUserUI(ServiceAPI serviceAPI) {
        this.dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        scrollPane = new ScrollPane();
        lbUtente = new Label[NR_FIELDS];
        tfUtente = new TextField[NR_FIELDS];

        for (int i = 0; i < NR_FIELDS; i++) {
            lbUtente[i] = new Label();
            tfUtente[i] = new TextField();
            tfUtente[i].setPrefWidth(Integer.MAX_VALUE);
            tfUtente[i].focusTraversableProperty().set(false);
            tfUtente[i].setPrefHeight(20);
        }
        lbUtente[0].setText("Name:");
        lbUtente[0].setMinWidth(80);
        lbUtente[1].setText("CC:");
        lbUtente[1].setMinWidth(50);
        lbUtente[2].setText("Email:");
        lbUtente[2].setMinWidth(75);
        lbUtente[3].setText("Emergency phone:");
        lbUtente[3].setMinWidth(220);
        lbUtente[4].setText("Occupation:");
        lbUtente[4].setMinWidth(110);
        tfUtente[4].setPromptText("(Optional)");
        dataNasc = new Label("Date of Birth:");
        sexo = new Label("Gender:");

        datePicker = new DatePicker();
        datePicker.focusTraversableProperty().set(false);
        datePicker.setEditable(false);

        combo = new ComboBox<String>();
        combo.setPromptText("Select a Gender");
        ObservableList<String> list = combo.getItems();
        list.add("Male");
        list.add("Female");


        hb1 = new HBox(lbUtente[0], tfUtente[0]);
        hb2 = new HBox(lbUtente[1], tfUtente[1]);
        hb3 = new HBox(lbUtente[2], tfUtente[2]);
        hb4 = new HBox(lbUtente[3], tfUtente[3]);
        hb5 = new HBox(lbUtente[4], tfUtente[4]);
        hb6 = new HBox(dataNasc, datePicker);
        hb7 = new HBox(sexo, combo);

        vb1 = new VBox(hb7, hb5);
        vb1.setPrefWidth(Integer.MAX_VALUE);
        vb1.setSpacing(15);

        vb2 = new VBox(hb1, hb2, hb6, hb3, hb4);
        vb2.setPrefWidth(Integer.MAX_VALUE);
        vb2.setSpacing(15);


        hb8 = new HBox(vb2, vb1);
        hb8.setPadding(new Insets(20));
        hb8.setSpacing(15);


        img1 = ImageManager.loadImageSize(ImageConstants.PLUSSIGN.getName(),24, 24, false, false);
        view1 = new Button();
        view1.setStyle("-fx-background-color: none");
        view1.setGraphic(new ImageView(img1));


        hb10 = new HBox(view1);
        hb10.setAlignment(Pos.CENTER_RIGHT);



        tvUtente = new TableView<>();
        TableColumn<String, String> medicamento = new TableColumn<String,String>("Pill");
        medicamento.setCellValueFactory(new PropertyValueFactory<String,String>("string"));
        TableColumn<String, String> obs = new TableColumn<String,String>("Time");
        obs.setCellValueFactory(new PropertyValueFactory<String,String>("string"));
        tvUtente.setPlaceholder(new Label("No Data"));
        tvUtente.setMaxHeight(300);
        tvUtente.getColumns().add(medicamento);
        tvUtente.getColumns().add(obs);
        tvUtente.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        vb6 = new VBox(hb10, tvUtente);


        view2 = new Button();
        view2.setStyle("-fx-background-color: none");
        view2.setGraphic(new ImageView(img1));


        alergias = new Label("Allergies");
        hb11 = new HBox(alergias, view2);
        hb11.setAlignment(Pos.CENTER);


        lAUtente = new ListView<String>();
        names = FXCollections.observableArrayList();
        lAUtente.setItems(names);
        lAUtente.setMaxHeight(50);
        lAUtente.setPrefWidth(Integer.MAX_VALUE);
        vb4 = new VBox(hb11, lAUtente);


        view3 = new Button();
        view3.setStyle("-fx-background-color: none");
        view3.setGraphic(new ImageView(img1));


        doencas = new Label("Illnesses");
        hb12 = new HBox(doencas, view3);
        hb12.setAlignment(Pos.CENTER);

        lDUtente = new ListView<String>();
        names1 = FXCollections.observableArrayList();
        lDUtente.setItems(names1);
        lDUtente.setMaxHeight(50);
        lDUtente.setPrefWidth(Integer.MAX_VALUE);
        vb5 = new VBox(hb12, lDUtente);


        contextMenu = new ContextMenu();
        remover = new MenuItem("Remove Pill");
        contextMenu.getItems().addAll(remover);
        tvUtente.setContextMenu(contextMenu);

        contextMenu1 = new ContextMenu();
        remover1 = new MenuItem("Remove Allergie");
        contextMenu1.getItems().addAll(remover1);
        lAUtente.setContextMenu(contextMenu1);

        contextMenu2 = new ContextMenu();
        remover2 = new MenuItem("Remove Illness");
        contextMenu2.getItems().addAll(remover2);
        lDUtente.setContextMenu(contextMenu2);


        hb9 = new HBox(vb4, vb5);
        hb9.setSpacing(20);
        hb9.setAlignment(Pos.CENTER);

        apply = new Button("Apply");
        apply.focusTraversableProperty().set(false);
        apply.setStyle("-fx-background-color: rgba(57,217,57,0.6);-fx-border-color: grey");

        cancel = new Button("Cancel");
        cancel.focusTraversableProperty().set(false);
        cancel.setStyle("-fx-background-color: rgba(217,70,70,0.59);-fx-border-color: grey");

        ButtonApplyCancel = new HBox(apply, cancel);
        ButtonApplyCancel.setSpacing(10);
        ButtonApplyCancel.setAlignment(Pos.CENTER_RIGHT);
        ButtonApplyCancel.setPadding(new Insets(0, 20, 0, 0));


        vb3 = new VBox(hb8, vb6, hb9, ButtonApplyCancel);
        vb3.setPadding(new Insets(20));
        vb3.setSpacing(15);


        scrollPane.setContent(vb3);
        scrollPane.fitToWidthProperty().set(true);
        this.setCenter(scrollPane);
    }

    private void registerHandlers() {
        dataModel.addListener("all",event -> Platform.runLater(this::update));

        cancel.setOnAction(event->{
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.close();
        });
    }

    private void update() {
    }
}
