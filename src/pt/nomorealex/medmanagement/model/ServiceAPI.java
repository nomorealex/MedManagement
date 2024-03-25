package pt.nomorealex.medmanagement.model;

import pt.nomorealex.medmanagement.model.fsm.StatesAPI;

public class ServiceAPI {
    public ServiceAPI() {
    }
    public String get(){
        return "entry";
    }

    public StatesAPI getState(){
        return StatesAPI.INICIALSTATE;
    }
}
