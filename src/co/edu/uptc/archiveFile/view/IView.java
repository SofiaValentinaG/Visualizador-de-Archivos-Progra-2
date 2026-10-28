package co.edu.uptc.archiveFile.view;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public interface IView {
	void addListeners(ActionListener listener);

	void showArchive(String texto);

	String getTextToEdit();

	void showTable();

	void showImage();

	void showEditor(String texto);

	void hideEditor();

	void showMessage(String mensaje);

}