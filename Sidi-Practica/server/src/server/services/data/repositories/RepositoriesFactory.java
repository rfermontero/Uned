package server.services.data.repositories;


import server.services.data.repositories.auth.AuthRepository;
import server.services.data.repositories.auth.InMemoryAuthRepository;
import server.services.data.repositories.online.ConnectedClientsRepository;
import server.services.data.repositories.online.InMemoryConnectedClientsRepository;

public class RepositoriesFactory {

	public static AuthRepository getAuthRepository() {
		return InMemoryAuthRepository.getInstance();
	}

	public static ConnectedClientsRepository getConnectedClientsRepository() {
		return InMemoryConnectedClientsRepository.getInstance();
	}
}
