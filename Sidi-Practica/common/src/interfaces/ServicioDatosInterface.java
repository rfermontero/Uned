package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.util.Collection;
import java.util.Optional;

import model.Client;
import model.Repository;

public interface ServicioDatosInterface extends RemoteService {

	String NAME = "DataService";

	Optional<UID> addClient(Client client) throws RemoteException;

	Optional<Repository> getRepositoryForClient(Client client) throws RemoteException;

	Optional<UID> login(Client client) throws RemoteException;

	Optional<UID> addRepository(Repository repository) throws RemoteException;

	Optional<UID> login(Repository repository) throws RemoteException;

	void setOnlineClient(UID uid, boolean online) throws RemoteException;

	void setOnlineRepository(UID uid, boolean online) throws RemoteException;

	Collection<Client> getClients() throws RemoteException;

	Collection<Repository> getRepositories() throws RemoteException;
}
