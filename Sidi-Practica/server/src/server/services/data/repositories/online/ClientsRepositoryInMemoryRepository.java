package server.services.data.repositories.online;

import java.rmi.server.UID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import model.Client;
import model.Repository;


/**
 * @inheritDoc
 */
public class ClientsRepositoryInMemoryRepository implements ConnectedClientsRepository {

	private static ClientsRepositoryInMemoryRepository instance;

	private final HashMap<Client, Repository> onlineClientsRepositories = new HashMap<>();

	public static ClientsRepositoryInMemoryRepository getInstance() {
		if (instance == null) {
			instance = new ClientsRepositoryInMemoryRepository();
		}
		return instance;
	}

	@Override
	public void mapClientToRepository(Client client, Repository repository) {
		client.setRepositoryId(repository.getUid());
		onlineClientsRepositories.put(client, repository);
	}

	@Override
	public Map<Client, Repository> getClientRepositoriesMap() {
		return onlineClientsRepositories;
	}

	@Override
	public List<Client> getClientsFor(Repository repository) {
		return onlineClientsRepositories
				.entrySet()
				.stream()
				.filter(clientRepositoryEntry -> clientRepositoryEntry.getValue().equals(repository))
				.map(Entry::getKey)
				.collect(Collectors.toList());
	}

	@Override
	public Repository getRepositoryFor(UID client) {
		Entry<Client, Repository> entry = onlineClientsRepositories.entrySet().stream().filter(clientRepositoryEntry ->
				clientRepositoryEntry
						.getKey().getClientUID().equals(client)).findFirst().orElse(null);

		return entry != null ? entry.getValue() : null;
	}
}
