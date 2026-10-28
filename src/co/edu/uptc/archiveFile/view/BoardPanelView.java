package co.edu.uptc.archiveFile.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BoardPanelView extends JPanel {

	private JTextArea textArea1;
	private JTextArea textArea2;
	private JScrollPane scroll1;
	private JScrollPane scroll2;

	public BoardPanelView() {

		super();

		textArea1 = new JTextArea();
		textArea2 = new JTextArea();

		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		loadingViewPanel();
	}

	private void loadingViewPanel() {

		scroll1 = new JScrollPane(textArea1);
		scroll2 = new JScrollPane(textArea2);
		scroll2.setPreferredSize(new Dimension(0, 200));

		add(scroll1, BorderLayout.CENTER);
		add(scroll2, BorderLayout.SOUTH);

		textArea1.setText("archive here...");

		scroll2.setVisible(false);
	}

	public void showEditor(String texto) {
		textArea2.setText(texto);
		scroll2.setVisible(true);
		revalidate();
		repaint();
	}

	public void hideEditor() {
		scroll2.setVisible(false);
		revalidate();
		repaint();
	}

	public JButton createButton(String texto, Color color) {

		JButton boton = new JButton(texto);
		boton.setBackground(color);

		return boton;
	}

	public JTextArea getTextArea1() {
		return textArea1;
	}

	public JTextArea getTextArea2() {
		return textArea2;
	}
}