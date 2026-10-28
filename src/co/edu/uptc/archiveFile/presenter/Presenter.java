package co.edu.uptc.archiveFile.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import co.edu.uptc.archiveFile.model.AllFilesSerealiaze;
import co.edu.uptc.archiveFile.model.FileRegistry;
import co.edu.uptc.archiveFile.model.IoBinaryManager;
import co.edu.uptc.archiveFile.model.IoImageManager;
import co.edu.uptc.archiveFile.model.IoPropertiesManager;
import co.edu.uptc.archiveFile.model.IoTextManager;
import co.edu.uptc.archiveFile.model.IoXMLManager;
import co.edu.uptc.archiveFile.view.ViewManager;

public class Presenter implements ActionListener, WindowListener {

	private IoTextManager archiveFile;
	private ViewManager view;
	private IoImageManager image;
	private IoBinaryManager binary;
	private FileRegistry registry;
	private AllFilesSerealiaze serializer;
	private IoXMLManager xml;

	private boolean editando = false;

	private String ubicacion = "src/co/edu/uptc/archiveFile/model/resources/";
	private String ubicacionProperties = "src/co/edu/uptc/archiveFile/model/resources/";
	private String nameBinary = "matriz.dat";

	private String nombre = null;
	private String nombreProperties = "archivos.properties";
	private String nombreImage = "image.jpg";
	private String ubicacionBinary = "src/co/edu/uptc/archiveFile/model/resources/";
	private boolean editandoBinary = false;


	private String nombreXml = "registro.xml";
	private String ubicacionXml = "src/co/edu/uptc/archiveFile/model/resources/";

	private int tipoSerializacion = 0; 

	public Presenter() {

		String[] opciones = { "Serializar con XML", "Serializar normal" };

		int respuesta = JOptionPane.showOptionDialog(null, "¿Qué deseas hacer?", "Menú principal",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		if (respuesta == -1) {
			System.exit(0);
			return;
		}

		tipoSerializacion = respuesta;

		archiveFile = new IoTextManager();
		image = new IoImageManager();
		binary = new IoBinaryManager();
		serializer = new AllFilesSerealiaze();
		xml = new IoXMLManager();

		deserializar();

		IoPropertiesManager.readPropertiesFile(nombreProperties, ubicacionProperties);

		view = new ViewManager();
		cargarTitle();
		addListeners();
	}

	
	private void deserializar() {
		if (tipoSerializacion == 0) {
			registry = xml.readingRegistryXML(nombreXml, ubicacionXml);
		} else {
			registry = serializer.deSerializarObjeto();
		}

		if (registry == null) {
			registry = new FileRegistry();
		}
	}

	
	private void serializar() {
		if (tipoSerializacion == 0) {
			xml.writingXML(registry, nombreXml, ubicacionXml);
		} else {
			serializer.serializarObjeto(registry);
		}
	}

	private void addListeners() {
		view.addListeners(this);
		view.addWindowListener(this);
	}

	private void cargarTitle() {
		String titulo = IoPropertiesManager.getTitle();
		view.cargarTitle(titulo != null ? titulo : "Archive File Manager");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String accion = e.getActionCommand();

		if (accion.equals("Open File")) {

			if (nombre == null) {
				view.showMessage("Primero debe crear el nombre del archivo");
				return;
			}
			String contenido = archiveFile.ReadingFile(nombre, ubicacion);
			view.showArchive(contenido);
			registry.record(nombre, ubicacion, "texto", "Abierto", false);

		} else if (accion.equals("Edit File")) {

			if (nombre == null) {
				view.showMessage("Primero debe crear el nombre del archivo");
				return;
			}
			String contenido = archiveFile.ReadingFile(nombre, ubicacion);
			view.showEditor(contenido);
			editando = true;
			registry.record(nombre, ubicacion, "texto", "Abierto en el editor", false);

		} else if (accion.equals("Loading File")) {

			if (nombre == null) {
				view.showMessage("Primero debe crear el nombre del archivo");
				return;
			}

			if (editando) {
				String textoEscrito = view.getTextToEdit();
				archiveFile.writingArchive(nombre, ubicacion, textoEscrito);
				view.hideEditor();
				editando = false;
				registry.record(nombre, ubicacion, "texto", "Modificado", true);
			}

			String contenido = archiveFile.loadingArchive(nombre, ubicacion);
			view.showArchive(contenido);

		} else if (accion.equals("View Loading Image")) {

			view.showImage();

		} else if (accion.equals("View Loading table")) {

			view.showTable();

		} else if (accion.equals("Create File")) {

			String nuevoNombre = view.createFileMessage();

			if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
				view.showMessage("Debe escribir un nombre");
				return;
			}

			String nuevaUbicacion = archiveFile.fileChoooser();

			if (nuevaUbicacion == null) {
				view.showMessage("Debe elegir una carpeta");
				return;
			}

			nombre = nuevoNombre.trim();
			ubicacion = nuevaUbicacion;

			if (archiveFile.createArchive(nombre, ubicacion)) {
				view.showMessage("Archivo creado");
				registry.record(nombre, ubicacion, "texto", "Creado", true);
			} else {
				view.showMessage("El archivo ya existe");
			}

		} else if (accion.equals("Save File")) {

			if (nombre == null) {
				view.showMessage("Primero debe crear el nombre del archivo");
				return;
			}

			if (editando) {
				archiveFile.createArchive(nombre, ubicacion);
				archiveFile.writingArchive(nombre, ubicacion, view.getTextToEdit());
				view.showMessage("Archivo guardado");
				registry.record(nombre, ubicacion, "texto", "Guardado", true);
			} else {
				view.showMessage("Primero presiona Edit File");
			}

		} else if (accion.equals("Create archive Properties")) {

			String titulo = view.askTitle();

			if (titulo == null || titulo.trim().isEmpty()) {
				view.showMessage("Debe escribir un nombre");
				return;
			}

			IoPropertiesManager.createPropertiesFile(nombreProperties, ubicacionProperties, titulo.trim());
			String contenido = archiveFile.ReadingFile(nombreProperties, ubicacionProperties);
			view.showArchive(contenido); // lo muestra en el text area de BoardPanelView
			view.showMessage("Archivo properties creado");
			registry.record(nombreProperties, ubicacionProperties, "properties", "Creado", true);

		} else if (accion.equals("Save archive Properties")) {

			IoPropertiesManager.readPropertiesFile(nombreProperties, ubicacionProperties);
			cargarTitle();
			view.showProperties(IoPropertiesManager.getClaves(), IoPropertiesManager.getValores());
			view.showMessage("Properties cargado en la tabla");
			registry.record(nombreProperties, ubicacionProperties, "properties", "Cargado en la tabla", false);

		} else if (accion.equals("Open Image")) {

			image.loadImage(ubicacion, nombreImage);
			view.showImageBuffer(image.getImage()); // aqui solo muestra la imagen creada
			registry.record(nombreImage, ubicacion, "imagen", "Abierta", false);

		} else if (accion.equals("Edit Image")) {

			StringBuilder info = image.loadImage(ubicacion, nombreImage); // devuelve un string builder
			view.showEditorImage(info.toString()); // aqui se lo pasa para que lo ponga en el text area
			registry.record(nombreImage, ubicacion, "imagen", "Abierta en el editor", false);

		} else if (accion.equals("Loading Image")) {

			image.loadImage(ubicacion, nombreImage);
			int cambiados = image.applyChanges(view.getImageTextToEdit()); // aqui le pasamos los pixeles que editamos
			view.showImageBuffer(image.getImage()); // luego mostramos la imagen cambiada
			view.showMessage("Píxeles modificados: " + cambiados); // y le pasamos un mensaje
			registry.record(nombreImage, ubicacion, "imagen", "Píxeles editados", cambiados > 0);

		} else if (accion.equals("Edit for a binary Archive")) {

			String contenido = binary.readBinaryArchive(nameBinary, ubicacionBinary);
			view.showEditor(contenido);
			editandoBinary = true;
			registry.record(nameBinary, ubicacionBinary, "binario", "Abierto en el editor", false);

		} else if (accion.equals("Loading Binary Archive")) {

			if (editandoBinary) {
				binary.writeBinaryArchive(nameBinary, ubicacionBinary, view.getTextToEdit());
				view.hideEditor();
				editandoBinary = false;
				registry.record(nameBinary, ubicacionBinary, "binario", "Modificado", true);
			}

			String contenido = binary.readBinaryArchive(nameBinary, ubicacionBinary);
			view.showArchive(contenido);

		} else if (accion.equals("File History")) {

			view.showArchive(registry.getReport());
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		serializar();
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}
}