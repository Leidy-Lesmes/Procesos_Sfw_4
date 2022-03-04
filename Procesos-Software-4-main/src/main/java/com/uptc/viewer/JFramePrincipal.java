package com.uptc.viewer;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import com.uptc.viewer.reports.ReportDialog;

public class JFramePrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jPanelPrincipal;
	private HeaderPartition headerPartition;
	private JTableData centerTable;
	private MenuBarReports menuBarr;
	private ProcessToPartition processToPartition;

	public JFramePrincipal(ActionListener actionListener, String [] headers) {
		super(Constants.TITTLE_APP);
		//this.setSize(1620, 870);
		
		this.setSize(1200, 700);
		Image icon = new ImageIcon(Constants.LOGO_APP).getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		this.setIconImage(icon);
		this.setUndecorated(true);
		this.jPanelPrincipal = new JPanel();
		this.headerPartition = new HeaderPartition(actionListener);
	//	this.centerTable = new JTableData(Constants.PRICIPAL_HEADERS);
		this.menuBarr = new MenuBarReports(actionListener);
		this.processToPartition = new ProcessToPartition(this, actionListener);
		this.initComponents(actionListener);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowsListenerOption();
		this.setVisible(true);
	}

	private void initComponents(ActionListener actionListener) {
		jPanelPrincipal.setBackground(Color.WHITE);
		jPanelPrincipal.setLayout(new BoxLayout(jPanelPrincipal, BoxLayout.Y_AXIS));
		jPanelPrincipal.add(headerPartition);
		jPanelPrincipal.add(menuBarr);

		this.add(jPanelPrincipal);
	}

	public void cleanRowsTableProcess() {
		processToPartition.cleanRowsTable();
	}

	public void cleanRowsTablePartition() {
		headerPartition.cleanRowsTable();
	}

	private void addWindowsListenerOption() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
	}

	public void close() {
		if (JOptionPane.showConfirmDialog(this, "¿Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public ArrayList<Object[]> getProcessInformationProcess() {
		return processToPartition.getProcessInformation( );
	}

	public ArrayList<Object[]> getProcessInformationPartition() {
		return headerPartition.getPartitionInformation();
	}

	public void addElementToTablePrincipalTableProcess(ActionListener actionListener ) {
		processToPartition.addElementToTable(getProcessInformationProcess());
	}

	public void addElementToTablePrincipalTablePartition(ActionListener actionListener ) {
		headerPartition.addElementToTable(getProcessInformationPartition());
	}

	public void setInformationProcessTable(ActionListener actionListener) {
	if(checkNameProcess(getProcessInformationProcess())){
		processToPartition.incrementIdProcess();
		Object[] data ={processToPartition.getId(),processToPartition.getNameProcess(), processToPartition.getProcessTime(),
				processToPartition.getSizeProcess(),processToPartition.getIsBlocked() } ;
		processToPartition.addElementUniqueToTable(data, actionListener);
		} else {
			JOptionPane.showMessageDialog(processToPartition, "Nombre de proceso ya existente");
		}
	}

	


	private boolean checkNameProcess(ArrayList<Object[]> information) {
        for (int i = 0; i < information.size(); i++) {
			Object[] data= information.get(i);
			if(data[0].equals(processToPartition.getNameProcess())) {
				return false;
			}
		}
		return true;
	}

	public void reportTableVisibility(boolean visibility,ReportDialog table) {
		table.setVisible(visibility);
	}

	public void processToPartitionVisibility(boolean visibility,ProcessToPartition table) {
		processToPartition.setVisible(visibility);
	}

    public void deleteProcess(int id,ActionListener actionListener) {
		processToPartition.deleteProcess(id,actionListener);
    }

	public void deletePartition(int id,ActionListener actionListener) {
		headerPartition.deletePartition(id,actionListener);
    }

    public ArrayList<Object[]> getPartitionInformation() {
        return headerPartition.getPartitionInformation();
    }


	public void setInformationPartitionTable(ActionListener actionListener) {
		if(checkNamePartition(getPartitionInformation())){
			headerPartition.incrementId();
			Object[] data ={headerPartition.getId(),headerPartition.getNamePartition(), headerPartition.getSizePartition()};
			headerPartition.addElementUniqueToTable(data, actionListener);
		} else {
				JOptionPane.showMessageDialog(this, "Nombre de proceso ya existente");
		}
	}
	
	private boolean checkNamePartition(ArrayList<Object[]> information) {
        for (int i = 0; i < information.size(); i++) {
			Object[] data= information.get(i);
			if(data[0].equals(headerPartition.getNamePartition())) {
				return false;
			}
		}
		return true;
	}

	public Object[] getInformationCreatePartition() {
		return headerPartition.getInformationCreatePartition();
	}

}