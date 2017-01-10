import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import interfaces.RemoteService;
import services.ServicioDiscoClienteImpl;
import util.CodeBase;

public class Cliente {
	public static void main(String[] args) {

		try {
			CodeBase.set(Cliente.class);
			registerService(ServicioDiscoClienteImpl.getInstance());

		} catch (RemoteException ignored) {
		}
		while (true) {
		}
	}


	private static void registerService(RemoteService service) throws RemoteException {
		LocateRegistry.getRegistry().rebind(service.getName(), service);
	}
}
