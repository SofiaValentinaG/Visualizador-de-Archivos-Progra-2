package co.edu.uptc.archiveFile.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class IoImageManager {

	private BufferedImage imagen;

	// Dibuja una carita en una imagen de 10x10 píxeles
	private void crearImagen() {
		imagen = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB); // crea una imagngen de 10x 10 pixeles y le dice
																		// que cada pizel guarda su
		// color convalores de la escala rgb rojo, azul y verde

		// Fondo amarillo
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				imagen.setRGB(x, y, Color.YELLOW.getRGB()); // pinta de amarillo
			}
		}

		// Ojos
		imagen.setRGB(3, 3, Color.BLACK.getRGB()); // ojo izquierdo
		imagen.setRGB(6, 3, Color.BLACK.getRGB()); // ojo derecho

		// Boca
		imagen.setRGB(2, 6, Color.BLACK.getRGB());
		imagen.setRGB(3, 7, Color.BLACK.getRGB());
		imagen.setRGB(4, 7, Color.BLACK.getRGB());
		imagen.setRGB(5, 7, Color.BLACK.getRGB());
		imagen.setRGB(6, 7, Color.BLACK.getRGB());
		imagen.setRGB(7, 6, Color.BLACK.getRGB());
	}

	// Devuelve el texto con todos los píxeles
	public StringBuilder loadImage(String location, String name) {
		StringBuilder texto = new StringBuilder();

		if (imagen == null) {
			crearImagen();
		}

		for (int x = 0; x < imagen.getWidth(); x++) {
			for (int y = 0; y < imagen.getHeight(); y++) {
				Color color = new Color(imagen.getRGB(x, y));// se pide el color del pixel en la posicion

				texto.append("Pixel ").append(x).append("-").append(y);
				texto.append(" R:").append(color.getRed()); // obtiene la escala de valores
				texto.append(" G:").append(color.getGreen());
				texto.append(" B:").append(color.getBlue());
				texto.append("\n");
			}
		}

		return texto;
	}

	public int applyChanges(String texto) {
		if (imagen == null || texto == null) {
			return 0;
		}

		int cambiados = 0;
		String[] lineas = texto.split("\n"); // parte la lista de texto por saltos de linea

		for (int i = 0; i < lineas.length; i++) {
			String linea = lineas[i].trim(); // quita espacios sobrantes

			if (linea.startsWith("Pixel")) { // si la linea empieza por pixel
				if (cambiarPixel(linea)) {// entonces  aplica el cambio si devuelve true
					cambiados++; // y suma al contador
				}
			}
		}

		return cambiados;
	}

	private boolean cambiarPixel(String linea) {

//		"Pixel 3-5 R:255 G:0 B:0"
		try {
			String[] partes = linea.split(" "); // split(" ") corta el texto cada vez que encuentra un espacio y guarda
												// los pedazos en un arreglo

			String[] posicion = partes[1].split("-"); // parte las posiciones
			int x = Integer.parseInt(posicion[0]);
			int y = Integer.parseInt(posicion[1]);

			int rojo = ajustar(Integer.parseInt(partes[2].substring(2))); // subssting quita los 2 primeros acaracteres y solo deja un numeor y lo pasa a int
			int verde = ajustar(Integer.parseInt(partes[3].substring(2)));
			int azul = ajustar(Integer.parseInt(partes[4].substring(2)));

			if (x >= imagen.getWidth() || y >= imagen.getHeight()) { // verifica si el pixel esta adentro
				return false;
			}

			imagen.setRGB(x, y, new Color(rojo, verde, azul).getRGB()); // aquii es donde cambia los colores
			return true;

		} catch (Exception e) {
			return false;
		}
	}
	
	// en este metodo se limitan los valores rgb

	private int ajustar(int valor) {
		if (valor < 0) {
			return 0;
		}
		if (valor > 255) {
			return 255;
		}
		return valor;
	}

	public BufferedImage getImage() {
		return imagen;
	}
}