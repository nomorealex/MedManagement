package pt.nomorealex.medmanagement.ui.factories;

import javafx.scene.Scene;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.ui.RefPane;
import pt.nomorealex.medmanagement.ui.RootPane;
import pt.nomorealex.medmanagement.ui.RootPane1;
import pt.nomorealex.medmanagement.ui.RootPane2;
import pt.nomorealex.medmanagement.ui.resources.CSSManager;

public final class SceneFactory {

    private SceneFactory(){}

    public static Scene configurateScene(RefPane pane, ServiceAPI serviceAPI){

        return switch (pane){
            case ROOTPANE -> {
                Scene scene = new Scene(new RootPane(serviceAPI),600,400);
                CSSManager.applyCSS(scene.getRoot(),"styles.css");
                yield scene;
            }
            case ROOTPANE1 -> {
                Scene scene = new Scene(new RootPane1(serviceAPI));
                CSSManager.applyCSS(scene.getRoot(), "styles.css");
                yield scene;
            }
            case ROOTPANE2 -> {
                Scene scene = new Scene(new RootPane2(serviceAPI));
                CSSManager.applyCSS(scene.getRoot(), "styles.css");
                yield scene;
            }
        };
    }
}
