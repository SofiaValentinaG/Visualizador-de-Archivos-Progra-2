package co.edu.uptc.archiveFile.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BoardPanelTable extends JPanel {

	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;

	public BoardPanelTable() {
		setLayout(new BorderLayout());

		model = new DefaultTableModel();
		table = new JTable(model);
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(0, 80));
		add(scroll, BorderLayout.CENTER);
	}

	
	public void showProperties(String[] claves, String[] valores) {
		Object[][] fila = { valores };
		model.setDataVector(fila, claves);
	}

	public JTable getTable() {
		return table;
	}
}