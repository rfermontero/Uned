package repository;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UID;
import java.util.Optional;

import interfaces.RemoteService;
import repository.services.srop.ServicioSrOperadorImpl;
import repository.usecases.Login;
import repository.usecases.SignUp;
import util.CodeBase;

public class Repositorio {

	private static final String CODEBASE = "java.rmi.server.codebase";

	public static void main(String[] args) {
		try {
			CodeBase.set(Repositorio.class);
			registerService(ServicioSrOperadorImpl.getInstance());

			Optional<UID> result = new SignUp().execute("nombre", "password");
			Optional<UID> res2ult = new Login().execute("nombre", "password");


		} catch (RemoteException ignored) {
		}
		while(true){}

	}

	private static void registerService(RemoteService service) throws RemoteException {
		LocateRegistry.getRegistry().rebind(service.getName(), service);
	}
}
