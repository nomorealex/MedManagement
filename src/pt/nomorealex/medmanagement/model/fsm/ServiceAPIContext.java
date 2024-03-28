package pt.nomorealex.medmanagement.model.fsm;

import pt.nomorealex.medmanagement.model.data.DataBucket;

public class ServiceAPIContext {

    DataBucket dataBucket;

    IServiceAPIState state;

    public ServiceAPIContext() {
        this.dataBucket = new DataBucket();
        state = ServiceAPIStates.INICIALSTATE.createState(this, dataBucket);
    }


    void changeState(IServiceAPIState state){
        this.state = state;
    }

    public ServiceAPIStates getState(){
        return state.getState();
    }

    public void next() {
        state.next();
    }

    public void mainPage(){
        state.mainPage();
    }

    public void usersPage(){
        state.usersPage();
    }

    public void pillsPage(){
        state.pillsPage();
    }

    public void ordersPage(){
        state.ordersPage();
    }
}
