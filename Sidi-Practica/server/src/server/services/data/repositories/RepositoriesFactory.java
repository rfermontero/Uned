package server.services.data.repositories;


import server.services.data.repositories.auth.AuthRepository;
import server.services.data.repositories.auth.InMemoryAuthRepository;
import server.services.data.repositories.metadata.InMemoryMetadataRepository;
import server.services.data.repositories.metadata.MetadataRepository;
import server.services.data.repositories.online.ClientsRepositoryInMemoryRepository;
import server.services.data.repositories.online.ConnectedClientsRepository;

/**
 * Factory for internal repositories
 */
public class RepositoriesFactory {

	public static AuthRepository getAuthRepository() {
		return InMemoryAuthRepository.getInstance();
	}

	public static ConnectedClientsRepository getConnectedClientsRepository() {
		return ClientsRepositoryInMemoryRepository.getInstance();
	}

	public static MetadataRepository getMetadataRepository() {
		return InMemoryMetadataRepository.getInstance();
	}
}
