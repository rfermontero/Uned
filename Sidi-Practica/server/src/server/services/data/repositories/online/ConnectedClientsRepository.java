package server.services.data.repositories.online;


import java.io.Serializable;

import model.Client;
import model.Repository;

public interface ConnectedClientsRepository extends Serializable {
	void mapClientToRepository(Client client, Repository repository);
}
