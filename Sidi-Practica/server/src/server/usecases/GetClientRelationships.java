package server.usecases;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import factories.RemoteServicesFactory;
import model.Client;
import model.Repository;


/**
 * Get all client-repository relations usecase.
 */
public class GetClientRelationships {
	public List<String> execute() {
		try {
			Map<Client, Repository> relations = RemoteServicesFactory.getDataService().getClientRepositories();
			return relations.entrySet().stream().map(clientRepositoryEntry -> clientRepositoryEntry.getKey().getName()
					.concat(" ")
					.concat(clientRepositoryEntry.getValue().getName())).collect(Collectors.toList());
		} catch (RemoteException ignored) {
		}
		return Collections.emptyList();
	}

}
