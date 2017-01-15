package client;

import java.rmi.server.UID;

/**
 * @inheritDoc
 */
public class InMemoryClientData implements ClientData {
	private static ClientData instance;

	private UID uid;
	private String name;
	private final long internalId = System.currentTimeMillis();

	static ClientData getInstance() {
		if (instance == null) {
			instance = new InMemoryClientData();
		}
		return instance;
	}

	private InMemoryClientData() {
	}

	@Override
	public void setUid(UID uid) {
		this.uid = uid;
	}

	@Override
	public UID getUid() {
		return uid;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public long getInternalId() {
		return internalId;
	}
}
