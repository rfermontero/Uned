package server.services.data.repositories.online;


import java.io.Serializable;
import java.rmi.server.UID;
import java.util.List;
import java.util.Map;

import model.Client;
import model.Repository;

public interface ConnectedClientsRepository extends Serializable {
	void mapClientToRepository(Client client, Repository repository);

	Map<Client,Repository> getClientRepositoriesMap();

	List<Client> getClientsFor(Repository repository);

	Repository getRepositoryFor(UID client);
}
