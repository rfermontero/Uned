package com.uned.rfernandez.services.data.repositories;

import java.rmi.server.UID;
import java.util.Optional;

import com.uned.rfernandez.services.data.model.Repository;
import com.uned.rfernandez.services.data.model.SignUpException;
import com.uned.rfernandez.services.data.model.Client;

public interface AuthRepository{

	UID singUp(Client client) throws SignUpException;

	Optional<Client> getClientByName(String name);

	UID singUp(Repository client) throws SignUpException;

	Optional<Repository> getRepositoryByName(String name);
}
