package server.services.data.repositories.auth;

import java.io.Serializable;
import java.rmi.server.UID;
import java.util.Collection;
import java.util.Optional;

import model.Client;
import model.Repository;


/**
 * Repository for authentication data.
 * Provides methods to fetch all related data with it.
 */
public interface AuthRepository extends Serializable {


	/**
	 * @return All clients
	 */
	Collection<Client> getClients();

	/**
	 * @return All repositories
	 */
	Collection<Repository> getRepositories();

	/**
	 * Get client UID
	 *
	 * @param client
	 * @return
	 */
	UID getUid(Client client);

	/**
	 * Get client by name
	 *
	 * @param name
	 * @return
	 */
	Optional<Client> getClientByName(String name);

	/**
	 * Return a repository UID
	 *
	 * @param repository
	 * @return
	 */
	UID getUid(Repository repository);

	/**
	 * Get a repository by name.
	 *
	 * @param name
	 * @return
	 */
	Optional<Repository> getRepositoryByName(String name);

	/**
	 * Return a new random repository for a client using RoundRobin algorithm.
	 *
	 * @return
	 */
	Optional<Repository> getNextRepository();

	Optional<Repository> getRepositoryFrom(UID uid);

	/**
	 * Set a client online.
	 *
	 * @param uid
	 */
	void setClientOnline(UID uid);

	/**
	 * Set a repository online
	 *
	 * @param uid
	 */
	void setRepositoryOnline(UID uid);

	/**
	 * Remote a client
	 *
	 * @param uid
	 */
	void removeClient(UID uid);

	/**
	 * Return a client by UID.
	 *
	 * @param uid
	 * @return
	 */
	Client getClientFrom(UID uid);
}
