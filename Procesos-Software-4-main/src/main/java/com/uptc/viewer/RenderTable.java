package com.uptc.viewer;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderTable extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(value instanceof JButton) {
				JButton button = (JButton)value;
				return button;
			}
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}	

}
