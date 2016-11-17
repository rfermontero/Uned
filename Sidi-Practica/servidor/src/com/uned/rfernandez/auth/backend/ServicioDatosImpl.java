package com.uned.rfernandez.auth.backend;

import java.rmi.server.UID;
import java.util.Optional;

import com.uned.rfernandez.auth.backend.model.SignUpException;
import com.uned.rfernandez.auth.backend.model.User;
import com.uned.rfernandez.auth.backend.repositories.AuthRepository;
import com.uned.rfernandez.auth.backend.repositories.impl.RepositoriesFactory;

public class ServicioDatosImpl implements ServicioDatosInterface {

	private final AuthRepository authRepository;
	private static ServicioDatosInterface instance;

	private ServicioDatosImpl(AuthRepository authRepository) {
		this.authRepository = authRepository;
	}

	public static ServicioDatosInterface getInstance() {
		if (instance == null) {
			instance = new ServicioDatosImpl(RepositoriesFactory.getAuthRepository());
		}
		return instance;
	}

	@Override
	public UID singUp(User user) throws SignUpException {
		return authRepository.singUp(user);
	}

	@Override
	public boolean login(String name, String password) {
		boolean present = false;
		Optional<User> optionalUser = authRepository.getClientByName(name);
		if (optionalUser.isPresent()) {
			present = optionalUser.get().getPassword().equals(password);
		}
		return present;
	}
}
