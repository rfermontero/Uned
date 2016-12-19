package com.uned.rfernandez.services.data.repositories.impl;

import com.uned.rfernandez.services.data.repositories.AuthRepository;

public class RepositoriesFactory {

	public static AuthRepository getAuthRepository(){
		return InMemoryAuthRepository.getInstance();
	}
}
