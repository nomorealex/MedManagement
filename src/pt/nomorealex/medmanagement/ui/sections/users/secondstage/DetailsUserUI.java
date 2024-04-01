package pt.nomorealex.medmanagement.ui.sections.users.secondstage;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.ui.sections.CreditsUI;

public class DetailsUserUI extends BorderPane {
    private static final int NR_FIELDS = 5;
    ServiceAPI dataModel;
    Label name;
    Label cc;
    Label dateOfBirth;
    Label email;
    Label emergencyPhone;
    Label gender;
    Label occupation;
    TableView tableView;
    HBox hb1;
    VBox vb1;
    VBox vb2;

    public DetailsUserUI(ServiceAPI serviceAPI) {
        this.dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        name = new Label("Name");
        name.setId("labelnome");
        cc = new Label("CC");
        dateOfBirth = new Label("Date of Birth");
        email = new Label("Email");
        emergencyPhone = new Label("Emergency phone");
        gender = new Label("Gender");
        occupation = new Label("Occupation");

        vb1 = new VBox(name, cc, dateOfBirth, email, emergencyPhone);
        vb1.setSpacing(15);

        vb2 = new VBox(gender, occupation);
        vb2.setSpacing(15);


        hb1 = new HBox(vb1,vb2);
        hb1.setSpacing(20);

        tableView = new TableView<>();
        TableColumn<String, String> medicamento = new TableColumn<String,String>("Pill");
        medicamento.setCellValueFactory(new PropertyValueFactory<String,String>("string"));
        TableColumn<String,Integer> qtDia = new TableColumn<String,Integer>("Quantity/day");
        qtDia.setCellValueFactory(new PropertyValueFactory<String,Integer>("string"));
        TableColumn<String, String> obs = new TableColumn<String,String>("Time");
        obs.setCellValueFactory(new PropertyValueFactory<String,String>("string"));
        tableView.setPlaceholder(new Label("No Data"));
        tableView.setMaxHeight(300);

        tableView.getColumns().add(medicamento);
        tableView.getColumns().add(qtDia);
        tableView.getColumns().add(obs);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        vb1 = new VBox(hb1,tableView);
        vb1.setPadding(new Insets(20));
        vb1.setSpacing(15);
        this.setCenter(vb1);
        this.setBottom(new CreditsUI());
    }

    private void registerHandlers() {
        dataModel.addListener("all",event -> Platform.runLater(this::update));


    }

    private void update() {
        
    }
}
