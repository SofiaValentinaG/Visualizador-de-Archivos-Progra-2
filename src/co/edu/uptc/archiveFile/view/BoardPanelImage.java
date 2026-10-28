package co.edu.uptc.archiveFile.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class BoardPanelImage extends JPanel {

	private BufferedImage img;

	public BoardPanelImage() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(300, 300));
	}

	public void showImage(BufferedImage img) {
		this.img = img;
		repaint();
	}

	public void loadingPanel() {
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (img == null) {
			g.drawString("Todavia no hay imagen", 20, 20);
			return;
		}

		// La imagen de 10x10 se agranda a 300x300: cada pixel es un cuadro de 30x30
		g.drawImage(img, 0, 0, 300, 300, null);
	}
}