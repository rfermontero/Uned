package server.services.data.repositories.online;


import java.io.Serializable;
import java.rmi.server.UID;
import java.util.List;
import java.util.Map;

import model.Client;
import model.Repository;

/**
 * Repository for online clients
 */
public interface ConnectedClientsRepository extends Serializable {

	/**
	 * Map a client with a repository
	 *
	 * @param client
	 * @param repository
	 */
	void mapClientToRepository(Client client, Repository repository);

	/**
	 * Return all relations.
	 *
	 * @return
	 */
	Map<Client, Repository> getClientRepositoriesMap();

	/**
	 * Return a list of clients attached to specified repository
	 *
	 * @param repository
	 * @return
	 */
	List<Client> getClientsFor(Repository repository);

	/**
	 * Return a repository data for a client.
	 * @param client
	 * @return
	 */
	Repository getRepositoryFor(UID client);
}
