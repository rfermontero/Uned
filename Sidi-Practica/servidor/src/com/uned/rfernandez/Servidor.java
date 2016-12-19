package com.uned.rfernandez;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UID;
import java.rmi.server.UnicastRemoteObject;

import com.uned.rfernandez.services.auth.ServicioAutenticacionInterface;
import com.uned.rfernandez.services.data.model.SignUpException;
import com.uned.rfernandez.services.impls.ServicesFactory;

class Servidor extends UnicastRemoteObject implements Server {

	private static final String CODEBASE = "java.rmi.server.codebase";

	private final ServicioAutenticacionInterface servicioAutenticacionInterface;

	private Servidor(ServicioAutenticacionInterface servicioAutenticacionInterface) throws RemoteException {
		this.servicioAutenticacionInterface = servicioAutenticacionInterface;
		try {
			registerService();
		} catch (RemoteException e) {
			System.out.println("Error");
		}
	}

	private void registerService() throws RemoteException {
		LocateRegistry.getRegistry().rebind(Server.NAME, this);
	}

	public static void main(String[] args) {
		try {
			setCodeBase(Server.class);
			ServicioAutenticacionInterface authService = ServicesFactory.getAuthService();
			new Servidor(authService);
		} catch (RemoteException e) {
			System.out.println("Error");
		}
	}

	private static void setCodeBase(Class<?> clazz) {
		String root = clazz.getProtectionDomain().getCodeSource().getLocation().toString();
		String path = System.getProperty(CODEBASE);
		if (path != null && !path.isEmpty()) {
			root = path + " " + root;
		}
		System.setProperty(CODEBASE, root);
	}

	@Override
	public UID singUpClient(String name, String password) throws RemoteException, SignUpException {
		return servicioAutenticacionInterface.singUpClient(name, password);
	}

	@Override
	public boolean loginClient(String uniqueId, String password) throws RemoteException {
		return servicioAutenticacionInterface.loginClient(uniqueId, password);
	}

	@Override
	public UID singUpRepository(String name, String password) throws RemoteException, SignUpException {
		return servicioAutenticacionInterface.singUpRepository(name, password);
	}

	@Override
	public boolean loginRepository(String uniqueId, String password) throws RemoteException {
		return servicioAutenticacionInterface.loginRepository(uniqueId, password);
	}
}
