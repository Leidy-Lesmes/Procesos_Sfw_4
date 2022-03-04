package com.uptc.controller;

import com.uptc.models.Partition;
import com.uptc.models.Process;
import com.uptc.reports.Report;

import static com.uptc.models.States.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ExecuteProcess {

    private final Queue<Process> processes;
    private final List<Process> allProcess;
    private final List<Partition> allPartition;
    private final Queue<Partition> partitions;
    private final List<Process> noExecuteProcess;


    private int timeProcess;   // cambia --
    private int timeCPU;       // lo que atiende la cpu
    private int totalTime;     // total de atencion de los procesos
    private Report report;

    public ExecuteProcess() {
        this.processes = new LinkedList<>();
        this.allProcess = new LinkedList<>();
        this.allPartition = new LinkedList<>();
        this.partitions = new LinkedList<>();
        this.noExecuteProcess=new LinkedList<>();
        timeProcess = 0;
        totalTime = 0;
    }


    public void addPartitionToList(Partition p) {
        this.allPartition.add(p);
    }

    public int allProcessSize(){
        return this.allProcess.size();
    }

    public void addProcessToQueue(Process process,String partition) {
        // this.processes.add(process);
         this.allProcess.add(process);
         addProcessPartition(process,partition);
         totalTime += process.getTime();
         process.states(0, 0, READY, INIT);
     }
 
     
     private void addProcessPartition(Process process, String partition) {
        for (Partition partitionIter : allPartition) {
            if(partitionIter.getName().equals(partition)){
                partitionIter.addProcesses(process);
                process.partitionAsing(partition);
            }
        }
     }
 
 
 
     public void init() {
         this.timeCPU = 5;
         cloneListPartition();
         while (!partitions.isEmpty()){
         Partition pAux=partitions.poll();
             while(!pAux.getListProcess().isEmpty()){
                     attendCPU(pAux); 
                 }
         }
     }
 
     private void cloneListPartition() {
         for (Partition partitionIter : allPartition) {
               partitions.add(partitionIter);
         }
     }
 
     private void attendCPU(Partition pAux) {
         Process p=pAux.getListProcess().poll();
         System.out.println("ATENDIENDO PROCESO" + p.getName());
         System.out.println(p.getName()+"tiempo"+p.getTime());
         if(p.getsize()<=pAux.getSize()){
             if (p.getTime() > timeCPU) { // 500 - 100
                 p.setTime(timeCPU);
                 p.states(timeProcess, timeProcess += timeCPU, EXECUTE, READY);
                 if (p.isBlocked()) {
                    p.states(timeProcess, timeProcess, LOCKED, EXECUTE);
                    p.states(timeProcess, timeProcess, READY, LOCKED);
                } else {
                    p.states(timeProcess, timeProcess, READY, EXECUTE);
                }
                 pAux.getListProcess().add(p);
             } else { // 50 100
                 int timePi = p.getTime();
                 p.setTime(timePi);
                 p.states(timeProcess, timeProcess += timePi, EXECUTE, READY);
                 if (p.isBlocked()) {
                    p.states(timeProcess, timeProcess, LOCKED, EXECUTE);
                    p.states(timeProcess, timeProcess, EXIT, EXECUTE);
                } else {
                    p.states(timeProcess, timeProcess, EXIT, EXECUTE);
                }
             }
         } else {
             noExecuteProcess.add(p);
         }
 
     }


    public void reports() {
        report = new Report(allProcess,allPartition, totalTime, timeCPU);
    }

    public ArrayList<Object[]> reportPartitionsProcess(){
        return report.getReportForPartition();
    }

    public ArrayList<Object[]> reportStatusChangeProcess() {
        return report.getReportForStatusChangeProcess();
    }

    public ArrayList<Object[]> reportByReadyStates(){
        return report.getReportByReadyStates();
    }

    public ArrayList<Object[]> reportByExitState(){
        return report.getReportByExitState();
    }

    public ArrayList<Object[]> reportByLockedStates(){
        return report.getReportByLockedStates();
    }

    public ArrayList<Object[]> reportForStatusChange(){
        return report.getReportForStatusChange();
    }

    public ArrayList<Object[]> reportByCpuExecuteOrder() {
        return report.reportByCpuExecuteOrder();
    }

    public String[] reportHeadersTable() {
        return report.headerTable();
    }
    
   
}
