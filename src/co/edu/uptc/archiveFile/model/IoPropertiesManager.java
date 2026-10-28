package co.edu.uptc.archiveFile.model;

import java.io.File;
import java.io.FileInputStream;  // abre un archivo para LEER bytes
import java.io.FileOutputStream; // abre un archivo para ESCRIBIR bytes
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Properties;

public class IoPropertiesManager {

	private static String title;
	private static String[] claves = new String[0];
	private static String[] valores = new String[0];

	public static void createPropertiesFile(String nombre, String ubicacion, String titulo) {
		File file = new File(ubicacion + nombre);
		file.getParentFile().mkdirs(); // aqui se devuelve la carpeta que tiene resouyrces .mkdirs crea carpeta si ya teine no hace nada
		try (OutputStream outputStream = new FileOutputStream(file)) { // abre el archivo para escribir si ya existe lo sobreescribe
			System.out.println("creando escritura archivo");
			Properties prop = new Properties();
			prop.setProperty("nombre de la aplicacion", titulo);
			prop.setProperty("ubicacion del archivo", ubicacion);
			prop.store(outputStream, "archivo de configuracion");
		} catch (IOException e) {
			System.out.println("error de salida");
			e.printStackTrace();
		}
	}

	public static void readPropertiesFile(String nombre, String ubicacion) {
		File archivo = new File(ubicacion + nombre);
		try (InputStream inputStream = new FileInputStream(archivo)) {
			System.out.println("leyendo archivo");
			Properties prop = new Properties();
			prop.load(inputStream);
			title = prop.getProperty("nombre de la aplicacion");

			claves = new String[] { "nombre de la aplicacion", "ubicacion del archivo" };
			Arrays.sort(claves);
			valores = new String[claves.length];
			for (int i = 0; i < claves.length; i++) {
				valores[i] = prop.getProperty(claves[i]);
			}
		} catch (IOException e) {
			System.out.println("archivo no encontrado o error de entrada");
			e.printStackTrace();
		}
	}

	public static String getTitle() {
		return title;
	}

	public static void setTitle(String title) {
		IoPropertiesManager.title = title;
	}

	public static String[] getClaves() {
		return claves;
	}

	public static String[] getValores() {
		return valores;
	}

	public static void main(String[] args) {
		String nombre = "archivos.properties";
		String ubicacion = "src/co/edu/uptc/archiveFile/model/resources/";
		createPropertiesFile(nombre, ubicacion, "Archive File Manager");
		readPropertiesFile(nombre, ubicacion);
	}
}