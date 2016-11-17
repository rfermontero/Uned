package com.uned.rfernandez;

import java.rmi.RemoteException;

import com.uned.rfernandez.auth.ServicioAutenticacionImpl;
import com.uned.rfernandez.auth.ServicioAutenticacionInterface;
import com.uned.rfernandez.auth.backend.ServicioDatosImpl;
import com.uned.rfernandez.auth.backend.ServicioDatosInterface;

public class ServicesFactory {

	private static ServicioAutenticacionInterface authService;
	private static ServicioDatosInterface dataService;


	static ServicioAutenticacionInterface getAuthService() {
		if (authService == null) {
			try {
				authService = ServicioAutenticacionImpl.getInstance();
			} catch (RemoteException e) {
				//TODO Log error
			}
		}
		return authService;
	}

	public static ServicioDatosInterface getDataService() {
		if (dataService == null) {
			dataService = ServicioDatosImpl.getInstance();
		}
		return dataService;
	}
}
