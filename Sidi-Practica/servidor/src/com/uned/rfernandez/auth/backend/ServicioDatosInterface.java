package com.uned.rfernandez.auth.backend;

import java.rmi.server.UID;

import com.uned.rfernandez.auth.backend.model.SignUpException;
import com.uned.rfernandez.auth.backend.model.User;

public interface ServicioDatosInterface {

	UID singUp(User user) throws SignUpException;

	boolean login(String name, String password);
}
