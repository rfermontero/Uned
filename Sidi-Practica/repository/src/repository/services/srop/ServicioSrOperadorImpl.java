package repository.services.srop;

import java.io.File;
import java.rmi.RemoteException;

import interfaces.ServicioSrOperadorInterface;
import model.Client;

public class ServicioSrOperadorImpl implements ServicioSrOperadorInterface {

	private static ServicioSrOperadorImpl instance;

	public static ServicioSrOperadorInterface getInstance() {
		if (instance == null) {
			instance = new ServicioSrOperadorImpl();
		}
		return instance;
	}

	@Override
	public String getName() throws RemoteException {
		return NAME;
	}

	@Override
	public boolean createFolderFor(Client client) throws RemoteException {
		File file = new File(client.getUid().toString());
		return !file.exists() && file.mkdir();
	}
}
