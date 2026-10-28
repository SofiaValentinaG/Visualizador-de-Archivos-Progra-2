package co.edu.uptc.archiveFile.model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileRegistry implements Serializable {

	/**
	 * 
	 */

	private ArrayList<FileInfo> files;

	public FileRegistry() {
		files = new ArrayList<>();
	}

	// busca un archivo por nombre y ubicacion, devuelve null si no existe
	public FileInfo search(String name, String location) {
		for (int i = 0; i < files.size(); i++) {
			FileInfo actual = files.get(i);
			if (actual.getName().equals(name) && actual.getLocation().equals(location)) {
				return actual;
			}
		}
		return null;
	}

	// registra la accion; si el archivo no estaba en la lista, lo agrega
	public void record(String name, String location, String type, String action, boolean changed) {
		FileInfo info = search(name, location);
		if (info == null) {
			info = new FileInfo(name, location, type);
			files.add(info);
		}
		info.setLastAction(action);
		info.setTimesHandled(info.getTimesHandled() + 1);
		if (changed) {
			info.setModified(true);
		}
	}

	// cuantos archivos hay de un tipo
	public int countByType(String type) {
		int total = 0;
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).getType().equals(type)) {
				total++;
			}
		}
		return total;
	}

	// texto con todo el historial, para mostrarlo en el text area
	public String getReport() {
		if (files.isEmpty()) {
			return "Todavía no se ha creado ni manipulado ningún archivo";
		}
		StringBuilder report = new StringBuilder();
		for (int i = 0; i < files.size(); i++) {
			report.append(files.get(i).toString()).append("\n");
		}
		return report.toString();
	}

	public ArrayList<FileInfo> getFiles() {
		return files;
	}
	
	
}