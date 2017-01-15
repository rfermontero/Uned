package server.services.auth;

import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

import factories.RemoteServicesFactory;
import interfaces.ServicioAutenticacionInterface;
import interfaces.ServicioDatosInterface;
import interfaces.ServicioSrOperadorInterface;
import model.Client;
import model.Repository;

/**
 * @inheritDoc
 */
public class ServicioAutenticacionImpl extends UnicastRemoteObject implements ServicioAutenticacionInterface {

	private static ServicioAutenticacionImpl instance;

	public static ServicioAutenticacionInterface getInstance() throws RemoteException {
		if (instance == null) {
			instance = new ServicioAutenticacionImpl();
		}
		return instance;
	}

	private ServicioAutenticacionImpl() throws RemoteException {
		super();
	}

	@Override
	public UID singUpClient(String name, String password, long internalId) throws RemoteException {
		final UID[] result = {null};
		final Client client = new Client(name, password, internalId);
		final ServicioDatosInterface dataService = RemoteServicesFactory.getDataService();
		final Optional<UID> optionalClientUID = Optional.ofNullable(dataService.signUpClient(client));
		optionalClientUID.ifPresent(uid -> {
			try {
				client.setClientUID(uid);
				final Optional<Repository> optionalRepository = Optional.ofNullable(dataService.attachRepositoryTo(client));
				optionalRepository.ifPresent(repository -> {
					result[0] = uid;
					final ServicioSrOperadorInterface srOperatorService = RemoteServicesFactory.getSrOpService(repository.getInternalIdentifier());
					if (srOperatorService != null) {
						if (optionalClientUID.isPresent() && optionalRepository.isPresent()) {
							client.setRepositoryId(repository.getUid());
							try {
								srOperatorService.createFolderFor(client);
							} catch (RemoteException ignored) {
							}
						} else {
							optionalClientUID.ifPresent(clientUid -> {
								try {
									dataService.removeClient(clientUid);
								} catch (RemoteException e) {
									System.out.println("Error deleting temporal client created");
								}
							});
						}

					}
				});
			} catch (RemoteException ignored) {
			}

		});
		return result[0];
	}

	@Override
	public UID loginClient(String name, String password, long internalIdentifier) throws RemoteException {
		return RemoteServicesFactory.getDataService().loginClient(new Client(name, password, internalIdentifier));
	}

	@Override
	public UID singUpRepository(String name, String password, long internalIdentifier) throws RemoteException {
		return RemoteServicesFactory.getDataService().signUpRepository(new Repository(name, password, internalIdentifier));
	}

	@Override
	public UID loginRepository(String name, String password, long internalIdentifier) throws RemoteException {
		return RemoteServicesFactory.getDataService().loginRepository(new Repository(name, password, internalIdentifier));
	}

	@Override
	public String getName() throws RemoteException {
		return NAME;
	}
}
