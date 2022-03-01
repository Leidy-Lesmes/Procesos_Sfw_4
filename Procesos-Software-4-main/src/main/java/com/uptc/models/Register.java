package com.uptc.models;

import java.util.Objects;

public class Register {

    protected  int timeInit;
    protected  int timeEnd;
    protected States status;
    protected int actualTime;
    protected Process process;
    protected States previousState;

    public Register(int timeI, int timeF, States status, int actualTime, Process p, States previous) {
        this.status = status;
        this.timeEnd = timeF;
        this.timeInit = timeI;
        this.actualTime = actualTime;
        this.process =p;
        this.previousState = previous;
    }

    public States getPreviousState() {
        return previousState;
    }

    public Process getProcess() {
        return process;
    }

    public States getStatus() {
        return status;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

    public int getTimeInit() {
        return timeInit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Register register = (Register) o;
        return timeInit == register.timeInit &&
                timeEnd == register.timeEnd &&
                actualTime == register.actualTime &&
                status == register.status &&
                Objects.equals(process, register.process);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeInit, timeEnd, status, actualTime, process);
    }

    @Override
    public String toString() {
        return "Register{" +
                "timeInit=" + timeInit +
                ", timeEnd=" + timeEnd +
                ", status=" + status +
                ", time=" + actualTime +
                '}';
    }
}
