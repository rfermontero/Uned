package repository.usecases;

import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.util.Optional;

import factories.RemoteServicesFactory;
import interfaces.ServicioAutenticacionInterface;
import repository.RepositoryData;

public class Login {
	public Optional<UID> execute(String name, String password) {
		try {
			final ServicioAutenticacionInterface authServer = RemoteServicesFactory.getAuthService();
			Optional<UID> optionalUid = authServer.loginRepository(name, password);
			optionalUid.ifPresent(uid -> RepositoryData.getInstance().saveUID(uid));
			return optionalUid;
		} catch (RemoteException ignored) {
		}
		return Optional.empty();
	}
}
