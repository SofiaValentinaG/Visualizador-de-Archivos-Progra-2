package co.edu.uptc.archiveFile.view;

import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ViewManager implements IView {

	private ViewTable viewTable;
	private ViewImage viewImage;

	public ViewManager() {

		viewTable = new ViewTable();
		viewImage = new ViewImage();

		viewTable.setVisible(true);
		viewImage.setVisible(false);
	}
	
	public String askTitle() {
		return JOptionPane.showInputDialog(viewTable, "Digite el nombre del archivo properties");
	}

	public void showProperties(String[] claves, String[] valores) {
		viewTable.setProperties(claves, valores);
	}

	public void loadImage() {
		viewImage.loadImage();
	}

	public void showImageBuffer(BufferedImage img) {
		viewImage.showImage(img);
	}

	public JLabel cargarTitle(String texto) {
		return viewTable.cargarTitle(texto);

	}
    public void getTextToBinary(String texto) {
    	viewTable.showEditor(texto);
    	
    }
	public String getImageTextToEdit() {
		return viewImage.getTextToEdit();
	}

	public String createFileMessage() {
		return JOptionPane.showInputDialog(viewTable, "Digite el nombre que le quiere poner al archivo");
	}

	public void showEditorImage(String texto) { // este metodo es el que escribe la slineas en el text area
		viewImage.showEditor(texto);
	}

	@Override
	public void showEditor(String texto) {
		viewTable.showEditor(texto);
	}

	@Override
	public void hideEditor() {
		viewTable.hideEditor();
	}
	
	public Window windowListener(WindowEvent e) {
		return e.getWindow();
		
	}

	@Override
	public void addListeners(ActionListener listener) {

		for (int i = 0; i < viewTable.getItem().length; i++) {
			viewTable.getItem()[i].addActionListener(listener);
		}

		for (int i = 0; i < viewImage.getItem().length; i++) {
			viewImage.getItem()[i].addActionListener(listener);
		}

		for (int i = 0; i < viewTable.getItem().length; i++) {
			viewTable.getItem()[i].addActionListener(listener);
		}

		viewTable.getSaveFileButton().addActionListener(listener);
		viewTable.getSavePropertiesButton().addActionListener(listener);

		for (int i = 0; i < viewImage.getItem().length; i++) {
			viewImage.getItem()[i].addActionListener(listener);
		}
		
	
	}
	
	public ViewTable getWindow() {
		return viewTable.getWindow();
	}

	@Override
	public void showMessage(String mensaje) {
		JOptionPane.showMessageDialog(viewTable, mensaje);
	}

	@Override
	public void showArchive(String texto) {
		viewTable.setTexto(texto);

	}

	@Override
	public String getTextToEdit() {
		return viewTable.getBoardView().getTextArea2().getText();
	}

	@Override
	public void showTable() {
		viewImage.setVisible(false);
		viewTable.setVisible(true);
	}

	@Override
	public void showImage() {
		viewTable.setVisible(false);
		viewImage.setVisible(true);
	}

	public void addWindowListener(WindowListener listener) {
		viewTable.addWindowListener(listener);
		viewImage.addWindowListener(listener);
	}
}