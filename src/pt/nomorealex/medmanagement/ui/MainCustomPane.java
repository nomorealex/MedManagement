package pt.nomorealex.medmanagement.ui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.ui.factories.LabelFactory;
import pt.nomorealex.medmanagement.ui.resources.ImageConstants;
import pt.nomorealex.medmanagement.ui.resources.ImageManager;
import pt.nomorealex.medmanagement.ui.sections.CreditsUI;
import pt.nomorealex.medmanagement.ui.sections.InitialUI;
import pt.nomorealex.medmanagement.ui.sections.MainUI;
import pt.nomorealex.medmanagement.ui.sections.orders.OrdersUI;
import pt.nomorealex.medmanagement.ui.sections.pills.PillsUI;
import pt.nomorealex.medmanagement.ui.sections.users.UsersUI;


public class MainCustomPane extends BorderPane {
    private static final int NR_BUTTONS = 4;
    private static final int BUTTON_WIDTH= 140;
    private static final int BUTTON_HEIGHT = 40;
    private static final String buttonsIdentifiers[] = {"Main","Users","Pills","Orders"};
    Button btns[];
    ServiceAPI dataModel;
    StackPane stackPane;
    Image backgroundImage;
    Label sectionsLabel;
    ToolBar toolBar;
    MenuBar menuBar;
    public MainCustomPane(ServiceAPI serviceAPI)  {
        dataModel = serviceAPI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        stackPane = new StackPane(
                new InitialUI(dataModel),
                new MainUI(dataModel),
                new UsersUI(dataModel),
                new PillsUI(dataModel),
                new OrdersUI(dataModel)
        );

        try {
            backgroundImage = ImageManager.loadImage(ImageConstants.PILLS.getName());
            stackPane.setBackground(new Background(new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1, 1, true, true, true, false)
            )));
        }catch(NullPointerException ignored){}

        sectionsLabel = LabelFactory.createLabel(null,"Sections:", Color.DARKBLUE,Pos.CENTER,0);

        btns = new Button[NR_BUTTONS];
        for(int i = 0; i < NR_BUTTONS; ++i) {
            btns[i] = new Button(buttonsIdentifiers[i]);
            btns[i].setPrefSize(BUTTON_WIDTH,BUTTON_HEIGHT);
            btns[i].setAlignment(Pos.CENTER_LEFT);
        }

        toolBar = new ToolBar(sectionsLabel, new Separator(), new Separator(),
                btns[0], btns[1], btns[2], btns[3], new Separator());
        toolBar.setOrientation(Orientation.VERTICAL);

        MenuItem mnAbout = new MenuItem("About...");
        mnAbout.setOnAction(event -> showAbout());
        menuBar = new MenuBar(new Menu("More",null,mnAbout));
        this.setCenter(stackPane);
    }

    private void registerHandlers() {

        dataModel.addListener("all",event -> Platform.runLater(this::update));

        btns[0].setOnAction(event -> {
            dataModel.mainPage();
        });

        btns[1].setOnAction(event -> {
            dataModel.usersPage();
        });

        btns[2].setOnAction(event -> {
            dataModel.pillsPage();
        });

        btns[3].setOnAction(event -> {
            dataModel.ordersPage();
        });
    }

    private void update() {
        switch(dataModel.getState()){
            case INICIALSTATE:
                setTop(null);
                setLeft(null);
                setBottom(new CreditsUI());
                break;
            case MAINSTATE, USERSSTATE, PILLSSTATE, ORDERSSTATE:
                setTop(menuBar);
                setLeft(toolBar);
                break;
            default:
                break;
        }
    }

    private void showAbout() {
        final Stage stage = new Stage();
        try {
        stage.getIcons().add(ImageManager.loadImage(ImageConstants.PILLS.getName()));
        }catch(NullPointerException ignored){}
        String text = """
                         LEI - 2023
                     (c)Nuno Domingues
                """;
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setPrefWidth(200);
        textArea.setPrefHeight(150);

        textArea.setStyle("-fx-font-family: 'Courier New';");
        textArea.setText(text);
        Button btnClose = new Button("Close");
        btnClose.setOnAction(event -> stage.close());
        btnClose.setCursor(Cursor.DEFAULT);
        stage.setWidth(250);
        stage.setHeight(260);
        VBox vBox = new VBox(textArea,btnClose);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(vBox));
        stage.setTitle("About");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.getScene().getWindow());
        stage.showAndWait();
    }


}
