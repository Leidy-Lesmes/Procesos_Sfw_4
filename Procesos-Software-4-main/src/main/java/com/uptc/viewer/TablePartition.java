package com.uptc.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.uptc.controller.Commands;


public class TablePartition extends JPanel {

    private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmElements;
	private String[] headers;
	private JTable jtElements;
	private JScrollPane jsTable;
	private ArrayList<Object[]> listPartition;
   

	public TablePartition (String[] headers) {
		this.headers = headers;
		initComponents();
	}

	private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.decode("#30373D"));
		dtmElements = new DefaultTableModel();
		dtmElements.setColumnIdentifiers(headers);
		jtElements = new JTable();
		defaulModel();
		jtElements.getTableHeader().setResizingAllowed(false);
		jtElements.getTableHeader().setReorderingAllowed(false);
		jtElements.getTableHeader().setBackground(Constants.DATA_PANEL_HEADERS_TABLE_COLOR);
		jtElements.getTableHeader().setPreferredSize(new Dimension(0, 60));
		jtElements.getTableHeader().setForeground(Color.BLACK);
		jtElements.getTableHeader().setFont(Constants.DATA_PANEL_HEADERS_TABLE_FONT);
		jtElements.setBackground(Color.WHITE);
		jtElements.setFont(Constants.DATA_PANEL_HEADERS_TABLE_FONT);
		jtElements.setFillsViewportHeight(true);
		jtElements.setRowHeight(50);
		jtElements.setBorder(null);
		jsTable = new JScrollPane(jtElements, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsTable.setForeground(Color.RED);
		jsTable.setBorder(null);
		jsTable.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(jsTable, BorderLayout.PAGE_END);
		this.setBorder(null);
       

	}

	public void defaulModel(){
		dtmElements.setColumnIdentifiers(headers);
		jtElements.setModel(dtmElements);
		jtElements.getColumn(Constants.headersPartition[2]).setCellEditor(new TableCellEditor() {
			
			@Override
			public boolean stopCellEditing() {
				return true;
			}
			
			@Override
			public boolean shouldSelectCell(EventObject arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void removeCellEditorListener(CellEditorListener arg0) {
			}
			
			@Override
			public boolean isCellEditable(EventObject arg0) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public Object getCellEditorValue() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void cancelCellEditing() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addCellEditorListener(CellEditorListener arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
				// TODO Auto-generated method stub
				return (JButton)value;
			}

		});
	}

	public DefaultTableModel getDtmElements() {
		return dtmElements;
	}

	public void setDtmElements(DefaultTableModel dtmElements) {
		this.dtmElements = dtmElements;
	}

	public void addElementToTable(ArrayList<Object[]> datasList) {
		for (Object[] datasObject : datasList) {
			dtmElements.addRow(datasObject);
		}
	}

	public void addElementUniqueToTable(Object[] datasList, ActionListener actionListener) {
		Object[] row = new Object[] { datasList[1], datasList[2],
		createButton(actionListener,String.valueOf(datasList[1]))};
		dtmElements.addRow(row);
	}

	public ArrayList<Object[]> getPartitionInformation() {
		ArrayList<Object[]> infoPartition = new ArrayList<>();
		for (int i = 0; i < dtmElements.getRowCount(); i++) {
			Object[] row = new Object[2];
			row[0] = dtmElements.getValueAt(i, 0);
			row[1] = dtmElements.getValueAt(i, 1);
			infoPartition.add(row);	
		}
		return infoPartition;
	}

	
	public void cleanRowsTable() {
		//dtmElements.setNumRows(0);
		this.remove(jsTable);
	}

	public JButton createButton(ActionListener actionListener,String id) {
		JButton createButton = new JButton("Crear Procesos");
		createButton.setName(id);
		createButton.addActionListener(actionListener);
		Commands.C_ADD_PROCESS_TO_PARTICION.setName(id);
		System.out.println("nombre asignado"+Commands.C_ADD_PROCESS_TO_PARTICION.getName());
		createButton.setActionCommand(Commands.C_ADD_PROCESS_TO_PARTICION.toString());
		createButton.setBackground(Color.decode("#DF3A01"));
		createButton.setForeground(Color.WHITE);
		createButton.setHorizontalAlignment(JLabel.CENTER);
		createButton.setVisible(true);
		return createButton;
	}


	public void deletePartition(int id, ActionListener actionListener) {
		listPartition= new ArrayList<>();
		int rowInitial=dtmElements.getRowCount()+1;
		for (int i = 0; i < rowInitial; i++) {
			int idProcess=Integer.parseInt(""+dtmElements.getValueAt(i, 0));
			if(idProcess==id){
				dtmElements.removeRow(i);
		      }
			else {
				Object[] row = new Object[3];
				row[0] = dtmElements.getValueAt(i, 0);
				row[1] = dtmElements.getValueAt(i, 1);
				row[2] = dtmElements.getValueAt(i, 2);
				listPartition.add(row);
			  }  
	        }
		cleanRowsTable();
		
		loadProcess(actionListener);
	}

	private void loadProcess(ActionListener actionListener) {
		initComponents();
		defaulModel();
		for (int i = 0; i < listPartition.size(); i++) {
			addElementUniqueToTable(listPartition.get(i), actionListener);
		}
	}
}
