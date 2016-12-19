package com.uned.rfernandez.services.data;

import java.rmi.server.UID;
import java.util.Optional;

import com.uned.rfernandez.services.data.model.Repository;
import com.uned.rfernandez.services.data.model.SignUpException;
import com.uned.rfernandez.services.data.model.Client;
import com.uned.rfernandez.services.data.repositories.AuthRepository;
import com.uned.rfernandez.services.data.repositories.impl.RepositoriesFactory;

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
	public UID singUp(Client client) throws SignUpException {
		return authRepository.singUp(client);
	}

	@Override
	public boolean login(Client client) {
		boolean present = false;
		Optional<Client> optionalUser = authRepository.getClientByName(client.getName());
		if (optionalUser.isPresent()) {
			present = optionalUser.get().getPassword().equals(client.getPassword());
		}
		return present;
	}

	@Override
	public UID signUp(Repository repository) throws SignUpException {
		return authRepository.singUp(repository);
	}

	@Override
	public boolean login(Repository repository) {
		boolean present = false;
		Optional<Client> optionalUser = authRepository.getClientByName(repository.getName());
		if (optionalUser.isPresent()) {
			present = optionalUser.get().getPassword().equals(repository.getPassword());
		}
		return present;
	}
}
