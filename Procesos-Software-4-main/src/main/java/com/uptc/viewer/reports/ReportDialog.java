package com.uptc.viewer.reports;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uptc.controller.Commands;
import com.uptc.viewer.Constants;
import com.uptc.viewer.JFramePrincipal;
import com.uptc.viewer.Utilities;


import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ReportDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jPanelPrincipal, northPanel, southPanel;
	private JTableDataReport centerTable;
	private String [] headersReports;
	private JButton closeButton;

	public ReportDialog(JFramePrincipal jFramePrincipal,String title) {
		jPanelPrincipal= new JPanel();
		setModal(true);
		this.setLayout(new BorderLayout(5, 5));
		this.setBackground(Color.WHITE);
		this.setSize(1200, 700);
		Image icon = new ImageIcon(Constants.LOGO_APP).getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		this.setIconImage(icon);
		this.setUndecorated(true);
		this.setTitleFrame(title);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(jFramePrincipal);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowsListenerOption();
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
	
	public void assignHeaders(ActionListener actionListener,String [] headersReport, String title) {
		this.headersReports=headersReport;
		this.centerTable = new JTableDataReport(headersReports);
		this.addComponentsCenter(actionListener,title);
	}
	
	private void addComponentsCenter(ActionListener actionListener,String title) {
		jPanelPrincipal.setBackground(Color.WHITE);
		jPanelPrincipal.setLayout(new BorderLayout());
		jPanelPrincipal.add(northPanel(title), BorderLayout.NORTH);
		jPanelPrincipal.add(centerTable, BorderLayout.CENTER);
		jPanelPrincipal.add(southPanel(actionListener), BorderLayout.SOUTH);
		this.add(jPanelPrincipal);
	}

	private Component northPanel(String title) {
		northPanel = new JPanel();
		northPanel.setBackground(Constants.COLOR_MENUBAR);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));	
		setTitlePanel(title);
		northPanel.setVisible(true);
		return northPanel;
	}

	private Component southPanel(ActionListener actionListener){
		southPanel = new JPanel();
		southPanel.setBackground(Constants.COLOR_MENUBAR);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		closeButton = new JButton();
		closeButton.addActionListener(actionListener);
		closeButton.setActionCommand(Commands.C_CLOSE_DIALOG.toString());
		southPanel.add(Utilities.button(closeButton, new Dimension(100, 30), "Cerrar"));
		southPanel.setVisible(true);
		return southPanel;
	}

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
}
