package com.uned.rfernandez;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UID;
import java.rmi.server.UnicastRemoteObject;

import com.uned.rfernandez.auth.ServicioAutenticacionInterface;
import com.uned.rfernandez.auth.backend.model.SignUpException;

public class Servidor extends UnicastRemoteObject implements Server{

	private static final String CODEBASE = "java.rmi.server.codebase";

	private final ServicioAutenticacionInterface servicioAutenticacionInterface;

	private Servidor(ServicioAutenticacionInterface servicioAutenticacionInterface) throws RemoteException {
		super();
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

	private static void setCodeBase(Class<?> c) {
		String root = c.getProtectionDomain().getCodeSource().getLocation().toString();
		String path = System.getProperty(CODEBASE);
		if (path != null && !path.isEmpty()) {
			root = path + " " + root;
		}
		System.setProperty(CODEBASE, root);
	}

	@Override
	public UID singUp(String name, String password) throws RemoteException, SignUpException {
		return servicioAutenticacionInterface.singUp(name, password);
	}

	@Override
	public boolean login(String uniqueId, String password) throws RemoteException {
		return servicioAutenticacionInterface.login(uniqueId, password);
	}
}
