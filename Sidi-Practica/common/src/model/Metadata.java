package model;

import java.io.Serializable;
import java.rmi.server.UID;

/**
 * Metadata POJO
 */
public class Metadata implements Serializable {

	private final UID clientUidOwner;
	private final String name;

	public Metadata(UID clientUidOwner, String name) {
		this.clientUidOwner = clientUidOwner;
		this.name = name;
	}

	public UID getClientUidOwner() {
		return clientUidOwner;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Metadata metadata = (Metadata) o;

		return name != null ? name.equals(metadata.name) : metadata.name == null;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}