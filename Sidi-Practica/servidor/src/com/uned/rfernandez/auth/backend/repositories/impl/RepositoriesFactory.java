package com.uned.rfernandez.auth.backend.repositories.impl;

import com.uned.rfernandez.auth.backend.repositories.AuthRepository;

public class RepositoriesFactory {

	public static AuthRepository getAuthRepository(){
		return InMemoryAuthRepository.getInstance();
	}
}
