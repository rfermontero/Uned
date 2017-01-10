package model;

import java.io.Serializable;
import java.rmi.server.UID;

public class Repository implements Serializable {

	private final String name;
	private final String password;
	private UID uid;
	private boolean online;

	public Repository(String name, String password) {
		this.name = name;
		this.password = password;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Repository that = (Repository) o;

		return name != null ? name.equals(that.name) : that.name == null;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
}
