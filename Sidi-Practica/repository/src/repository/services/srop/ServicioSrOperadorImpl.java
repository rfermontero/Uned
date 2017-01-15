package repository.services.srop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;

import factories.RemoteServicesFactory;
import interfaces.ServicioDiscoClienteInterface;
import interfaces.ServicioSrOperadorInterface;
import model.Client;
import repository.repository.RepositoryData;

/**
 * @inheritDoc
 */
public class ServicioSrOperadorImpl implements ServicioSrOperadorInterface {

	private static ServicioSrOperadorImpl instance;
	private final long id;

	private ServicioSrOperadorImpl(long internalIdentifier) {
		this.id = internalIdentifier;
	}

	public static ServicioSrOperadorInterface getInstance(long internalIdentifier) {
		if (instance == null) {
			instance = new ServicioSrOperadorImpl(internalIdentifier);
		}
		return instance;
	}

	@Override
	public boolean createFolderFor(Client client) throws RemoteException {
		final RepositoryData repositoryData = RepositoryData.getInstance();
		File folder = new File(".");
		File clientFolder = new File(folder, client.getClientUID().toString());
		if (clientFolder.mkdirs()) {
			repositoryData.addClientFolder(client, clientFolder.getAbsolutePath());
			return true;
		}
		return false;
	}

	@Override
	public boolean downloadFileTo(String remoteRepository, String path) throws RemoteException {
		ServicioDiscoClienteInterface remoteDisk = RemoteServicesFactory.getClientDiskService(remoteRepository);
		File file = new File(path);
		try {
			return remoteDisk.uploadFile(Files.readAllBytes(Paths.get(file.getAbsolutePath())), file.getName());
		} catch (IOException e) {
		}
		return false;
	}

	@Override
	public String getName() throws RemoteException {
		return NAME + id;
	}
}
