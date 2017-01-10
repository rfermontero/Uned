package repository;

import java.rmi.server.UID;

public interface RepositoryData {

	void saveUID(UID uid);

	UID getUid();

	boolean isInitiated();

	static RepositoryData getInstance() {
		return new InMemoryRepositoryData();
	}
}
