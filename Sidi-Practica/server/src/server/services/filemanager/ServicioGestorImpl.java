package server.services.filemanager;

import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.rmi.server.UnicastRemoteObject;

import factories.RemoteServicesFactory;
import interfaces.RemoteService;
import interfaces.ServicioClOperadorInterface;
import interfaces.ServicioDatosInterface;
import interfaces.ServicioDiscoClienteInterface;
import interfaces.ServicioGestorInterface;
import interfaces.ServicioSrOperadorInterface;
import model.Client;
import model.Repository;

/**
 * @inheritDoc
 */
public class ServicioGestorImpl extends UnicastRemoteObject implements ServicioGestorInterface {

	private static ServicioGestorInterface instance;

	private ServicioGestorImpl() throws RemoteException {
		super();
	}

	@Override
	public String getRemoteSrOperatorServiceFor(UID clientUid) {
		String result = null;
		final ServicioDatosInterface dataService = RemoteServicesFactory.getDataService();
		try {
			Repository repository = dataService.getRepositoryFor(clientUid);
			final ServicioClOperadorInterface clOpInterface = RemoteServicesFactory.getClOpService(repository.getInternalIdentifier());
			if (clOpInterface != null) {
				result = String.valueOf(repository.getInternalIdentifier());
			}
		} catch (RemoteException ignored) {

		}
		return ServicioClOperadorInterface.NAME + result;
	}

	@Override
	public boolean download(UID clientUid, String file) throws RemoteException {
		String remoteRepositoryUrl = getRemoteServiceForDownload(clientUid);
		final ServicioDatosInterface dataService = RemoteServicesFactory.getDataService();
		final ServicioSrOperadorInterface srOpService = RemoteServicesFactory.getSrOpService(remoteRepositoryUrl);
		try {
			Client client = dataService.getClientFor(clientUid);
			final ServicioDiscoClienteInterface diskService = RemoteServicesFactory.getClientDiskService(client.getInternalId());
			if (diskService != null) {
				String remoteRepository = String.valueOf(ServicioDiscoClienteInterface.NAME + client.getInternalId());
				return srOpService.downloadFileTo(remoteRepository, file);
			}
		} catch (RemoteException e) {
			System.out.println("Error downloading file" + e.getMessage());
		}
		return false;
	}

	@Override
	public String getName() throws RemoteException {
		return NAME;
	}

	private String getRemoteServiceForDownload(UID clientUid) {
		String result = null;
		final ServicioDatosInterface dataService = RemoteServicesFactory.getDataService();
		try {
			Repository repository = dataService.getRepositoryFor(clientUid);
			final ServicioSrOperadorInterface srOperatorService = RemoteServicesFactory.getSrOpService(repository.getInternalIdentifier());
			if (srOperatorService != null) {
				result = String.valueOf(repository.getInternalIdentifier());
			}
		} catch (RemoteException ignored) {
		}
		return ServicioSrOperadorInterface.NAME + result;
	}

	public static RemoteService getInstance() {
		if (instance == null) {
			try {
				instance = new ServicioGestorImpl();
			} catch (RemoteException ignored) {
			}
		}
		return instance;
	}
}
