package repository.repository;

import java.rmi.server.UID;

import model.Client;

public interface RepositoryData {

	void saveRemoteIdentifier(UID uid);

	UID getRemoteIdentifier();

	long getInternalIdentifier();

	boolean isInitiated();

	void addClientFolder(Client client, String folder);

	static RepositoryData getInstance() {
		return InMemoryRepositoryData.getInstance();
	}
}
