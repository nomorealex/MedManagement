package pt.nomorealex.medmanagement.model.fsm;

import pt.nomorealex.medmanagement.model.data.DataBucket;

public abstract class ServiceAPIStateAdapter implements IServiceAPIState{

    protected ServiceAPIContext context;
    protected DataBucket dataBucket;

    protected ServiceAPIStateAdapter(ServiceAPIContext context, DataBucket dataBucket) {
        this.context = context;
        this.dataBucket = dataBucket;
    }

    protected void changeState(ServiceAPIStates newState) {
        context.changeState(newState.createState(context,dataBucket));
    }

}
