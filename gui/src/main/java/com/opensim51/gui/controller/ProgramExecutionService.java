package com.opensim51.gui.controller;

import com.opensim51.simulator.ExecutionListener;
import com.opensim51.simulator.Simulator;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

class ProgramExecutionService extends Service<Void> {

    private Simulator simulator = Simulator.getInstance();

    private ExecutionListener executionListener;

    void setExecutionListener(ExecutionListener executionListener) {
        this.executionListener = executionListener;
    }

    protected Task<Void> createTask() {
        return new Task<Void>() {
            protected Void call() {
                simulator.run(executionListener);
                return null;
            }
        };
    }

}
