package services;

import java.rmi.RemoteException;

import interfaces.RemoteService;
import interfaces.ServicioDiscoClienteInterface;

public class ServicioDiscoClienteImpl implements ServicioDiscoClienteInterface {

	private static ServicioDiscoClienteInterface instance;


	private ServicioDiscoClienteImpl() {
	}

	public static RemoteService getInstance() {
		if (instance == null) {
			instance = new ServicioDiscoClienteImpl();
		}
		return instance;
	}

	@Override
	public String getName() throws RemoteException {
		return NAME;
	}
}
