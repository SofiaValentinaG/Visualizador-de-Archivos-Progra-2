package co.edu.uptc.archiveFile.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileInfo implements Serializable{

	/**
	 * 
	 */

	private String name;
	private String location;
	private String type; 
	private String creationDate;
	private String lastAction;
	private boolean modified;
	private int timesHandled;

	public FileInfo(String name, String location, String type) {
		this.name = name;
		this.location = location;
		this.type = type;
		this.creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		this.lastAction = "Registrado";
		this.modified = false;
		this.timesHandled = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public String getLastAction() {
		return lastAction;
	}

	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public int getTimesHandled() {
		return timesHandled;
	}
	

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public void setTimesHandled(int timesHandled) {
		this.timesHandled = timesHandled;
	}

	@Override
	public String toString() {
		return "Archivo: " + name + "\n" + "Tipo: " + type + "\n" + "Ubicación: " + location + "\n"
				+ "Fecha de registro: " + creationDate + "\n" + "Última acción: " + lastAction + "\n"
				+ "Veces manipulado: " + timesHandled + "\n" + "Modificado: " + (modified ? "Sí" : "No") + "\n";
	}
}