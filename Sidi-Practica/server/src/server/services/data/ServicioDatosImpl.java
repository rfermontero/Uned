package server.services.data;

import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import interfaces.ServicioDatosInterface;
import model.Client;
import model.Metadata;
import model.Repository;
import server.services.data.repositories.RepositoriesFactory;
import server.services.data.repositories.auth.AuthRepository;
import server.services.data.repositories.metadata.MetadataRepository;
import server.services.data.repositories.online.ConnectedClientsRepository;

/**
 * @inheritDoc
 */
public class ServicioDatosImpl extends UnicastRemoteObject implements ServicioDatosInterface {

	private final AuthRepository authRepository;
	private final ConnectedClientsRepository connectedClientsRepository;
	private final MetadataRepository metadataRepository;

	private static ServicioDatosInterface instance;

	private ServicioDatosImpl(AuthRepository authRepository,
			ConnectedClientsRepository connectedClientsRepository,
			MetadataRepository metadataRepository) throws RemoteException {
		super();
		this.authRepository = authRepository;
		this.connectedClientsRepository = connectedClientsRepository;
		this.metadataRepository = metadataRepository;
	}

	public static ServicioDatosInterface getInstance() throws RemoteException {
		if (instance == null) {
			instance = new ServicioDatosImpl(RepositoriesFactory.getAuthRepository(),
					RepositoriesFactory.getConnectedClientsRepository(),
					RepositoriesFactory.getMetadataRepository());
		}
		return instance;
	}

	@Override
	public UID signUpClient(Client client) throws RemoteException {
		return authRepository.getUid(client);
	}

	@Override
	public void removeClient(UID uid) throws RemoteException {
		authRepository.removeClient(uid);
	}

	@Override
	public Repository attachRepositoryTo(Client client) throws RemoteException {
		Optional<Repository> repositoryOptional = authRepository.getNextRepository();
		repositoryOptional.ifPresent(repository -> connectedClientsRepository.mapClientToRepository(client, repository));
		return repositoryOptional.orElse(null);
	}

	@Override
	public Repository getRepositoryFor(UID client) throws RemoteException {
		return connectedClientsRepository.getRepositoryFor(client);
	}

	@Override
	public UID loginClient(Client client) throws RemoteException {
		Optional<Client> optionalUser = authRepository.getClientByName(client.getName());
		UID uid = null;
		if (optionalUser.isPresent()) {
			boolean match = optionalUser.get().getPassword().equals(client.getPassword());
			if (match) {
				uid = optionalUser.get().getClientUID();
				setOnlineClient(optionalUser.get().getClientUID());
			}
		}
		return uid;
	}

	@Override
	public UID signUpRepository(Repository repository) throws RemoteException {
		return authRepository.getUid(repository);
	}

	@Override
	public UID loginRepository(Repository repository) throws RemoteException {
		Optional<Repository> optionalRepository = authRepository.getRepositoryByName(repository.getName());
		UID uid = null;
		if (optionalRepository.isPresent()) {
			boolean match = optionalRepository.get().getPassword().equals(repository.getPassword());
			if (match) {
				uid = optionalRepository.get().getUid();
				setOnlineRepository(optionalRepository.get().getUid());
			}
		}
		return uid;
	}

	private boolean setOnlineClient(UID uid) throws RemoteException {
		authRepository.setClientOnline(uid);
		return true;
	}

	private boolean setOnlineRepository(UID uid) throws RemoteException {
		authRepository.setRepositoryOnline(uid);
		return true;
	}

	@Override
	public List<Client> getClients() throws RemoteException {
		return new ArrayList<>(authRepository.getClients());
	}

	@Override
	public List<Repository> getRepositories() throws RemoteException {
		return new ArrayList<>(authRepository.getRepositories());
	}

	@Override
	public List<Client> getClientsFor(UID uid) throws RemoteException {
		Optional<Repository> repositoryOptional = authRepository.getRepositoryFrom(uid);
		List<Client> clients = new ArrayList<>();
		if (repositoryOptional.isPresent()) {
			Repository repository = repositoryOptional.get();
			clients.addAll(connectedClientsRepository.getClientsFor(repository));
		}
		return clients;
	}

	@Override
	public HashMap<Client, Repository> getClientRepositories() throws RemoteException {
		return (HashMap<Client, Repository>) connectedClientsRepository.getClientRepositoriesMap();
	}

	@Override
	public void addMetadata(Metadata metadata) throws RemoteException {
		metadataRepository.addMetaData(metadata);
	}

	@Override
	public List<Metadata> getClientFiles(UID uid) throws RemoteException {
		return metadataRepository.getMetadataFor(uid);
	}

	@Override
	public void removeMetadata(UID clientUid, Metadata metadata) throws RemoteException {
		metadataRepository.remove(clientUid, metadata);
	}

	@Override
	public Client getClientFor(UID clientUid) throws RemoteException {
		return authRepository.getClientFrom(clientUid);
	}

	@Override
	public String getName() throws RemoteException {
		return NAME;
	}
}
