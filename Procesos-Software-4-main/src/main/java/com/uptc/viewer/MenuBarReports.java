package com.uptc.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.uptc.controller.Commands;

public class MenuBarReports extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private JMenu report;
	private JMenuItem report3, report4, report5,report6,report9,report8, report10, report11,report12;
	private JButton executeButton, deletePartition, closeApppButton;

	public MenuBarReports(ActionListener actionListener) {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBackground(Color.WHITE);
		this.initComponents(actionListener);
		this.setVisible(true);
	}
	

	private void initComponents(ActionListener actionListener) {
		
		executeButton = new JButton();
		executeButton.addActionListener(actionListener);
		executeButton.setActionCommand(Commands.C_EXECUTE_PROCESS.toString());
		this.add(Utilities.button(executeButton, new Dimension(100, 30), "Ejecutar"));
		
		report = new JMenu(Constants.TOP_PANEL_MENU_PRINCIPAL_REPORT);
		report.setFont(Constants.FONT_MENUBAR);
		report.setForeground(Color.BLACK);

		report3 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT3);
		report3.setFont(Constants.FONT_MENUBAR);
		report3.addActionListener(actionListener);
		report3.setActionCommand(Commands.C_REPORT_BY_EXIT_STATE.toString());
		report.add(report3);
		report.addSeparator();

		report4 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT4);
		report4.setFont(Constants.FONT_MENUBAR);
		report4.addActionListener(actionListener);
		report4.setActionCommand(Commands.C_REPORT_BY_READY_STATES.toString());
		report.add(report4);
		report.addSeparator();

		report5 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT5);
		report5.setFont(Constants.FONT_MENUBAR);
		report5.addActionListener(actionListener);
		report5.setActionCommand(Commands.C_REPORT_BY_LOCKED_STATES.toString());
		report.add(report5);
		report.addSeparator();

		report6 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT6);
		report6.setFont(Constants.FONT_MENUBAR);
		report6.addActionListener(actionListener);
		report6.setActionCommand(Commands.C_REPORT_BY_LIST_PARTITION.toString());
		report.add(report6);
		report.addSeparator();

		report8 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT8);
		report8.setFont(Constants.FONT_MENUBAR);
		report8.addActionListener(actionListener);
		report8.setActionCommand(Commands.C_REPORT_BY_TRANSITION_WAKE.toString());
		report.add(report8);
		report.addSeparator();

		report9 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT9);
		report9.setFont(Constants.FONT_MENUBAR);
		report9.addActionListener(actionListener);
		report9.setActionCommand(Commands.C_REPORT_BY_TRANSITION_TIME_EXPIRED.toString());
		report.add(report9);
		report.addSeparator();

		report10 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT10);
		report10.setFont(Constants.FONT_MENUBAR);
		report10.addActionListener(actionListener);
		report10.setActionCommand(Commands.C_REPORT_BY_EXECUTE_STATES.toString());
		report.add(report10);
		report.addSeparator();

		report12 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT12);
		report12.setFont(Constants.FONT_MENUBAR);
		report12.addActionListener(actionListener);
		report12.setActionCommand(Commands.C_REPORT_BY_NOEXECUTE_STATES.toString());
		report.add(report12);
		report.addSeparator();

		report11 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT11);
		report11.setFont(Constants.FONT_MENUBAR);
		report11.addActionListener(actionListener);
		report11.setActionCommand(Commands.C_REPORT_FOR_STATUS_CHANGE.toString());
		report.add(report11);
		report.addSeparator();
		
		this.add(report);
		
		deletePartition = new JButton();
		deletePartition.addActionListener(actionListener);
		deletePartition.setActionCommand(Commands.C_DELETTE_PARTITION.toString());
		this.add(Utilities.button(deletePartition, new Dimension(100, 30), "Eliminar"));

		closeApppButton = new JButton();
		closeApppButton.addActionListener(actionListener);
		closeApppButton.setActionCommand(Commands.C_CLOSE_APP.toString());
		this.add(Utilities.button(closeApppButton, new Dimension(100, 30), "Cerrar"));
	}
}