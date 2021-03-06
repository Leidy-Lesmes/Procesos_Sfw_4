package com.uptc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.lang.model.element.Name;
import javax.swing.JOptionPane;

import com.uptc.models.Partition;
import com.uptc.models.Process;
import com.uptc.reports.Report;
import com.uptc.viewer.Constants;
import com.uptc.viewer.JFramePrincipal;
import com.uptc.viewer.ProcessToPartition;
import com.uptc.viewer.reports.ReportDialog;

public class ControllerApp implements ActionListener {
	ExecuteProcess executeProcess;
	JFramePrincipal jPrincipal;
	Report reportClass;
	String [] headersReports;
	ReportDialog reportTable;
	ProcessToPartition processToPartition;

	public ControllerApp() {
		executeProcess = new ExecuteProcess();
		jPrincipal = new JFramePrincipal(this,  headersReports);
		processToPartition= new ProcessToPartition(jPrincipal, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
		switch (Commands.valueOf(e.getActionCommand())) {
		case C_ADD_PROCESS:
			// agregar proceso a la tabla de procesos
			System.out.println("add proceso");
			addProcessTable(this);
			jPrincipal.cleanFieldsProcess();
			break;
		case C_ADD_PARTITION:
			// agregar proceso a la tabla de procesos
			System.out.println("agregar particion");
			addPartitionTable(this);
			addPartitionList(jPrincipal.getInformationCreatePartition());
            jPrincipal.cleanFieldPartition();
			break;

		case C_ADD_PROCESS_TO_PARTICION:
			System.out.println("agregar procesos particion");
			//jPrincipal.processToPartitionVisibility(true, processToPartition);
			break;

		case C_SAVE_PROCESS_PARTITION:
			System.out.println("agregar procesos particion");
            executeListProcess(jPrincipal.getProcessInformationProcess(),jPrincipal.getNamePartition());
			break;
			

		case C_EXECUTE_PROCESS:
			// Ejecutar lista de procesos
			System.out.println("ENTRO A EJECUTAR");
			execute();
			break;

		case C_CLOSE_APP:
			// Cerrar la app
			jPrincipal.close();
			break;

		case C_REPORT_FOR_STATUS_CHANGE_PROCESS:
			// reporte por cambio de estado de los proceso
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT2);
			reportTable.assignHeaders(this, getHeadersTable(),Constants.TOP_T_MENUITEM_REPORT2);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportStatusChangeProcess());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

		case C_REPORT_BY_EXIT_STATE:
			// reporte por orden en el estado en salida
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT3);
			reportTable.assignHeaders(this,Constants.headersEstados,Constants.TOP_T_MENUITEM_REPORT3);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportByExitState());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

		case C_REPORT_BY_READY_STATES:
			// reporte por orden en el estado en listo
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT4);
			reportTable.assignHeaders(this, Constants.headersEstados,Constants.TOP_T_MENUITEM_REPORT4);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportByReadyStates());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

			case C_REPORT_BY_LOCKED_STATES:
			// reporte por orden en el estado en bloqueo
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT5);
			reportTable.assignHeaders(this, Constants.headersR5,Constants.TOP_T_MENUITEM_REPORT5);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportByLockedStates());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

		case C_REPORT_BY_EXECUTE_STATES:
			// reporte por orden en el estado de en ejecuci??n
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT10);
			reportTable.assignHeaders(this,Constants.headersR6,Constants.TOP_T_MENUITEM_REPORT10);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportByCpuExecuteOrder());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

		case C_REPORT_BY_NOEXECUTE_STATES:
			// reporte por orden en el estado de en ejecuci??n
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT12);
			reportTable.assignHeaders(this,Constants.headersR8,Constants.TOP_T_MENUITEM_REPORT12);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportByNoExecuteProcess());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

		case C_REPORT_FOR_STATUS_CHANGE:
			// reporte por cambios de estado de cada proceso
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT11);
			reportTable.assignHeaders(this,Constants.headersR7,Constants.TOP_T_MENUITEM_REPORT11);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportForStatusChange());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

		case C_REPORT_BY_LIST_PARTITION:
			// reporte por orden en el estado en salida
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT5);
			reportTable.assignHeaders(this,Constants.headersPartitionList,Constants.TOP_T_MENUITEM_REPORT5);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportForTransitions());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

		case C_REPORT_BY_TRANSITION_WAKE:
			// reporte por orden en el estado en salida
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT8);
			reportTable.assignHeaders(this,Constants.headersR9,Constants.TOP_T_MENUITEM_REPORT8);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportForTransitionWake());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

		case C_REPORT_BY_TRANSITION_TIME_EXPIRED:
			// reporte por orden en el estado en salida
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT9);
			reportTable.assignHeaders(this,Constants.headersR9,Constants.TOP_T_MENUITEM_REPORT9);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportForTransitionTimeExpired());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

		case C_CLOSE_DIALOG_REPORT:
			reportTable.setVisible(false);
			break;

		case C_CLOSE_DIALOG_ADD_PROCESS:
			jPrincipal.processToPartitionVisibilityFalse(false);
			break;

		case C_DELETTE_PROCESS:
			this.deleteProcess(e.getActionCommand());
			break;
		
		case C_DELETTE_PARTITION:
			this.deletePartition(e.getActionCommand());
			break;
		default:

			break;
		}
	}catch (Exception ex) {
		System.out.println("Boton particiion numero"+e.getActionCommand());
		jPrincipal.processToPartitionVisibility(true,e.getActionCommand());
	}
	}

	private ArrayList<Object[]> reportByNoExecuteProcess() {
		return executeProcess.reportByNoExecuteProcess();
	}

	public ArrayList<Object[]> reportForTransitionWake() {
		ArrayList<Object[]> temp=executeProcess.reportTransitionWake();
		ArrayList<Object[]> exit=new ArrayList<>();
		for (Object[] tempIter : temp) {
			if(tempIter[0]!=null && !existProcess(exit,tempIter[0])){
              exit.add(tempIter);
			}
		}
		return exit;
	}

		private boolean existProcess(ArrayList<Object[]> exit,Object process) {
			for (Object[] tempIter : exit) {
				if(tempIter[0].equals(process)){
				  return true;
				}
			}
		return false;
	}

		public ArrayList<Object[]> reportForTransitionTimeExpired() {
		ArrayList<Object[]> temp=executeProcess.reportTransitionTimeExpired();
		ArrayList<Object[]> exit=new ArrayList<>();
			for (Object[] tempIter : temp) {
				if(tempIter[0]!=null){
				exit.add(tempIter);
				}
			}
		return exit;
	}
	
	private void addPartitionTable(ControllerApp controllerApp) {
		jPrincipal.setInformationPartitionTable(controllerApp);
	}

	public void addProcessTable(ActionListener actionListener) {
		jPrincipal.setInformationProcessTable(actionListener);
	}

	private void execute(){
		executeProcess.init();
		executeProcess.reports();
		JOptionPane.showMessageDialog(null, "Ejecucion realizada correctamente");
	}
	private void addPartitionList(Object[] listPartition) {
		//for (int i = 0; i < listPartition.size(); i++) {
		//	Object[] vector = (Object[]) listPartition;
			Partition temp=new Partition("" + listPartition[0], Integer.parseInt("" + listPartition[1]));
			executeProcess.addPartitionToList(temp);
		//}
	}

	private void executeListProcess(ArrayList<Object[]> listProcess,String partition) {
		System.out.println("procesos ates"+executeProcess.allProcessSize());
		int aux = executeProcess.allProcessSize();
		for (int i = aux; i < listProcess.size(); i++) {
			Object[] vector = listProcess.get(i);
			System.out.println("proceso"+vector[0]+"partition"+partition);
			Process temp=new Process("" + vector[0], Integer.parseInt("" + vector[1]),Integer.parseInt("" + vector[2]),Boolean.parseBoolean(""+vector[3]));
			executeProcess.addProcessToQueue(temp,partition);
		}
		JOptionPane.showMessageDialog(null, "Procesos agregados correctamente");
	}

	public String[] getHeadersTable() {
		return executeProcess.reportHeadersTable();
	}

	public ArrayList<Object[]> reportForTransitions() {
		return executeProcess.reportPartitionsProcess();
	}

	public ArrayList<Object[]> reportStatusChangeProcess() {
		return executeProcess.reportStatusChangeProcess();
	}

	public ArrayList<Object[]> reportByCpuExecuteOrder() {
		return executeProcess.reportByCpuExecuteOrder();
	}

	public ArrayList<Object[]> reportByReadyStates() {
		return executeProcess.reportByReadyStates();
	}

	public ArrayList<Object[]> reportByLockedStates() {
		return executeProcess.reportByLockedStates();
	}

	public ArrayList<Object[]> reportByExitState() {
		return executeProcess.reportByExitState();
	}

	public ArrayList<Object[]> reportForStatusChange() {
		return executeProcess.reportForStatusChange();
	}

	public void deleteProcess(String name) {
		name = JOptionPane.showInputDialog("Ingresa el nombre del proceso");
		JOptionPane.showMessageDialog(null, "Proceso " + name);

		if(JOptionPane.showConfirmDialog(jPrincipal, "??Seguro que desea borrar el proceso con nombre: " + name +"?",
				"Pregunta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			jPrincipal.deleteProcess(name, this);
		}
	}

	public void deletePartition(String name) {
		name = JOptionPane.showInputDialog("Ingresa el nombre de la particion");
		JOptionPane.showMessageDialog(null, "Particion " + name);

		if(JOptionPane.showConfirmDialog(jPrincipal, "??Seguro que desea borrar la particion con nombre: " + name +"?",
				"Pregunta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			jPrincipal.deletePartition(name , this);
		}
	}
}
