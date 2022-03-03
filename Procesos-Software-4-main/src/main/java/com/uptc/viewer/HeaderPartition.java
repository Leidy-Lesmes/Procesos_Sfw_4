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

public class HeaderPartition extends JPanel  {

	private static final long serialVersionUID = 1L;
	
	private JTextField namePartition, sizePartition;
	private JLabel tittle, lPartitionName, lSizePartition;
	private JPanel tittlePanel, partitionPanel, headerTittle; 
	private JButton savePartitionButton;
	private TablePartition tablePartition;
	private int numProcess;
	private int numPartition;
	private ArrayList<String> partitionArray;
	private ActionListener actionListener;

	public HeaderPartition(ActionListener actionListener) {
		super();
		this.tittlePanel = new JPanel();
		new JPanel();
		this.setActionListener(actionListener);
		this.headerTittle = new JPanel();
		new JPanel();
		this.partitionPanel = new JPanel();
		new JPanel();
		this.tablePartition = new TablePartition(Constants.headersPartition);
		this.partitionArray = new ArrayList<>();
		new JPanel();
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

	}

	public void addPartitionInComboBox(){
		for (int i = 0; i < partitionArray.size(); i++) {
			partitionArray.add(i, namePartition.getText());		
		}
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

	public void addElementUniqueToTable(Object[] data, ActionListener actionListener) {
    	tablePartition.addElementUniqueToTable(data,actionListener);
	}

	public ArrayList<Object[]> getPartitionInformation() {
		return tablePartition.getPartitionInformation();
	}
}