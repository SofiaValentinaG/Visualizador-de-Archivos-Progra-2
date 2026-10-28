package co.edu.uptc.archiveFile.model;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;

public class IoBinaryManager {

	// escribe con DataOutputStream (crea el archivo o lo sobreescribe)
	public void writeBinaryArchive(String name, String ubicacion, String texto) {
		File binary = new File(ubicacion + name);
		binary.getParentFile().mkdirs();
		try (DataOutputStream escribir = new DataOutputStream(new FileOutputStream(binary))) {
			// new FileOutputStream(binary): abre el archivo para escribir bytes. Si no
			// existe, lo crea. Si existe, lo sobreescribe desde cero.
			escribir.writeChars(texto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// lee con FileInputStream + BufferedInputStream y devuelve el texto
	public String readBinaryArchive(String name, String ubicacion) {
		File binary = new File(ubicacion + name);
		if (!binary.exists()) {
			return "";
		}
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(binary))) {
			byte[] bytes = buffer.readAllBytes();
			return new String(bytes, StandardCharsets.UTF_16BE);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	
}