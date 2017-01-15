package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;


public class Fichero implements Serializable {

	private static final long serialVersionUID = -701591859841871541L;
	private final String propietario;        //Identificador del propietario original del fichero
	private final String nombre;            //Nombre del fichero
	private long peso;                //Peso del fichero en bytes
	private long checksum;            //Suma de chequeo de los bytes del fichero
	private byte[] data;            //Contenido del fichero

	public Fichero(String ruta, String nombre, String propietario) {
		this.nombre = nombre;
		this.propietario = propietario;

		CheckedInputStream c = null;
		peso = 0;

		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			c = new CheckedInputStream(new FileInputStream(ruta + File.separator + nombre), new CRC32());
			peso = new File(ruta + File.separator+ nombre).length();
			data = new byte[(int) this.peso];
			int leido;
			while ((leido = c.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, leido);
			}
			data = buffer.toByteArray();
			c.close();

		} catch (FileNotFoundException ef) {
			System.err.println("Fichero no encontrado");
		} catch (IOException e) {
			System.err.println("Error leyendo fichero" + e.toString());
		}
		checksum = c.getChecksum().getValue();

	}

	public boolean escribirEn(OutputStream os) {
		long CheckSum;
		CheckedOutputStream cs = new CheckedOutputStream(os, new CRC32());
		try {
			cs.write(data);
			CheckSum = cs.getChecksum().getValue();
			cs.close();
			if (CheckSum != this.checksum) {
				return (false);    //Falla el checksum, deber�a mandarse de nuevo
			}
		} catch (Exception e) {
			System.err.println("Error escribiendo fichero" + e.toString());
		}
		return (true); //Fichero mandado Ok
	}

	public String obtenerPropietario() {
		return propietario;
	}

	public String obtenerNombre() {
		return nombre;
	}

	public long obtenerPeso() {
		return peso;
	}

	public long obtenerChecksum() {
		return checksum;
	}

}

