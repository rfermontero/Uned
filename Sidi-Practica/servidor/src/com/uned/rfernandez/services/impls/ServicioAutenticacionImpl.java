package com.uned.rfernandez.services.impls;

import java.rmi.RemoteException;
import java.rmi.server.UID;

import com.uned.rfernandez.services.auth.ServicioAutenticacionInterface;
import com.uned.rfernandez.services.data.ServicioDatosInterface;
import com.uned.rfernandez.services.data.model.Repository;
import com.uned.rfernandez.services.data.model.SignUpException;
import com.uned.rfernandez.services.data.model.Client;

class ServicioAutenticacionImpl implements ServicioAutenticacionInterface {

	private static ServicioAutenticacionImpl instance;

	private final ServicioDatosInterface servicioDatosInterface;

	static ServicioAutenticacionInterface getInstance() throws RemoteException {
		if (instance == null) {
			instance = new ServicioAutenticacionImpl(ServicesFactory.getDataService());
		}
		return instance;
	}

	private ServicioAutenticacionImpl(ServicioDatosInterface servicioDatosInterface) throws RemoteException {
		this.servicioDatosInterface = servicioDatosInterface;
	}

	@Override
	public UID singUpClient(String name, String password) throws RemoteException, SignUpException {
		return servicioDatosInterface.singUp(new Client(name, password));
	}

	@Override
	public boolean loginClient(String name, String password) throws RemoteException {
		return servicioDatosInterface.login(new Client(name, password));
	}

	@Override
	public UID singUpRepository(String name, String password) throws RemoteException, SignUpException {
		return servicioDatosInterface.signUp(new Repository(name, password));
	}

	@Override
	public boolean loginRepository(String name, String password) throws RemoteException {
		return servicioDatosInterface.login(new Repository(name, password));
	}
}
