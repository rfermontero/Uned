package client;

import java.rmi.server.UID;

public interface ClientData {

	void setUid(UID uid);

	UID getUid();

	String getName();

	void setName(String name);

	long getInternalId();

	static ClientData getInstance() {
		return InMemoryClientData.getInstance();
	}
}
