package server.services.data.repositories.auth;

import java.rmi.server.UID;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import model.Client;
import model.Repository;

/**
 * @inheritDoc
 */
public class InMemoryAuthRepository implements AuthRepository {

	private static InMemoryAuthRepository instance;
	private final Map<UID, Client> clientsMap = new HashMap<>();
	private final Map<UID, Repository> repositoryMap = new HashMap<>();

	private int lastRepositoryIndex;

	public static InMemoryAuthRepository getInstance() {
		if (instance == null) {
			instance = new InMemoryAuthRepository();
		}
		return instance;
	}

	@Override
	public Collection<Client> getClients() {
		return clientsMap.values();
	}

	@Override
	public Collection<Repository> getRepositories() {
		return repositoryMap.values();
	}

	@Override
	public UID getUid(Client client) {
		if (!clientsMap.containsValue(client)) {
			UID uid = new UID();
			client.setClientUID(uid);
			clientsMap.put(uid, client);
			return uid;
		}
		return null;
	}

	@Override
	public Optional<Client> getClientByName(String name) {
		return clientsMap.values()
				.stream()
				.filter(client -> client.getName().equals(name))
				.findFirst();
	}

	@Override
	public UID getUid(Repository repository) {
		if (!repositoryMap.containsValue(repository)) {
			UID uid = new UID();
			repository.setUid(uid);
			repositoryMap.put(uid, repository);
			return uid;
		}
		return null;
	}

	@Override
	public Optional<Repository> getRepositoryByName(String name) {
		return repositoryMap.values()
				.stream()
				.filter(repository -> repository.getName().equals(name))
				.findFirst();
	}

	@Override
	public Optional<Repository> getNextRepository() {
		Optional<Repository> repositoryOptional = repositoryMap
				.values()
				.stream()
				.skip(lastRepositoryIndex)
				.filter(Repository::isOnline)
				.findFirst();
		if (repositoryOptional.isPresent()) {
			lastRepositoryIndex++;
			return repositoryOptional;
		} else {
			lastRepositoryIndex = 0;
			return repositoryMap.values().stream().filter(Repository::isOnline).skip(lastRepositoryIndex).findFirst();
		}
	}

	@Override
	public Optional<Repository> getRepositoryFrom(UID uid) {
		return Optional.ofNullable(repositoryMap.get(uid));
	}

	@Override
	public void setClientOnline(UID uid) {
		clientsMap.get(uid).setOnline(true);
	}

	@Override
	public void setRepositoryOnline(UID uid) {
		repositoryMap.get(uid).setOnline(true);
	}

	@Override
	public void removeClient(UID uid) {
		clientsMap.remove(uid);
	}

	@Override
	public Client getClientFrom(UID uid) {
		return clientsMap.get(uid);
	}
}
