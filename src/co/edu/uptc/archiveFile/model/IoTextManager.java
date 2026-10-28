package co.edu.uptc.archiveFile.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

public class IoTextManager {

	public String fileChoooser() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int resultado = fileChooser.showOpenDialog(null);

		if (resultado == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath() + File.separator;
		}

		return null;
	}

	public boolean createArchive(String nombre, String ubicacion) {

		File archivo = new File(ubicacion + nombre);

		try {

			archivo.getParentFile().mkdirs();

			if (archivo.createNewFile()) {
				System.out.println("Archivo creado");
				return true;
			} else {
				System.out.println("El archivo ya existe");
				return false;
			}

		} catch (IOException e) {

			Logger.getLogger(IoTextManager.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	public void writingArchive(String nombre, String ubicacion, String contenido) {

		FileWriter archivo = null;
		PrintWriter pw = null;

		try {

			archivo = new FileWriter(ubicacion + nombre);

			pw = new PrintWriter(archivo);

			pw.print(contenido);

		} catch (IOException e) {

			Logger.getLogger(IoTextManager.class.getName()).log(Level.SEVERE, null, e);

		} finally {

			try {

				if (pw != null) {
					pw.close();
				}

				if (archivo != null) {
					archivo.close();
				}

			} catch (IOException e) {

				Logger.getLogger(IoTextManager.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	public String ReadingFile(String nombre, String ubicacion) {

		String contenido = "";

		FileReader archivo = null;
		BufferedReader br = null;

		try {

			archivo = new FileReader(ubicacion + nombre);

			br = new BufferedReader(archivo);

			String linea;

			while ((linea = br.readLine()) != null) {

				contenido += linea + "\n";
			}

		} catch (IOException e) {

			Logger.getLogger(IoTextManager.class.getName()).log(Level.SEVERE, null, e);

		} finally {

			try {

				if (br != null) {
					br.close();
				}

				if (archivo != null) {
					archivo.close();
				}

			} catch (IOException e) {

				Logger.getLogger(IoTextManager.class.getName()).log(Level.SEVERE, null, e);
			}
		}

		return contenido;
	}

	public String loadingArchive(String nombreA, String ubicacionA) {

		FileReader fr = null;
		BufferedReader br = null;

		String contenido = "";

		try {

			File archivo = new File(ubicacionA + nombreA);

			fr = new FileReader(archivo);

			br = new BufferedReader(fr);

			String linea;

			while ((linea = br.readLine()) != null) {

				contenido += linea + "\n";
			}

		} catch (IOException e) {

			Logger.getLogger(IoTextManager.class.getName()).log(Level.SEVERE, null, e);

		} finally {

			try {

				if (br != null) {
					br.close();
				}

				if (fr != null) {
					fr.close();
				}

			} catch (IOException e) {

				Logger.getLogger(IoTextManager.class.getName()).log(Level.SEVERE, null, e);
			}
		}

		return contenido;
	}
	
	
   

}