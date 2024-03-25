package pt.nomorealex.medmanagement;

import javafx.application.Application;
import pt.nomorealex.medmanagement.model.ServiceAPI;
import pt.nomorealex.medmanagement.ui.MainJFX;

public class Main{
    public static ServiceAPI model = null;
    public static void main(String[] args) {
        model = new ServiceAPI();
        Application.launch(MainJFX.class, args);
    }
}
