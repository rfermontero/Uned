package repository.usecases;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import factories.RemoteServicesFactory;
import interfaces.ServicioDatosInterface;
import model.Client;
import repository.repository.RepositoryData;

/**
 * Get clients use case
 */
public class GetClients {

	public List<String> execute() {
		try {
			ServicioDatosInterface dataService = RemoteServicesFactory.getDataService();
			List<Client> clients = dataService.getClientsFor(RepositoryData.getInstance().getRemoteIdentifier());
			return clients.stream().map(Client::getName).collect(Collectors.toList());
		} catch (RemoteException ignored) {
		}
		return Collections.emptyList();
	}
}
