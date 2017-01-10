package repository;

import java.rmi.server.UID;

public class InMemoryRepositoryData implements RepositoryData {

	private static RepositoryData instance;
	private UID uid;
	private boolean initiated;

	static RepositoryData getIntance() {
		if (instance == null) {
			instance = new InMemoryRepositoryData();
		}
		return instance;
	}

	@Override
	public void saveUID(UID uid) {
		this.uid = uid;
		this.initiated = true;
	}

	@Override
	public UID getUid() {
		return uid;
	}

	@Override
	public boolean isInitiated() {
		return initiated;
	}
}
