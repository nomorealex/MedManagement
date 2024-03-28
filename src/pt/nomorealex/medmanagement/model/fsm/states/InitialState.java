package pt.nomorealex.medmanagement.model.fsm.states;

import pt.nomorealex.medmanagement.model.data.DataBucket;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIContext;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStateAdapter;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;

public class InitialState extends ServiceAPIStateAdapter {

    public InitialState(ServiceAPIContext context, DataBucket dataBucket) {
        super(context,dataBucket);
    }

    @Override
    public ServiceAPIStates getState() {
        return ServiceAPIStates.INICIALSTATE;
    }

    @Override
    public void next() {
        changeState(ServiceAPIStates.MAINSTATE);
    }
}
