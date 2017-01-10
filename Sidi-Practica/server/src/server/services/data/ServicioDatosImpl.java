package server.services.data;

import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.util.Collection;
import java.util.Optional;

import interfaces.ServicioDatosInterface;
import model.Client;
import model.Repository;
import server.services.data.repositories.RepositoriesFactory;
import server.services.data.repositories.auth.AuthRepository;
import server.services.data.repositories.online.ConnectedClientsRepository;

public class ServicioDatosImpl implements ServicioDatosInterface {

	private final AuthRepository authRepository;
	private final ConnectedClientsRepository connectedClientsRepository;

	private static ServicioDatosInterface instance;

	private ServicioDatosImpl(AuthRepository authRepository,
			ConnectedClientsRepository connectedClientsRepository) {
		this.authRepository = authRepository;
		this.connectedClientsRepository = connectedClientsRepository;
	}

	public static ServicioDatosInterface getInstance() {
		if (instance == null) {
			instance = new ServicioDatosImpl(RepositoriesFactory.getAuthRepository(),
					RepositoriesFactory.getConnectedClientsRepository());
		}
		return instance;
	}

	@Override
	public Optional<UID> addClient(Client client) throws RemoteException {
		return Optional.ofNullable(authRepository.getUid(client));
	}

	@Override
	public Optional<Repository> getRepositoryForClient(Client client) throws RemoteException {
		Optional<Repository> repositoryOptional = authRepository.getNextRepository();
		repositoryOptional.ifPresent(repository -> connectedClientsRepository.mapClientToRepository(client, repository));
		return repositoryOptional;
	}

	@Override
	public Optional<UID> login(Client client) throws RemoteException {
		Optional<Client> optionalUser = authRepository.getClientByName(client.getName());
		Optional<UID> uidOptional = Optional.empty();
		if (optionalUser.isPresent()) {
			boolean match = optionalUser.get().getPassword().equals(client.getPassword());
			if (match) {
				uidOptional = Optional.of(optionalUser.get().getUid());
				setOnlineClient(optionalUser.get().getUid(), true);
			}
		}
		return uidOptional;
	}

	@Override
	public Optional<UID> addRepository(Repository repository) throws RemoteException {
		return Optional.ofNullable(authRepository.getUid(repository));
	}

	@Override
	public Optional<UID> login(Repository repository) throws RemoteException {
		Optional<Repository> optionalRepository = authRepository.getRepositoryByName(repository.getName());
		Optional<UID> uidOptional = Optional.empty();
		if (optionalRepository.isPresent()) {
			boolean match = optionalRepository.get().getPassword().equals(repository.getPassword());
			if (match) {
				uidOptional = Optional.of(optionalRepository.get().getUid());
				setOnlineRepository(optionalRepository.get().getUid(), true);
			}
		}
		return uidOptional;
	}

	@Override
	public void setOnlineClient(UID uid, boolean online) throws RemoteException {
		if (online) {
			authRepository.setClientOnline(uid);
		} else {
			authRepository.setClientOffline(uid);
		}
	}

	@Override
	public void setOnlineRepository(UID uid, boolean online) throws RemoteException {
		if (online) {
			authRepository.setRepositoryOnline(uid);
		} else {
			authRepository.setRepositoryOffline(uid);
		}
	}

	@Override
	public Collection<Client> getClients() throws RemoteException {
		return authRepository.getClients();
	}

	@Override
	public Collection<Repository> getRepositories() throws RemoteException {
		return authRepository.getRepositories();
	}

	@Override
	public String getName() throws RemoteException {
		return NAME;
	}
}
