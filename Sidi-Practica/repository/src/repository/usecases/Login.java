package repository.usecases;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.util.Optional;

import factories.RemoteServicesFactory;
import interfaces.ServicioAutenticacionInterface;
import repository.repository.RepositoryData;

/**
 * Login use case
 */
public class Login {
	public Optional<UID> execute(String name, String password) {
		try {
			final RepositoryData repositoryData = RepositoryData.getInstance();
			final ServicioAutenticacionInterface authServer = RemoteServicesFactory.getAuthService();
			Optional<UID> optionalUid = Optional.ofNullable(authServer.loginRepository(name, password, repositoryData.getInternalIdentifier()));
			optionalUid.ifPresent(repositoryData::saveRemoteIdentifier);
			return optionalUid;
		} catch (RemoteException | MalformedURLException ignored) {
			System.out.println("Error haciendo loginRepository" + ignored.getMessage());
		}
		return Optional.empty();
	}
}
