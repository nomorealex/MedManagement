package pt.nomorealex.medmanagement.model.fsm;

import pt.nomorealex.medmanagement.model.data.DataBucket;
import pt.nomorealex.medmanagement.model.fsm.states.InitialState;
import pt.nomorealex.medmanagement.model.fsm.states.MainState;
import pt.nomorealex.medmanagement.model.fsm.states.OrdersState;
import pt.nomorealex.medmanagement.model.fsm.states.PillsState;
import pt.nomorealex.medmanagement.model.fsm.states.UsersState;

public enum ServiceAPIStates {
    INICIALSTATE, MAINSTATE, USERSSTATE, PILLSSTATE, ORDERSSTATE;

    public IServiceAPIState createState(ServiceAPIContext context, DataBucket dataBucket){
        return switch (this){
            case INICIALSTATE -> new InitialState(context, dataBucket);
            case MAINSTATE -> new MainState(context, dataBucket);
            case USERSSTATE -> new UsersState(context, dataBucket);
            case PILLSSTATE -> new PillsState(context, dataBucket);
            case ORDERSSTATE -> new OrdersState(context, dataBucket);
        };

    }
}
