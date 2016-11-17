package com.uned.rfernandez.auth.backend.repositories;

import java.rmi.server.UID;
import java.util.Optional;

import com.uned.rfernandez.auth.backend.model.SignUpException;
import com.uned.rfernandez.auth.backend.model.User;

public interface AuthRepository{

	UID singUp(User user) throws SignUpException;

	Optional<User> getClientByName(String name);
}
