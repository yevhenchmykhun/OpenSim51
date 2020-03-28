package com.opensim51.simulator;

public abstract class ExecutionListenerAdapter implements ExecutionListener {

    private boolean running;

    public ExecutionListenerAdapter(boolean running) {
        this.running = running;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void cancel() {
        running = false;
    }

    @Override
    public void reset() {
        running = true;
    }

}
