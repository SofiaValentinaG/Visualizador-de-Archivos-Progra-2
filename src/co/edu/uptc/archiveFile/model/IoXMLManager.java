package co.edu.uptc.archiveFile.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
public class IoXMLManager {
	
	// agregar una opcion para el usuario sobre con que serializar con xml o arcbhivos sereliazables
	
	
	public StringBuilder readingXML(String name, String ubicacion) {

	    StringBuilder sc = new StringBuilder();

	    File inputFile = new File(ubicacion + name);
	    SAXBuilder saxBuilder = new SAXBuilder();

	    try {
	        Document document = saxBuilder.build(inputFile);

	        Element elementoInicial = document.getRootElement();

	        sc.append("Elemento inicial: ")
	          .append(elementoInicial.getName())
	          .append("\n");

	        recorrerHijos(elementoInicial, sc);

	    } catch (JDOMException e) {
	        e.printStackTrace();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return sc;
	}
	public void recorrerHijos(Element elemento, StringBuilder sc) {

	    List<Element> listaetiquetasHijas = elemento.getChildren();

	    for (Element i : listaetiquetasHijas) {

	        sc.append("Hijo: ")
	          .append(i.getName())
	          .append("\n");

	        recorrerHijos(i, sc);
	    }
	}
	
	public void writingXML(FileRegistry registro, String name, String ubicacion) {
		new File(ubicacion).mkdirs();

		Element raiz = new Element("registro");

		for (FileInfo f : registro.getFiles()) {
			Element archivo = new Element("archivo");
			archivo.addContent(new Element("nombre").setText(f.getName()));
			archivo.addContent(new Element("ubicacion").setText(f.getLocation()));
			archivo.addContent(new Element("tipo").setText(f.getType()));
			archivo.addContent(new Element("fecha").setText(String.valueOf(f.getCreationDate())));
			archivo.addContent(new Element("ultimaAccion").setText(f.getLastAction()));
			archivo.addContent(new Element("modificado").setText(String.valueOf(f.isModified())));
			archivo.addContent(new Element("veces").setText(String.valueOf(f.getTimesHandled())));
			raiz.addContent(archivo);
		}

		Document documento = new Document(raiz);

		try (FileWriter escritor = new FileWriter(ubicacion + name)) {
			new XMLOutputter(Format.getPrettyFormat()).output(documento, escritor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public FileRegistry readingRegistryXML(String name, String ubicacion) {
		FileRegistry registro = new FileRegistry();
		File archivoXml = new File(ubicacion + name);

		if (!archivoXml.exists()) {
			return registro;
		}

		try {
			Document documento = new SAXBuilder().build(archivoXml);
			Element raiz = documento.getRootElement();

			for (Element archivo : raiz.getChildren("archivo")) {
				FileInfo f = new FileInfo(archivo.getChildText("nombre"), archivo.getChildText("ubicacion"),
						archivo.getChildText("tipo"));
				f.setCreationDate(archivo.getChildText("fecha"));
				f.setLastAction(archivo.getChildText("ultimaAccion"));
				f.setModified(Boolean.parseBoolean(archivo.getChildText("modificado")));
				f.setTimesHandled(Integer.parseInt(archivo.getChildText("veces")));
				registro.getFiles().add(f);
			}
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		return registro;
	}


}
