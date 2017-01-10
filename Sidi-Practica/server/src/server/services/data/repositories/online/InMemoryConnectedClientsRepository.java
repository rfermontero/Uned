package server.services.data.repositories.online;

import java.util.HashMap;

import model.Client;
import model.Repository;


public class InMemoryConnectedClientsRepository implements ConnectedClientsRepository {

	private static InMemoryConnectedClientsRepository instance;

	private final HashMap<Client, Repository> onlineClientsRepositories = new HashMap<>();

	public static InMemoryConnectedClientsRepository getInstance() {
		if (instance == null) {
			instance = new InMemoryConnectedClientsRepository();
		}
		return instance;
	}

	@Override
	public void mapClientToRepository(Client client, Repository repository) {
		onlineClientsRepositories.put(client, repository);
	}
}
