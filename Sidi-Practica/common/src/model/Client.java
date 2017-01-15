package model;

import java.io.Serializable;
import java.rmi.server.UID;

/**
 * Client POJO
 */
public class Client implements Serializable {

	private final String name;
	private final String password;
	private final long internalId;
	private UID clientUID;
	private UID repositoryId;
	private transient boolean online;

	public Client(String name, String password, long internalId) {
		this.name = name;
		this.password = password;
		this.internalId = internalId;
		this.online = false;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public UID getClientUID() {
		return clientUID;
	}

	public void setClientUID(UID clientUID) {
		this.clientUID = clientUID;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public void setRepositoryId(UID repositoryId) {
		this.repositoryId = repositoryId;
	}

	public UID getRepositoryId() {
		return repositoryId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Client client = (Client) o;

		return clientUID != null ? clientUID.equals(client.clientUID) : client.clientUID == null;
	}

	@Override
	public int hashCode() {
		return clientUID != null ? clientUID.hashCode() : 0;
	}

	public long getInternalId() {
		return internalId;
	}
}
