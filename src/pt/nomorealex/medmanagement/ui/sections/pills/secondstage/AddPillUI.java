package pt.nomorealex.medmanagement.ui.sections.pills.secondstage;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class AddPillUI extends BorderPane {
    private static final int NR_FIELDS = 5;
    ServiceAPI dataModel;
    Label lbMed[];
    Label dataNasc;
    DatePicker datePicker;

    Calendar calendar;
    TextField tfMed[];
    TextArea ta;

    Button apply,cancel;

    HBox hb1,hb2,hb3,hb4,hb5,hb6,ButtonApplyCancel;
    VBox vb2;


    public AddPillUI(ServiceAPI serviceAPI) {
        this.dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        lbMed = new Label[NR_FIELDS];
        tfMed = new TextField[NR_FIELDS];

        for (int i = 0; i < NR_FIELDS; i++) {
            lbMed[i] = new Label();
            tfMed[i] = new TextField();
            tfMed[i].setPrefWidth(Integer.MAX_VALUE);
            tfMed[i].setMaxWidth(220);
            tfMed[i].focusTraversableProperty().set(false);
            tfMed[i].setPrefHeight(20);
        }
        lbMed[0].setText("Name:");
        lbMed[1].setText("ID:");
        lbMed[2].setText("Administration mode:");
        lbMed[3].setText("Active principle:");
        lbMed[4].setText("Labs:");
        dataNasc = new Label("Expiration date:");


        ta = new TextArea();
        ta.setText("BULA: Pill description");
        ta.setPrefWidth(Integer.MAX_VALUE);
        ta.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(ta.getText().length() > 200){
                    String aux = ta.getText().substring(0, 200);
                    ta.setText(aux);
                }
            }
        });

        datePicker = new DatePicker();
        datePicker.focusTraversableProperty().set(false);
        datePicker.setEditable(false);
        datePicker.setDayCellFactory(d ->{
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisable(item.isBefore(LocalDate.now()));
                }
            };
        });





        hb1 = new HBox(lbMed[0], tfMed[0]);
        hb1.setSpacing(15);
        hb2 = new HBox(lbMed[1], tfMed[1]);
        hb2.setSpacing(15);
        hb3 = new HBox(lbMed[2], tfMed[2]);
        hb3.setSpacing(15);
        hb4 = new HBox(lbMed[3], tfMed[3]);
        hb4.setSpacing(15);
        hb5 = new HBox(lbMed[4], tfMed[4]);
        hb5.setSpacing(15);
        hb6 = new HBox(dataNasc, datePicker);
        hb6.setSpacing(15);

        vb2 = new VBox(hb1, hb2, hb6, hb3, hb4,hb5,ta);
        vb2.setSpacing(15);

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



        HBox buttonsBox = new HBox(apply,cancel);
        buttonsBox.setSpacing(10);
        buttonsBox.setAlignment(Pos.CENTER_RIGHT);

        LocalDate value = datePicker.getValue();

        try {
            ZoneId zoneId = ZoneId.systemDefault();
            Date date = Date.from(value.atStartOfDay(zoneId).toInstant());
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }catch (NullPointerException e){}



        VBox vb1 = new VBox(vb2,buttonsBox);
        vb1.setPadding(new Insets(20));
        vb1.setSpacing(15);
        setCenter(vb1);
    }
    private void registerHandlers() {
        dataModel.addListener("all",event -> Platform.runLater(this::update));

        cancel.setOnAction(event->{
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.close();
        });

    }

    private void update() {
        if (dataModel.getState() != ServiceAPIStates.PILLSSTATE) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);



    }

}
