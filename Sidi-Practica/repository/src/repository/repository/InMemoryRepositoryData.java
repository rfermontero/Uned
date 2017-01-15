package repository.repository;

import java.rmi.server.UID;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import model.Client;

/**
 * @inheritDoc
 */
public class InMemoryRepositoryData implements RepositoryData {

	private static RepositoryData instance;

	private final Map<Client, String> repositoryClients = new HashMap<>();
	private final long internalIdentifier = System.currentTimeMillis();
	private Optional<UID> repositoryUID = Optional.empty();

	static RepositoryData getInstance() {
		if (instance == null) {
			instance = new InMemoryRepositoryData();
		}
		return instance;
	}

	@Override
	public void saveRemoteIdentifier(UID uid) {
		this.repositoryUID = Optional.of(uid);
	}

	@Override
	public UID getRemoteIdentifier() {
		return repositoryUID.orElse(null);
	}

	@Override
	public boolean isInitiated() {
		return repositoryUID.isPresent();
	}

	@Override
	public void addClientFolder(Client client, String folder) {
		repositoryClients.put(client, folder);
	}

	@Override
	public long getInternalIdentifier() {
		return internalIdentifier;
	}
}
