package factories;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import interfaces.ServicioAutenticacionInterface;
import interfaces.ServicioDatosInterface;
import interfaces.ServicioSrOperadorInterface;

public class RemoteServicesFactory {

	private static ServicioAutenticacionInterface authService;
	private static ServicioDatosInterface dataService;
	private static ServicioSrOperadorInterface srOperatorService;

	public static ServicioAutenticacionInterface getAuthService() {
		if (authService == null) {
			try {
				authService = (ServicioAutenticacionInterface) Naming.lookup(ServicioAutenticacionInterface.NAME);
			} catch (NotBoundException | MalformedURLException | RemoteException ignored) {
			}
		}
		return authService;
	}

	public static ServicioDatosInterface getDataService() {
		if (dataService == null) {
			try {
				dataService = (ServicioDatosInterface) Naming.lookup(ServicioDatosInterface.NAME);
			} catch (NotBoundException | MalformedURLException | RemoteException ignored) {
			}
		}
		return dataService;
	}

	public static ServicioSrOperadorInterface getSrOpService(){
		if (srOperatorService == null) {
			try {
				srOperatorService = (ServicioSrOperadorInterface) Naming.lookup(ServicioSrOperadorInterface.NAME);
			} catch (NotBoundException | MalformedURLException | RemoteException ignored) {
			}
		}
		return srOperatorService;
	}
}
