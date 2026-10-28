package co.edu.uptc.archiveFile.model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



// que es un archivo xml y un archivo json para que sirven y como son las estructuras

public class AllFilesSerealiaze {

	public FileRegistry deSerializarObjeto() {
		File archivo = new File("src/co/edu/uptc/archiveFile/model/resources/sofia.txt");
		ObjectInputStream objFile = null;
		FileRegistry file = null;

		try {
			FileInputStream inFile = new FileInputStream(archivo);

			objFile = new ObjectInputStream(inFile);

			if (objFile != null) {
				try {
					file = (FileRegistry) objFile.readObject();
				} catch (EOFException eof) {

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (file != null) {
					return file;
				}
			}

		} catch (FileNotFoundException e) {
			
			System.out.println("No hay datos guardados, se empieza con un registro vacio");
		} catch (IOException ex) {
			ex.printStackTrace();

		} finally {

			if (objFile != null) {
				try {
					objFile.close();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		}

		return new FileRegistry();

	}

	public void serializarObjeto(FileRegistry objS) {

		File archivo = new File("src/co/edu/uptc/archiveFile/model/resources/sofia.txt");
		ObjectOutputStream objFile = null;

		try {
			FileOutputStream outFile = new FileOutputStream(archivo);
			objFile = new ObjectOutputStream(outFile);

			if (objFile != null) {
				objFile.writeObject(objS);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			if (objFile != null) {
				try {
					objFile.close();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		}

	}

}