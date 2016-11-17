package com.uned.rfernandez.auth.backend.repositories.impl;

import java.rmi.server.UID;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.uned.rfernandez.auth.backend.model.SignUpException;
import com.uned.rfernandez.auth.backend.model.User;
import com.uned.rfernandez.auth.backend.repositories.AuthRepository;

class InMemoryAuthRepository implements AuthRepository {

	private static InMemoryAuthRepository instance;
	private final Map<UID, User> clientsMap;

	static InMemoryAuthRepository getInstance() {
		if (instance == null) {
			instance = new InMemoryAuthRepository();
		}
		return instance;
	}

	private InMemoryAuthRepository() {
		clientsMap = new HashMap<>();
	}

	@Override
	public UID singUp(User user) throws SignUpException {
		if (!clientsMap.containsValue(user)) {
			UID uid = new UID();
			clientsMap.put(uid, user);
			return uid;
		}
		throw new SignUpException();
	}

	@Override
	public Optional<User> getClientByName(String name) {
		return clientsMap.values()
				.stream()
				.filter(user -> user.getName().equals(name))
				.findFirst();
	}
}
