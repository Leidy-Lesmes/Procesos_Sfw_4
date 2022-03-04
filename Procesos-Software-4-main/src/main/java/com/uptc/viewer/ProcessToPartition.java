package com.uptc.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;

import com.uptc.controller.Commands;


import java.awt.Image;

import javax.swing.ImageIcon;
import com.uptc.viewer.reports.JTableDataReport;

public class ProcessToPartition extends JDialog {
    
    private static final long serialVersionUID = 1L;
	private JPanel  jPanelPrincipal,tittlePanel,northPanel, labelsData, dataProcess, southPanel;
	private JTableData centerTable;
	private JCheckBox isBlocked; 
	private JButton saveButton, closeButton, addButton;
	private JLabel tittle, lNameProcess, lProcessTime, lProcessSize;
	private JTextField nameProcess, processTime, processSize;
	private int numProcess;

	public ProcessToPartition(JFramePrincipal jFramePrincipal, ActionListener actionListener) {
		this.setModal(true);
		this.setLayout(new BorderLayout(5, 5));
		this.setSize(1200, 700);
		Image icon = new ImageIcon(Constants.LOGO_APP).getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		this.setIconImage(icon);
		this.jPanelPrincipal = new JPanel();
		this.northPanel = new JPanel();
		this.labelsData = new JPanel();
		this.dataProcess = new JPanel();
		this.tittlePanel = new JPanel();
		this.southPanel = new JPanel();
		this.centerTable = new JTableData(Constants.PRICIPAL_HEADERS);
		this.setUndecorated(true);
	//	this.setTitleFrame(title);
		this.setLocationRelativeTo(jFramePrincipal);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowsListenerOption();
		this.initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {	
		jPanelPrincipal.setLayout(new BorderLayout());

		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
		northPanel.setBackground(Constants.COLOR_TITTLE_PANEL);
		tittlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		tittlePanel.setBackground(Constants.COLOR_TITTLE_PANEL);
		tittle = new JLabel();
		tittlePanel.add(Utilities.text(tittle, Constants.FONT_TITTLE, "Procesos particion X", Color.BLACK));
		northPanel.add(tittlePanel);

		labelsData.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		labelsData.setBackground(Constants.COLOR_SET_DATA_PANEL);

		lNameProcess = new JLabel();
		labelsData.add(Utilities.text(lNameProcess, Constants.FONT_FIELDS_DATA, "Ingresa el nombre del proceso", Color.BLACK));

		lProcessTime = new JLabel();
		labelsData.add(Utilities.text(lProcessTime, Constants.FONT_FIELDS_DATA, "Ingresa el tiempo que el proceso requiere", Color.BLACK));

		lProcessSize = new JLabel();
		labelsData.add(Utilities.text(lProcessSize, Constants.FONT_FIELDS_DATA, "Ingresa el tamaño del proceso", Color.BLACK));

		northPanel.add(labelsData);

		dataProcess.setLayout(new FlowLayout(FlowLayout.LEFT, 10,5));
		dataProcess.setBackground(Constants.COLOR_SET_DATA_PANEL);

		nameProcess = new JTextField();
		dataProcess.add(Utilities.textField(nameProcess, Constants.FONT_FIELDS_DATA, "", Color.GRAY, 215, 20));
		
		processTime= new JTextField();
		dataProcess.add(Utilities.textField(processTime, Constants.FONT_FIELDS_DATA, "", Color.GRAY, 280, 20));

		processSize= new JTextField();
		dataProcess.add(Utilities.textField(processSize, Constants.FONT_FIELDS_DATA, "", Color.GRAY, 280, 20));

		isBlocked = new JCheckBox();
		isBlocked.setText("Bloqueo");
		dataProcess.add(Utilities.checkBox(isBlocked, Constants.FONT_FIELDS_DATA, Color.BLACK, Constants.COLOR_SET_DATA_PANEL, false));

		saveButton = new JButton();
		saveButton.addActionListener(actionListener);
		saveButton.setActionCommand(Commands.C_ADD_PROCESS.toString());
		dataProcess.add(Utilities.button(saveButton, new Dimension(100, 30), "Agregar"));
		
		northPanel.add(dataProcess);

		jPanelPrincipal.add(northPanel, BorderLayout.NORTH);
		jPanelPrincipal.add(centerTable, BorderLayout.CENTER);

		southPanel.setBackground(Constants.COLOR_TITTLE_PANEL);

		addButton = new JButton();
		addButton.addActionListener(actionListener);
		addButton.setActionCommand(Commands.C_SAVE_PROCESS_PARTITION.toString());


		closeButton = new JButton();
		closeButton.addActionListener(actionListener);
		closeButton.setActionCommand(Commands.C_CLOSE_DIALOG_ADD_PROCESS.toString());
		southPanel.add(Utilities.button(addButton, new Dimension(100, 30), "Agregar"));
		southPanel.add(Utilities.button(closeButton, new Dimension(100, 30), "Cerrar"));

		jPanelPrincipal.add(southPanel, BorderLayout.SOUTH);
		this.add(jPanelPrincipal);
	}

	public void close() {
		if (JOptionPane.showConfirmDialog(this, "¿Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	private void addWindowsListenerOption() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
	}
	
	public void setTitleFrame(String title){
		this.setTitle(title);
	}
/*	
	public void assignHeaders(ActionListener actionListener,String [] headersReport, String title) {
		this.headersReports=headersReport;
		this.centerTable = new JTableDataReport(headersReports);
		this.addComponentsCenter(actionListener,title);
	}*/
	
	public void setTitlePanel(String titleReport){
		JLabel title = new JLabel();
		JLabel help = new JLabel();
		northPanel.add(Utilities.text(title, Constants.FONT_MENUBAR, titleReport, Color.BLACK));
		if(titleReport=="TABLA DEL CAMBIO DE ESTADOS DE LOS PROCESOS"){
			northPanel.add(Utilities.text(help, Constants.FONT_MENUBAR, "DONDE L=LISTOS, E= EJECUCION, B= BLOQUEO, S= SALIDA", Color.BLACK));
		}
	}


	public void cleanRowsTable() {
		centerTable.cleanRowsTable();
	} 

	public void addElementToTable(ArrayList<Object[]> datasList){
		centerTable.addElementToTable(datasList);
	}

	public String getNameProcess(){
		return nameProcess.getText();
	}

	public String getProcessTime(){
	   return processTime.getText();
   }
   	public String getSizeProcess(){
	   return processSize.getText();
	}

	public boolean getIsBlocked(){
		return isBlocked.isSelected();
	 }

	 public int getId(){
        return numProcess;
	 }

	public int incrementIdProcess() {
        return numProcess++;
    }

	public ArrayList<Object[]> getProcessInformation() {
		return centerTable.getProcessInformation();
	}

	public void deleteProcess(int id, ActionListener actionListener) {
		centerTable.deleteProcess(id,actionListener);
	}

    public void addElementUniqueToTable(Object[] data, ActionListener actionListener) {
		centerTable.addElementUniqueToTable(data,actionListener);
    }

}