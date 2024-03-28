package pt.nomorealex.medmanagement.model;

import pt.nomorealex.medmanagement.model.fsm.ServiceAPIContext;
import pt.nomorealex.medmanagement.model.fsm.ServiceAPIStates;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class ServiceAPI {

    private ServiceAPIContext context;

    private PropertyChangeSupport propertyChangeSupport;

    public ServiceAPI() {
        this.context = new ServiceAPIContext();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addListener(String property, PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(property, listener);
    }

    public void removeListener(String property, PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener(property, listener);
    }

    public ServiceAPIStates getState(){
        return context.getState();
    }

    public void next(){
        System.out.println("Context:"+context.getState());
        context.next();
        System.out.println("Context:"+context.getState());
        propertyChangeSupport.firePropertyChange("all",null,null);
    }

    public void mainPage(){
        context.mainPage();
        propertyChangeSupport.firePropertyChange("all",null,null);
    }

    public void usersPage(){
        context.usersPage();
        propertyChangeSupport.firePropertyChange("all",null,null);
    }

    public void pillsPage(){
        context.pillsPage();
        propertyChangeSupport.firePropertyChange("all",null,null);
    }

    public void ordersPage(){
        context.ordersPage();
        propertyChangeSupport.firePropertyChange("all",null,null);
    }
}
