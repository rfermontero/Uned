package repository.services.clop;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UID;

import factories.RemoteServicesFactory;
import interfaces.ServicioClOperadorInterface;
import model.Metadata;
import repository.utils.FileUtils;
import util.Fichero;

/**
 * @inheritDoc
 */
public class ServicioClOperadorImpl implements ServicioClOperadorInterface {

	private static ServicioClOperadorInterface instance;
	private final long id;

	private ServicioClOperadorImpl(long internalIdentifier) {
		this.id = internalIdentifier;
	}

	public static ServicioClOperadorInterface getInstance(long internalIdentifier) {
		if (instance == null) {
			instance = new ServicioClOperadorImpl(internalIdentifier);
		}
		return instance;
	}

	@Override
	public boolean uploadFile(Fichero file, UID clientUid) {
		boolean result = false;
		try {
			File fileToWrite = FileUtils.getFileFor(file.obtenerPropietario(), file.obtenerNombre());
			if (fileToWrite.exists()) {
				System.out.println("Overriding file");
				fileToWrite.delete();
				fileToWrite.createNewFile();
			}
			OutputStream outputStream = FileUtils.getOutputStreamFor(fileToWrite);
			file.escribirEn(outputStream);
			Metadata metadata = new Metadata(clientUid, fileToWrite.getAbsolutePath());
			RemoteServicesFactory.getDataService().addMetadata(metadata);
			result = true;
		} catch (IOException e) {
			System.out.println("Error reading file");
		}
		return result;
	}

	@Override
	public boolean removeFile(UID clientUid, String file) throws RemoteException {
		File fileToRemove = new File(file);
		Metadata metadata = new Metadata(clientUid, fileToRemove.getAbsolutePath());
		boolean result = fileToRemove.delete();
		if (result) {
			RemoteServicesFactory.getDataService().removeMetadata(clientUid, metadata);
		}
		return result;
	}

	@Override
	public String getName() throws RemoteException {
		return NAME + id;
	}
}
