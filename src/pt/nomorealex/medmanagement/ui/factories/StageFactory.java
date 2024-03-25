package pt.nomorealex.medmanagement.ui.factories;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.nomorealex.medmanagement.ui.resources.ImageManager;
import pt.nomorealex.medmanagement.ui.structures.StageData;

public final class StageFactory {
    private StageFactory(){}

    public static Stage configuratePrimaryStage(Stage stage, StageData stageData){
        stage.setMinWidth(stageData.minWidth());
        stage.setMinHeight(stageData.minHeight());
        stage.setTitle(stageData.stageTitle());
        try {
            Image image = ImageManager.loadImage(stageData.imageFilename());
            stage.getIcons().add(image);
        }catch (NullPointerException e){}

        setOnCloseRequest(stage,stageData.imageFilename());

        return stage;
    }

    public static Stage configurateStage(StageData stageData){
        Stage stage = new Stage();
        validateStageData(stageData);
        stage.setMinWidth(stageData.minWidth());
        stage.setMinHeight(stageData.minHeight());
        stage.setTitle(stageData.stageTitle());
        stage.initOwner(stageData.windowOwner());
        stage.initModality(stageData.modality());
        try {
            Image image = ImageManager.loadImage(stageData.imageFilename());
            stage.getIcons().add(image);
        }catch (NullPointerException e){}

        return stage;
    }

    private static void setOnCloseRequest(Stage stage, String filename){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                windowEvent.consume();
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Confirm exit!");
                a.setHeaderText(null);
                a.setContentText("Do you really want to exit?");
                final Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
                try {
                    Image image = ImageManager.loadImage(filename);
                    stage.getIcons().add(image);
                }catch (NullPointerException e){}
                a.showAndWait();
                if(a.getResult() == ButtonType.OK){
                    stage.close();
                    Platform.exit();
                    System.exit(0);
                }
            }
        });

    }

    private static void validateStageData(StageData stageData){
        if(stageData.minHeight() < 0.0 || stageData.minWidth() < 0.0){
            throw new IllegalArgumentException("Exception: minHeight and minWidth should be greater than 0.");
        }
        if(stageData.stageTitle() == null){
            throw new NullPointerException("Exception: a title should be provided");
        }
        if(stageData.modality() == null){
            throw new NullPointerException("Exception: a modality(NONE,WINDOW_MODAL,APPLICATION_MODAL) should be provided");
        }
    }
}