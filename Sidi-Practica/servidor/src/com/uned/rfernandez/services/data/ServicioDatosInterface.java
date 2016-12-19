package com.uned.rfernandez.services.data;

import java.rmi.server.UID;

import com.uned.rfernandez.services.data.model.Repository;
import com.uned.rfernandez.services.data.model.SignUpException;
import com.uned.rfernandez.services.data.model.Client;

public interface ServicioDatosInterface {

	UID singUp(Client client) throws SignUpException;

	boolean login(Client client);

	UID signUp(Repository repository) throws SignUpException;

	boolean login(Repository repository);
}
