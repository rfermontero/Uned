package com.uned.rfernandez.auth;

import java.rmi.RemoteException;
import java.rmi.server.UID;

import com.uned.rfernandez.ServicesFactory;
import com.uned.rfernandez.auth.backend.ServicioDatosInterface;
import com.uned.rfernandez.auth.backend.model.SignUpException;
import com.uned.rfernandez.auth.backend.model.User;

public class ServicioAutenticacionImpl implements ServicioAutenticacionInterface {

	private static ServicioAutenticacionImpl instance;

	private final ServicioDatosInterface servicioDatosInterface;

	public static ServicioAutenticacionInterface getInstance() throws RemoteException {
		if (instance == null) {
			instance = new ServicioAutenticacionImpl(ServicesFactory.getDataService());
		}
		return instance;
	}

	private ServicioAutenticacionImpl(ServicioDatosInterface servicioDatosInterface) throws RemoteException {
		this.servicioDatosInterface = servicioDatosInterface;
	}

	@Override
	public UID singUp(String name, String password) throws RemoteException, SignUpException {
		User user = new User(name, password);
		return servicioDatosInterface.singUp(user);
	}

	@Override
	public boolean login(String user, String password) throws RemoteException {
		return servicioDatosInterface.login(user, password);
	}
}
