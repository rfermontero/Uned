package services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;

import client.ClientData;
import interfaces.RemoteService;
import interfaces.ServicioDiscoClienteInterface;

/**
 * @inheritDoc
 */
public class ServicioDiscoClienteImpl implements ServicioDiscoClienteInterface {

	private static ServicioDiscoClienteInterface instance;
	private final long internalId;

	private ServicioDiscoClienteImpl(long id) {
		this.internalId = id;
	}

	public static RemoteService getInstance() {
		if (instance == null) {
			instance = new ServicioDiscoClienteImpl(ClientData.getInstance().getInternalId());
		}
		return instance;
	}

	@Override
	public String getName() throws RemoteException {
		return NAME + internalId;
	}

	@Override
	public boolean uploadFile(byte[] receivedFile, String name) {
		String path = new File(".").getAbsolutePath();
		File newFile = new File(path + name);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(newFile);
			fos.write(receivedFile);
			fos.close();
			return true;
		} catch (IOException e) {
			System.out.println("Error in client disk " + e.getMessage());
		}

		return false;
	}
}
