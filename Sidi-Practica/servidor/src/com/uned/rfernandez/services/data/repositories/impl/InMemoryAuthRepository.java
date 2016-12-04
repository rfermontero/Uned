package com.uned.rfernandez.services.data.repositories.impl;

import java.rmi.server.UID;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.uned.rfernandez.services.data.model.Repository;
import com.uned.rfernandez.services.data.model.SignUpException;
import com.uned.rfernandez.services.data.model.Client;
import com.uned.rfernandez.services.data.repositories.AuthRepository;

class InMemoryAuthRepository implements AuthRepository {

	private static InMemoryAuthRepository instance;
	private final Map<UID, Client> clientsMap;
	private final Map<UID, Repository> repositoryMap;

	static InMemoryAuthRepository getInstance() {
		if (instance == null) {
			instance = new InMemoryAuthRepository();
		}
		return instance;
	}

	private InMemoryAuthRepository() {
		clientsMap = new HashMap<>();
		repositoryMap = new HashMap<>();
	}

	@Override
	public UID singUp(Client client) throws SignUpException {
		if (!clientsMap.containsValue(client)) {
			UID uid = new UID();
			clientsMap.put(uid, client);
			return uid;
		}
		throw new SignUpException();
	}

	@Override
	public Optional<Client> getClientByName(String name) {
		return clientsMap.values()
				.stream()
				.filter(client -> client.getName().equals(name))
				.findFirst();
	}

	@Override
	public UID singUp(Repository repository) throws SignUpException {
		if (!repositoryMap.containsValue(repository)) {
			UID uid = new UID();
			repositoryMap.put(uid, repository);
			return uid;
		}
		throw new SignUpException();
	}

	@Override
	public Optional<Repository> getRepositoryByName(String name) {
		return repositoryMap.values()
				.stream()
				.filter(repository -> repository.getName().equals(name))
				.findFirst();
	}
}
