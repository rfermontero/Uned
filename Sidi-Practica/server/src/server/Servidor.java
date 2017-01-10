package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import factories.RemoteServicesFactory;
import interfaces.RemoteService;
import server.gui.Gui;
import server.services.auth.ServicioAutenticacionImpl;
import server.services.data.ServicioDatosImpl;
import util.CodeBase;

class Servidor {

	private static void registerService(RemoteService service) throws RemoteException {
		LocateRegistry.getRegistry().rebind(service.getName(), service);
	}

	public static void main(String[] args) {
		try {
			CodeBase.set(Servidor.class);
			registerService(ServicioAutenticacionImpl.getInstance());
			registerService(ServicioDatosImpl.getInstance());
			new Gui();
			RemoteServicesFactory.getAuthService().singUpClient("name", "pass");
		} catch (RemoteException e) {
			System.out.println("Error");
		}
		while (true) {
		}
	}
}
