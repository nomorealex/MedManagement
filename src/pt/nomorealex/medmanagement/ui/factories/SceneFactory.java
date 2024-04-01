package pt.nomorealex.medmanagement.ui.factories;

import javafx.scene.Scene;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.ui.MainCustomPane;
import pt.nomorealex.medmanagement.ui.RefPane;
import pt.nomorealex.medmanagement.ui.resources.CSSManager;
import pt.nomorealex.medmanagement.ui.sections.pills.secondstage.AddPillUI;
import pt.nomorealex.medmanagement.ui.sections.users.secondstage.AddUserUI;
import pt.nomorealex.medmanagement.ui.sections.users.secondstage.DetailsUserUI;
import pt.nomorealex.medmanagement.ui.sections.users.secondstage.EditUserUI;
import pt.nomorealex.medmanagement.ui.sections.users.secondstage.HistoricUserUI;
import pt.nomorealex.medmanagement.ui.sections.users.secondstage.PillsUserScheduleUI;
import pt.nomorealex.medmanagement.ui.sections.users.secondstage.thirdstage.AddUserAllergiesUI;
import pt.nomorealex.medmanagement.ui.sections.users.secondstage.thirdstage.AddUserIllnessUI;
import pt.nomorealex.medmanagement.ui.sections.users.secondstage.thirdstage.AddUserPillUI;

public final class SceneFactory {
    private SceneFactory(){}
    public static Scene configurateScene(RefPane pane, ServiceAPI serviceAPI){
        return switch (pane){
            case MAINCUSTOMPANE -> {
                Scene scene = new Scene(new MainCustomPane(serviceAPI),600,400);
                CSSManager.applyCSS(scene.getRoot(),"styles.css");
                yield scene;
            }
            case ADDUSERPANE -> {
                Scene scene = new Scene(new AddUserUI(serviceAPI));
                CSSManager.applyCSS(scene.getRoot(), "styles.css");
                yield scene;
            }
            case DETAILSUSERPANE -> {
                Scene scene = new Scene(new DetailsUserUI(serviceAPI));
                CSSManager.applyCSS(scene.getRoot(), "styles.css");
                yield scene;
            }
            case EDITUSERPANE -> {
                Scene scene = new Scene(new EditUserUI(serviceAPI));
                CSSManager.applyCSS(scene.getRoot(), "styles.css");
                yield scene;
            }
            case HISTORICUSERPANE -> {
                Scene scene = new Scene(new HistoricUserUI(serviceAPI));
                CSSManager.applyCSS(scene.getRoot(), "styles.css");
                yield scene;
            }
            case PILLSUSERSSCHEDULEPANE -> {
                Scene scene = new Scene(new PillsUserScheduleUI(serviceAPI));
                CSSManager.applyCSS(scene.getRoot(), "styles.css");
                yield scene;
            }
            case ADDUSERALLERGIESPANE -> {
                Scene scene = new Scene(new AddUserAllergiesUI(serviceAPI));
                CSSManager.applyCSS(scene.getRoot(), "styles.css");
                yield scene;
            }
            case ADDUSERILLNESSPANE -> {
                Scene scene = new Scene(new AddUserIllnessUI(serviceAPI));
                CSSManager.applyCSS(scene.getRoot(), "styles.css");
                yield scene;
            }
            case ADDUSERPILLPANE -> {
                Scene scene = new Scene(new AddUserPillUI(serviceAPI));
                CSSManager.applyCSS(scene.getRoot(), "styles.css");
                yield scene;
            }
            case ADDPILLPANE -> {
                Scene scene = new Scene(new AddPillUI(serviceAPI));
                CSSManager.applyCSS(scene.getRoot(), "styles.css");
                yield scene;
            }
            case DETAILSPILLPANE -> null;
            case EDITPILLPANE -> null;
            case ADDORDERPANE -> null;
        };
    }
}
