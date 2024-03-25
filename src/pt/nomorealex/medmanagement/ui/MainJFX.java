package pt.nomorealex.medmanagement.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import pt.nomorealex.medmanagement.Main;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.ui.factories.SceneFactory;
import pt.nomorealex.medmanagement.ui.factories.StageFactory;
import pt.nomorealex.medmanagement.ui.structures.StageData;

public class MainJFX extends Application {
    ServiceAPI dataModel;

    @Override
    public void init() throws Exception {
        super.init();
    }

    public MainJFX() {
        dataModel = Main.model;
    }

     @Override
    public void start(Stage stage) throws Exception {
        stage = StageFactory.configuratePrimaryStage(stage,
                new StageData(
                        1200,
                        700,
                        null,
                        null,
                        "Pills Management",
                        "pills.png"));
        stage.setScene(SceneFactory.configurateScene(RefPane.ROOTPANE,dataModel));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
