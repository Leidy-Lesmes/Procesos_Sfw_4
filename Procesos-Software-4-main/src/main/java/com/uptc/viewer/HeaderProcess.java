package com.uptc.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.uptc.controller.Commands;

public class HeaderProcess extends JPanel  {

	private static final long serialVersionUID = 1L;
	
	private JTextField processTime, nameProcess,  namePartition, sizePartition,processSize;
	private JLabel tittle, lProcessTime, lNameProcess, lProcessSize, lPartitionName, lSizePartition;
	private JPanel tittlePanel, dataProcess, labelsData, partitionPanel, headerTittle, southPanel; 
	private JButton saveButton, savePartitionButton;
	private TablePartition tablePartition;
	private int numProcess;
	private int numPartition;
	private ArrayList<String> partitionArray;
	private ActionListener actionListener;

	public HeaderProcess(ActionListener actionListener) {
		super();
		this.tittlePanel = new JPanel();
		new JPanel();
		this.setActionListener(actionListener);
		this.headerTittle = new JPanel();
		this.dataProcess = new JPanel();
		this.partitionPanel = new JPanel();
		this.labelsData = new JPanel();
		this.tablePartition = new TablePartition(Constants.headersPartition);
		this.partitionArray = new ArrayList<>();
		this.southPanel = new JPanel();
		this.initComponents(actionListener);
		numProcess=0;
		numPartition=0;
	}
	
	public ActionListener getActionListener() {
		return actionListener;
	}

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	private void initComponents(ActionListener actionListener) {	
		this.setLayout(new BorderLayout());

		headerTittle.setLayout(new BoxLayout(headerTittle, BoxLayout.Y_AXIS));
		
		tittlePanel.setBackground(Constants.COLOR_TITTLE_PANEL);
		tittlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		tittle = new JLabel();
		tittlePanel.add(Utilities.text(tittle, Constants.FONT_TITTLE, Constants.TITTLE_APP, Color.BLACK));
		headerTittle.add(tittlePanel);

		partitionPanel.setBackground(Constants.COLOR_SET_DATA_PANEL);
		partitionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		lPartitionName = new JLabel();
		partitionPanel.add(Utilities.text(lPartitionName, new Font("arial", Font.ITALIC, 15), "Ingresa el nombre de la particion", Color.BLACK));

		namePartition = new JTextField();
		partitionPanel.add(Utilities.textField(namePartition, new Font("arial", Font.ITALIC, 15), "", Color.GRAY, 150, 20));

		lSizePartition = new JLabel();
		partitionPanel.add(Utilities.text(lSizePartition, new Font("arial", Font.ITALIC, 15), "Ingresa el tamaño de la particion", Color.BLACK));
		sizePartition = new JTextField();
		partitionPanel.add(Utilities.textField(sizePartition, new Font("arial", Font.ITALIC, 15), "", Color.GRAY, 150, 20));


		savePartitionButton = new JButton();
		savePartitionButton.addActionListener(actionListener);
		savePartitionButton.setActionCommand(Commands.C_ADD_PARTITION.toString());
		partitionPanel.add(Utilities.button(savePartitionButton, new Dimension(200, 30), "Agregar Particion"));

		headerTittle.add(partitionPanel);
		this.add(headerTittle, BorderLayout.NORTH);

		this.add(tablePartition, BorderLayout.CENTER);

		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

		labelsData.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		labelsData.setBackground(Constants.COLOR_SET_DATA_PANEL);

		lNameProcess = new JLabel();
		labelsData.add(Utilities.text(lNameProcess, new Font("arial", Font.ITALIC, 15), "Ingresa el nombre del proceso", Color.BLACK));

		lProcessTime = new JLabel();
		labelsData.add(Utilities.text(lProcessTime, new Font("arial", Font.ITALIC, 15), "Ingresa el tiempo que el proceso requiere", Color.BLACK));

		lProcessSize = new JLabel();
		labelsData.add(Utilities.text(lProcessSize, new Font("arial", Font.ITALIC, 15), "Ingresa el tamaño del proceso", Color.BLACK));

		
		southPanel.add(labelsData);

		dataProcess.setLayout(new FlowLayout(FlowLayout.LEFT, 10,5));
		dataProcess.setBackground(Constants.COLOR_SET_DATA_PANEL);

		nameProcess = new JTextField();
		dataProcess.add(Utilities.textField(nameProcess, new Font("arial", Font.ITALIC, 15), "", Color.GRAY, 215, 20));
		
		processTime= new JTextField();
		dataProcess.add(Utilities.textField(processTime, new Font("arial", Font.ITALIC, 15), "", Color.GRAY, 280, 20));

		processSize= new JTextField();
		dataProcess.add(Utilities.textField(processSize, new Font("arial", Font.ITALIC, 15), "", Color.GRAY, 280, 20));

		saveButton = new JButton();
		saveButton.addActionListener(actionListener);
		saveButton.setActionCommand(Commands.C_ADD_PROCESS.toString());
		dataProcess.add(Utilities.button(saveButton, new Dimension(100, 30), "Agregar"));
		
		southPanel.add(dataProcess);

		this.add(southPanel, BorderLayout.SOUTH);
	
	}

	public void addPartitionInComboBox(){
		for (int i = 0; i < partitionArray.size(); i++) {
			partitionArray.add(i, namePartition.getText());		
		}
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

	public String getNamePartition(){
		return namePartition.getText();
	}

	public String getSizePartition(){
	   return sizePartition.getText();
	}

    public int getId() {
        return numProcess;
    }

	public int incrementId() {
        return numProcess++;
    }

	public int getIdPartition() {
        return numPartition;
    }

	public int incrementIdPartition() {
        return numPartition++;
    }


	/*public boolean changeStatusJtextfield(){
		boolean isEditable = false;
		if(partitionArray.size() > 0){
			isEditable = true;
			nameProcess.setEditable(true);
			processTime.setEditable(true);
		
		}
		else{
			isEditable = false;
			nameProcess.setEditable(false);
			processTime.setEditable(false);
			
		}
		return isEditable;
	}*/


	public void addElementUniqueToTable(Object[] data, ActionListener actionListener) {
    	tablePartition.addElementUniqueToTable(data,actionListener);
	}

	public ArrayList<Object[]> getPartitionInformation() {
		return tablePartition.getPartitionInformation();
	}
}