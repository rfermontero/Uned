package server.usecases;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import factories.RemoteServicesFactory;
import model.Repository;

/**
 * Get all repositories usecase.
 */
public class GetRepositories {
	public List<String> execute() {
		List<String> repositorieNames = Collections.emptyList();
		try {
			Collection<Repository> clients = RemoteServicesFactory.getDataService().getRepositories();
			return clients.stream().map(Repository::getName).collect(Collectors.toList());
		} catch (RemoteException ignored) {

		}
		return repositorieNames;
	}
}
