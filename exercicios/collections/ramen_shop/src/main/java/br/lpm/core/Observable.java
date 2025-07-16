package br.lpm.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<Observer> observers;
    private int state;

    public Observable() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer) {
        if (observer != null) {
            observers.remove(observer);
        }
    }   

    public String notifyAllObservers(){
        return observers.stream()
                .map(Observer::update)
                .reduce("", (a, b) -> a + "\n" + b);
    }
    
    public List<Observer> getObservers() {
        return observers;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if (state >=0 && state <=2) {
            this.state = state;
        }
    }   
}
