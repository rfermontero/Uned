package server.services.data.repositories.auth;

import java.io.Serializable;
import java.rmi.server.UID;
import java.util.Collection;
import java.util.Optional;

import model.Client;
import model.Repository;


public interface AuthRepository extends Serializable {

	Collection<Client> getClients();

	Collection<Repository> getRepositories();

	UID getUid(Client client);

	Optional<Client> getClientByName(String name);

	UID getUid(Repository client);

	Optional<Repository> getRepositoryByName(String name);

	Optional<Repository> getNextRepository();

	void setClientOnline(UID uid);

	void setClientOffline(UID uid);

	void setRepositoryOnline(UID uid);

	void setRepositoryOffline(UID uid);
}
