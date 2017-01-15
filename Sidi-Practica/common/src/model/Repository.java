package model;

import java.io.Serializable;
import java.rmi.server.UID;

/**
 * Repository POJO
 */
public class Repository implements Serializable {

	private final String name;
	private final String password;
	private final long internalIdentifier;
	private UID uid;
	private boolean online;

	public Repository(String name, String password, long internalIdentifier) {
		this.name = name;
		this.password = password;
		this.internalIdentifier = internalIdentifier;
		this.online = false;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public UID getUid() {
		return uid;
	}

	public void setUid(UID uid) {
		this.uid = uid;
	}


	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public long getInternalIdentifier() {
		return internalIdentifier;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Repository that = (Repository) o;

		return internalIdentifier == that.internalIdentifier;
	}

	@Override
	public int hashCode() {
		return (int) (internalIdentifier ^ (internalIdentifier >>> 32));
	}
}


