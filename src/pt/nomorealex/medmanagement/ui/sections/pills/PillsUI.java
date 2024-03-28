package pt.nomorealex.medmanagement.ui.sections.pills;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;

public class PillsUI extends BorderPane {
    ServiceAPI dataModel;
    Label lb;
    public PillsUI(ServiceAPI serviceAPI){
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        lb = new Label("Pills Page - IN DEVELOPMENT.....");
        lb.setAlignment(Pos.CENTER);
        this.setCenter(lb);
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
    /*
    ServiceAPI dataModel;

    TableView<Medicamento> tableView;

    TableColumn<Medicamento,String> ID;
    TableColumn<Medicamento,String> Nome;
    TableColumn<Medicamento,String> QtStock;



    TextField tfPesquisa;
    ComboBox comboBox;

    Button lb0,lb1,lb2;

    ContextMenu contextMenu;
    MenuItem detalhes,editar_medicamento,remover_medicamento;
    Stage stage1;

    List<Medicamento> aux=null;


    public PillsUI(ServiceAPI serviceAPI) {
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        lb = new Label("Medicamentos");
        lb.setTextFill(Color.DARKBLUE);
        lb.setId("Special");

        HBox hBox = new HBox(lb);
        //hBox.setSpacing(250);


        Image ButtonAddUser = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../resources/images/plus1.png")),32,32,false,false);
        lb1 = new Button("Adicionar Medicamento");
        lb1.setGraphic(new ImageView(ButtonAddUser));

        HBox buttonsBox = new HBox(lb1);
        buttonsBox.setSpacing(10);
        buttonsBox.setAlignment(Pos.TOP_LEFT);


        tfPesquisa = new TextField();
        tfPesquisa.setPromptText("Escreva o que quer pesquisar");
        tfPesquisa.setPrefWidth(Integer.MAX_VALUE);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Procurar por ID",
                        "Procurar por Nome"
                );
        comboBox = new ComboBox(options);
        comboBox.setMinWidth(150);
        comboBox.setValue(options.get(0));



        HBox hb = new HBox(comboBox,tfPesquisa);
        hb.setSpacing(30);


        tableView = new TableView();

        ID = new TableColumn<Medicamento,String>("ID");
        ID.setCellValueFactory(new PropertyValueFactory<Medicamento,String>("ID"));
        Nome = new TableColumn<Medicamento,String>("Nome");
        Nome.setCellValueFactory(new PropertyValueFactory<Medicamento,String>("Nome"));
        QtStock = new TableColumn<Medicamento,String>("Qt.stock");
        QtStock.setCellValueFactory(new PropertyValueFactory<Medicamento,String>("stock"));
        tableView.setPlaceholder(new Label("Sem dados"));
        tableView.setMaxHeight(600);
        tableView.getColumns().add(ID);
        tableView.getColumns().add(Nome);
        tableView.getColumns().add(QtStock);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        contextMenu = new ContextMenu();
        detalhes = new MenuItem("Detalhes");
        editar_medicamento = new MenuItem("Editar Medicamento");
        remover_medicamento = new MenuItem("Remover Medicamento");
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
        model.addPropertyChangeListener(evt -> { update(); });

        tfPesquisa.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Platform.runLater(()->{
                    if(comboBox.getValue().toString().equals("Procurar por ID")) {
                        if(tfPesquisa.getText().trim().isBlank()){
                            update();
                        }else {
                            Medicamento aux = model.getMedicamentoID(tfPesquisa.getText().trim());
                            if (aux != null) {
                                tableView.getItems().clear();
                                tableView.getItems().add(aux);
                            } else {
                                ToastMessage.show(getScene().getWindow(), "Não existe nenhum Medicamento com esse numero registado!");
                            }
                        }
                    }
                    else if(comboBox.getValue().toString().equals("Procurar por Nome")){
                        if(tfPesquisa.getText().trim().isBlank()){
                            update();
                        }else {
                            aux = model.getMedicamentoNome(tfPesquisa.getText().trim());
                            if (aux.size() > 0) {
                                tableView.getItems().clear();
                                for (Medicamento a : aux)
                                    tableView.getItems().add(a);
                            } else {
                                ToastMessage.show(getScene().getWindow(), "Não existe nenhum Medicamento com esse nome registado!");
                            }
                        }
                    }
                });



            }
        });




        //  tableView.getItems().add(new Me("John", "Doe"));

        detalhes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(tableView.getSelectionModel().getSelectedItem() != null) {
                    Medicamento medicamento = tableView.getSelectionModel().getSelectedItem();
                    stage1.setScene(new Scene(new RootPane1(model, new DetalhesMedicamentoUI(model,medicamento)), 950, 600));
                    System.out.println("detalhes...");
                    stage1.setTitle("Detalhes");
                    stage1.show();
                }
            }
        });
        editar_medicamento.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(tableView.getSelectionModel().getSelectedItem() != null) {
                    Medicamento medicamento = tableView.getSelectionModel().getSelectedItem();
                    stage1.setScene(new Scene(new RootPane1(model, new EditarMedicamentoUI(model,medicamento)), 950, 600));
                    System.out.println("Editar...");
                    stage1.setTitle("Detalhes");
                    stage1.show();
                }
            }
        });
        remover_medicamento.setOnAction(event->{
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                Medicamento medicamento = tableView.getSelectionModel().getSelectedItem();
                if (model.removerMedicamento(medicamento.getID())) {
                    ToastMessage.show(getScene().getWindow(), "Medicamento removido com sucesso!");
                } else
                    ToastMessage.show(getScene().getWindow(), "Medicamento não foi removido com sucesso!");
            }
        });
*/
       /* remover_medicamento.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Alerta!");
                a.setHeaderText(null);
                a.setContentText("Deseja mesmo\ncolocar o medicamento no histórico?");
                final Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
                try {
                    stage.getIcons().add(new Image(this.getClass().getResource("../resources/images/pills.png").toString()));
                }catch (NullPointerException e){}
                a.showAndWait();
                if(a.getResult() == ButtonType.OK){
                    stage.close();
                    Platform.exit();
                    System.exit(0);
                }

            }
        });
*/

/*
        DropShadow shadow = new DropShadow();
        lb1.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        lb1.setEffect(shadow);
                        lb1.setStyle("-fx-border-color: #1a83c6; -fx-border-width: 2");
                    }
                });

        lb1.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        lb1.setEffect(null);
                        lb1.setStyle("-fx-border-color: none");
                        lb1.focusTraversableProperty().set(false);
                    }
                });


        lb1.setOnAction(actionEvent -> {
            stage1.setScene(new Scene(new RootPane1(model,new AdicionarMedicamentoUI(model)),950,600));
            stage1.setTitle("Adicionar Medicamento");
            stage1.show();

        });




    }
    private void update() {

        if (model.getState() != MEEstados.ESTADOMEDICAMENTOS) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

        tableView.getItems().clear();
        List<Medicamento> medicamentos =  model.getMedicamentos();
        if(medicamentos.size() != 0) {
            for (Medicamento u : medicamentos) {
                tableView.getItems().add(u);
            }
        }
    }
*/

}
