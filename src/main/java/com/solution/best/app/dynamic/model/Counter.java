package com.solution.best.app.dynamic.model;

public class Counter {

    private int counter = 0;

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }
    
    public void increment(){
        counter++;
    }

}
