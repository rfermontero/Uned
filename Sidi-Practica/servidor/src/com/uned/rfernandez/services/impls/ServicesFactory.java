package com.uned.rfernandez.services.impls;

import java.rmi.RemoteException;

import com.uned.rfernandez.services.auth.ServicioAutenticacionInterface;
import com.uned.rfernandez.services.data.ServicioDatosImpl;
import com.uned.rfernandez.services.data.ServicioDatosInterface;

public class ServicesFactory {

	private static ServicioAutenticacionInterface authService;
	private static ServicioDatosInterface dataService;


	public static ServicioAutenticacionInterface getAuthService() {
		if (authService == null) {
			try {
				authService = ServicioAutenticacionImpl.getInstance();
			} catch (RemoteException e) {
				//TODO Log error
			}
		}
		return authService;
	}

	static ServicioDatosInterface getDataService() {
		if (dataService == null) {
			dataService = ServicioDatosImpl.getInstance();
		}
		return dataService;
	}
}
