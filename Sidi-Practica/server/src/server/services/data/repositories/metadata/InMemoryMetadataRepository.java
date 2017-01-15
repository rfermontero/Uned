package server.services.data.repositories.metadata;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Metadata;

public class InMemoryMetadataRepository implements MetadataRepository {

	private static MetadataRepository instance;
	private final Map<UID, List<Metadata>> metadataMap = new HashMap<>();

	public static MetadataRepository getInstance() {
		if (instance == null) {
			instance = new InMemoryMetadataRepository();
		}
		return instance;
	}

	private InMemoryMetadataRepository() {
	}

	@Override
	public void addMetaData(Metadata metadata) {
		if (metadataMap.containsKey(metadata.getClientUidOwner())) {
			List<Metadata> metadataList = metadataMap.get(metadata.getClientUidOwner());
			metadataList.add(metadata);
			metadataMap.put(metadata.getClientUidOwner(), metadataList);
		} else {
			List<Metadata> metadataList = new LinkedList<>();
			metadataList.add(metadata);
			metadataMap.put(metadata.getClientUidOwner(), metadataList);
		}
	}

	@Override
	public List<Metadata> getMetadataFor(UID uid) {
		List<Metadata> result = metadataMap.get(uid);
		return result != null ? result : new ArrayList<>();
	}

	@Override
	public void remove(UID clientUid, Metadata metadata) {
		if (metadataMap.containsKey(metadata.getClientUidOwner())) {
			List<Metadata> metadataList = metadataMap.get(metadata.getClientUidOwner());
			metadataList.remove(metadata);
			metadataMap.put(metadata.getClientUidOwner(), metadataList);
		}
	}
}
