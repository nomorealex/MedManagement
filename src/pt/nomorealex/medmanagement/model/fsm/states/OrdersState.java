package pt.nomorealex.medmanagement.model.fsm.states;

import pt.nomorealex.medmanagement.model.data.DataBucket;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIContext;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStateAdapter;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;

public class OrdersState extends ServiceAPIStateAdapter {


    public OrdersState(ServiceAPIContext context, DataBucket dataBucket) {
        super(context,dataBucket);
    }

    @Override
    public ServiceAPIStates getState() {
        return ServiceAPIStates.ORDERSSTATE;
    }

    @Override
    public void mainPage(){
        changeState(ServiceAPIStates.MAINSTATE);
    }

    @Override
    public void usersPage() {
        changeState(ServiceAPIStates.USERSSTATE);
    }

    @Override
    public void pillsPage() {
        changeState(ServiceAPIStates.PILLSSTATE);
    }

    @Override
    public void ordersPage() {
        changeState(ServiceAPIStates.ORDERSSTATE);
    }
}
