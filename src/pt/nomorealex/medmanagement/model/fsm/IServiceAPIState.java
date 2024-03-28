package pt.nomorealex.medmanagement.model.fsm;

public interface IServiceAPIState {
    ServiceAPIStates getState();
    default void next() {}
    default void mainPage() {}
    default void usersPage() {}
    default void pillsPage() {}
    default void ordersPage() {}

}
