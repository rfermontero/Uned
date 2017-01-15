package repository.usecases;

import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import factories.RemoteServicesFactory;
import interfaces.ServicioDatosInterface;
import model.Client;
import repository.repository.RepositoryData;

/**
 * Get client with UIDs use case.
 */
public class GetClientWithUids {

	public List<ClientUid> execute() {
		try {
			ServicioDatosInterface dataService = RemoteServicesFactory.getDataService();
			List<Client> clients = dataService.getClientsFor(RepositoryData.getInstance().getRemoteIdentifier());
			return clients
					.stream()
					.map(client -> new ClientUid(client.getClientUID(), client.getName()))
					.collect(Collectors.toList());
		} catch (RemoteException ignored) {
		}
		return Collections.emptyList();
	}

	public class ClientUid {
		private final UID uid;
		private final String name;

		ClientUid(UID uid, String name) {
			this.uid = uid;
			this.name = name;
		}

		public UID getUid() {
			return uid;
		}

		public String getName() {
			return name;
		}
	}
}
