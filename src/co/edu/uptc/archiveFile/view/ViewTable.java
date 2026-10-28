package co.edu.uptc.archiveFile.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ViewTable extends JFrame {

	private BoardPanelView boardView;
	private BoardPanelTable boardTable;
	private JLabel jLabel = new JLabel();
	private JMenu[] menus;
	private JMenuItem[] item;
	private List<String> menuEnunce;
	private JMenuBar menuBar;
	private JButton saveFileButton = new JButton("Save File");
	private JButton savePropertiesButton = new JButton("Save archive Properties");
	private JPanel buttonPanel;
	private JPanel southPanel;


	private String[] menuNames = { "Archivo", "Properties", "Binario", "Imagen", "Historial" };

	
	private String[][] menuOptions = {
			{ "Open File", "Edit File", "Loading File", "Create File" },
			{ "Create archive Properties" },
			{ "Edit for a binary Archive", "Loading Binary Archive" },
			{ "View Loading Image" },
			{ "File History" } };

	public ViewTable() {

		Image image = (Image) new ImageIcon(
				getClass().getResource("/co/edu/uptc/archiveFile/model/resources/helloKitty.png")).getImage();
		this.setIconImage(image);
		menuBar = new JMenuBar();
		boardView = new BoardPanelView();
		boardTable = new BoardPanelTable();

	
		menuEnunce = new ArrayList<>();
		for (int i = 0; i < menuOptions.length; i++) {
			for (int j = 0; j < menuOptions[i].length; j++) {
				menuEnunce.add(menuOptions[i][j]);
			}
		}
		item = new JMenuItem[menuEnunce.size()];
		menus = new JMenu[menuNames.length];

		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadingView();
		setSize(800, 600);
		setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void setProperties(String[] claves, String[] valores) {
		boardTable.showProperties(claves, valores);
	}

	public JLabel cargarTitle(String texto) {
		jLabel.setText(texto);
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		return jLabel;
	}

	public void loadingView() {

	
		int k = 0;
		for (int i = 0; i < menuNames.length; i++) {
			menus[i] = new JMenu(menuNames[i]);
			
			for (int j = 0; j < menuOptions[i].length; j++) {
				item[k] = new JMenuItem(menuOptions[i][j]);
				menus[i].add(item[k]);
				k++;
			}
			menuBar.add(menus[i]);
		}

		ImageIcon icon = new ImageIcon(
				getClass().getResource("/co/edu/uptc/archiveFile/model/resources/helloKitty.png"));
		Image image = icon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		item[0].setIcon(new ImageIcon(image));

		setJMenuBar(menuBar);

		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(saveFileButton);
		buttonPanel.add(savePropertiesButton);

		southPanel = new JPanel(new BorderLayout());
		southPanel.add(buttonPanel, BorderLayout.NORTH);
		southPanel.add(boardTable, BorderLayout.CENTER);

		add(jLabel, BorderLayout.NORTH);
		add(boardView, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}

	public ViewTable getWindow() {
		return this;
		
	}
	public void showEditor(String texto) {
		boardView.showEditor(texto);
	}

	public void hideEditor() {
		boardView.hideEditor();
	}

	public void setTexto(String texto) {
		boardView.getTextArea1().setText(texto);
	}

	public JButton getSaveFileButton() {
		return saveFileButton;
	}

	public JButton getSavePropertiesButton() {
		return savePropertiesButton;
	}

	public BoardPanelView getBoardView() {
		return boardView;
	}

	public void setBoardView(BoardPanelView boardView) {
		this.boardView = boardView;
	}

	public BoardPanelTable getBoardTable() {
		return boardTable;
	}

	public void setBoardTable(BoardPanelTable boardTable) {
		this.boardTable = boardTable;
	}

	public JMenu[] getMenus() {
		return menus;
	}

	public JMenuItem[] getItem() {
		return item;
	}

	public void setItem(JMenuItem[] item) {
		this.item = item;
	}

	public List<String> getMenuEnunce() {
		return menuEnunce;
	}

	public void setMenuEnunce(List<String> menuEnunce) {
		this.menuEnunce = menuEnunce;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}
}