package server.services.auth;

import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.util.Optional;

import factories.RemoteServicesFactory;
import interfaces.ServicioAutenticacionInterface;
import interfaces.ServicioDatosInterface;
import interfaces.ServicioSrOperadorInterface;
import model.Client;
import model.Repository;


public class ServicioAutenticacionImpl implements ServicioAutenticacionInterface {

	private static ServicioAutenticacionImpl instance;

	public static ServicioAutenticacionInterface getInstance() throws RemoteException {
		if (instance == null) {
			instance = new ServicioAutenticacionImpl();
		}
		return instance;
	}

	private ServicioAutenticacionImpl() {
	}

	@Override
	public Optional<UID> singUpClient(String name, String password) throws RemoteException {
		final ServicioDatosInterface dataService = RemoteServicesFactory.getDataService();
		final ServicioSrOperadorInterface srOperatorService = RemoteServicesFactory.getSrOpService();
		final Client client = new Client(name, password);
		Optional<UID> clientUid = dataService.addClient(client);
		Optional<Repository> optionalRepository = dataService.getRepositoryForClient(client);
		clientUid.ifPresent(uid -> optionalRepository.ifPresent(repository -> {
			try {
				srOperatorService.createFolderFor(client);
			} catch (RemoteException ignored) {
			}
		}));
		return clientUid;
	}

	@Override
	public Optional<UID> loginClient(String name, String password) throws RemoteException {
		return RemoteServicesFactory.getDataService().login(new Client(name, password));
	}

	@Override
	public Optional<UID> singUpRepository(String name, String password) throws RemoteException {
		return RemoteServicesFactory.getDataService().addRepository(new Repository(name, password));
	}

	@Override
	public Optional<UID> loginRepository(String name, String password) throws RemoteException {
		return RemoteServicesFactory.getDataService().login(new Repository(name, password));
	}

	@Override
	public String getName() throws RemoteException {
		return NAME;
	}
}
