package repository.usecases;

import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.util.Optional;

import factories.RemoteServicesFactory;
import interfaces.ServicioAutenticacionInterface;

public class SignUp {

	public Optional<UID> execute(String name, String password) {
		try {
			final ServicioAutenticacionInterface authServer = RemoteServicesFactory.getAuthService();
			return Optional.of(authServer.singUpRepository(name, password));
		} catch (RemoteException e) {
			//TODO error
		}
		return Optional.empty();
	}
}
