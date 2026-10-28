package co.edu.uptc.archiveFile.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ViewImage extends JFrame {
	private JTextArea textArea;
	private BoardPanelImage boardImage;
	private JMenu jMenu;
	private JMenuItem[] item;
	private List<String> menuEnunce;
	private JMenuBar menuBar;

	public ViewImage() {

		Image image = new ImageIcon(
				getClass().getResource("/co/edu/uptc/archiveFile/model/resources/archive.png")).getImage();
		this.setIconImage(image);

		menuBar = new JMenuBar();
		textArea = new JTextArea();
		boardImage = new BoardPanelImage();
		menuEnunce = Arrays.asList("Open Image", "Loading Image","Edit Image","View Loading table");
		item = new JMenuItem[menuEnunce.size()];

		jMenu = new JMenu("menu");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadingView();
		setSize(900, 600);
		setLocationRelativeTo(null);
		this.setVisible(false);
	}

	public void loadImage() {
		boardImage.loadingPanel();
	}

	public void showImage(BufferedImage img) {
		boardImage.showImage(img);
	}

	// Lo que está escrito en el editor
	public String getTextToEdit() {
		return textArea.getText();
	}

	// Pone el texto en el editor
	public void showEditor(String texto) {
		textArea.setText(texto);
		textArea.setEditable(true);
		textArea.setEnabled(true);
		textArea.setCaretPosition(0);
	}

	public void loadingView() {
		for (int i = 0; i < menuEnunce.size(); i++) {
			item[i] = new JMenuItem(menuEnunce.get(i));
			jMenu.add(item[i]);
		}

		ImageIcon icon = new ImageIcon(getClass().getResource("/co/edu/uptc/archiveFile/model/resources/archive.png"));
		Image image = icon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		item[0].setIcon(new ImageIcon(image));

		menuBar.add(jMenu);
		setJMenuBar(menuBar);

		// Editor a la izquierda, imagen a la derecha
		JPanel centro = new JPanel(new GridLayout(1, 2));
		centro.add(new JScrollPane(textArea));
		centro.add(new JScrollPane(boardImage));
		add(centro, BorderLayout.CENTER);
	}

	public BoardPanelImage getBoardImage() {
		return boardImage;
	}

	public void setBoardImage(BoardPanelImage boardImage) {
		this.boardImage = boardImage;
	}

	public JMenu getjMenu() {
		return jMenu;
	}

	public void setjMenu(JMenu jMenu) {
		this.jMenu = jMenu;
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