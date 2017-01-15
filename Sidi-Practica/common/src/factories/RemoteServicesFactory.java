package factories;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.ServicioAutenticacionInterface;
import interfaces.ServicioClOperadorInterface;
import interfaces.ServicioDatosInterface;
import interfaces.ServicioDiscoClienteInterface;
import interfaces.ServicioGestorInterface;
import interfaces.ServicioSrOperadorInterface;

public class RemoteServicesFactory {

	private static Registry registry;

	public static ServicioAutenticacionInterface getAuthService() throws MalformedURLException {
		ServicioAutenticacionInterface authService = null;
		try {
			initRegistry();
			authService = (ServicioAutenticacionInterface) LocateRegistry.getRegistry().lookup(ServicioAutenticacionInterface.NAME);
		} catch (NotBoundException | RemoteException ignored) {
		}
		return authService;
	}

	public static ServicioDatosInterface getDataService() {
		ServicioDatosInterface dataService = null;
		try {
			initRegistry();
			dataService = (ServicioDatosInterface) LocateRegistry.getRegistry().lookup(ServicioDatosInterface.NAME);
		} catch (NotBoundException | RemoteException ignored) {
		}
		return dataService;
	}

	public static ServicioSrOperadorInterface getSrOpService(long id) {
		ServicioSrOperadorInterface srOperatorService = null;
		try {
			initRegistry();
			srOperatorService = (ServicioSrOperadorInterface) LocateRegistry.getRegistry().lookup(ServicioSrOperadorInterface.NAME + id);
		} catch (NotBoundException | RemoteException ignored) {
		}
		return srOperatorService;
	}

	public static ServicioSrOperadorInterface getSrOpService(String name) {
		ServicioSrOperadorInterface srOpService = null;
		try {
			initRegistry();
			srOpService = (ServicioSrOperadorInterface) LocateRegistry.getRegistry().lookup(name);
		} catch (NotBoundException | RemoteException ignored) {
		}
		return srOpService;
	}

	public static ServicioClOperadorInterface getClOpService(String name) {
		ServicioClOperadorInterface srClientService = null;
		try {
			initRegistry();
			srClientService = (ServicioClOperadorInterface) LocateRegistry.getRegistry().lookup(name);
		} catch (NotBoundException | RemoteException ignored) {
		}
		return srClientService;
	}

	public static ServicioClOperadorInterface getClOpService(long id) {
		ServicioClOperadorInterface srClientService = null;
		try {
			initRegistry();
			srClientService = (ServicioClOperadorInterface) LocateRegistry.getRegistry().lookup
					(ServicioClOperadorInterface.NAME + id);
		} catch (NotBoundException | RemoteException ignored) {
		}
		return srClientService;
	}

	public static ServicioDiscoClienteInterface getClientDiskService(String name) {
		ServicioDiscoClienteInterface clientDiskService = null;
		try {
			initRegistry();
			clientDiskService = (ServicioDiscoClienteInterface) LocateRegistry.getRegistry().lookup(name);
		} catch (NotBoundException | RemoteException ignored) {
		}
		return clientDiskService;
	}

	public static ServicioDiscoClienteInterface getClientDiskService(long id) {
		ServicioDiscoClienteInterface clientDiskService = null;
		try {
			initRegistry();
			clientDiskService = (ServicioDiscoClienteInterface) LocateRegistry.getRegistry().lookup
					(ServicioDiscoClienteInterface.NAME + id);
		} catch (NotBoundException | RemoteException ignored) {
		}
		return clientDiskService;
	}

	public static ServicioGestorInterface getFileManager() {
		ServicioGestorInterface fileManagerService = null;
		try {
			initRegistry();
			fileManagerService = (ServicioGestorInterface) LocateRegistry.getRegistry().lookup(ServicioGestorInterface.NAME);
		} catch (NotBoundException | RemoteException ignored) {
		}
		return fileManagerService;
	}


	private static void initRegistry() throws RemoteException {
		if (registry == null) {
			registry = LocateRegistry.getRegistry(1089);
		}
	}
}
