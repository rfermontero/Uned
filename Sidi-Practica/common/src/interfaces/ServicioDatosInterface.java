package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.util.HashMap;
import java.util.List;

import model.Client;
import model.Metadata;
import model.Repository;

public interface ServicioDatosInterface extends RemoteService {

	String NAME = "DataService";

	UID signUpClient(Client client) throws RemoteException;

	UID loginClient(Client client) throws RemoteException;

	void removeClient(UID uid) throws RemoteException;

	UID signUpRepository(Repository repository) throws RemoteException;

	UID loginRepository(Repository repository) throws RemoteException;

	Repository attachRepositoryTo(Client client) throws RemoteException;

	Repository getRepositoryFor(UID client) throws RemoteException;

	List<Client> getClients() throws RemoteException;

	List<Repository> getRepositories() throws RemoteException;

	List<Client> getClientsFor(UID repositoryUid) throws RemoteException;

	HashMap<Client, Repository> getClientRepositories() throws RemoteException;

	void addMetadata(Metadata metadata) throws RemoteException;

	List<Metadata> getClientFiles(UID files) throws RemoteException;

	void removeMetadata(UID clientUid, Metadata metadata) throws RemoteException;

	Client getClientFor(UID clientUid) throws RemoteException;
}
