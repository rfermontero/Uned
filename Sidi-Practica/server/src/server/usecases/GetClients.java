package server.usecases;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import factories.RemoteServicesFactory;
import model.Client;


/**
 * Get all clients use case.
 */
public class GetClients {

	public List<String> execute() {
		List<String> clientNames = Collections.emptyList();
		try {
			Collection<Client> clients = RemoteServicesFactory.getDataService().getClients();
			return clients.stream().map(Client::getName).collect(Collectors.toList());
		} catch (RemoteException ignored) {

		}
		return clientNames;
	}

}
