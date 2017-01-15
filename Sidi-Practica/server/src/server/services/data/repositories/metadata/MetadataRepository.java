package server.services.data.repositories.metadata;

import java.rmi.server.UID;
import java.util.List;

import model.Metadata;

public interface MetadataRepository {
	void addMetaData(Metadata metadata);

	List<Metadata> getMetadataFor(UID uid);

	void remove(UID clientUid, Metadata metadata);
}
