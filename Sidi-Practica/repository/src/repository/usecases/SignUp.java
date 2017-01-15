package repository.usecases;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.util.Optional;

import factories.RemoteServicesFactory;
import interfaces.ServicioAutenticacionInterface;
import repository.repository.RepositoryData;

/**
 * Register use case
 */
public class SignUp {

	public Optional<UID> execute(String name, String password) {
		try {
			final RepositoryData repositoryData = RepositoryData.getInstance();
			final ServicioAutenticacionInterface authServer = RemoteServicesFactory.getAuthService();
			Optional<UID> result = Optional.ofNullable(authServer.singUpRepository(name, password, repositoryData.getInternalIdentifier()));
			result.ifPresent(repositoryData::saveRemoteIdentifier);
			return result;
		} catch (RemoteException | MalformedURLException error) {
			System.out.println("Error haciendo sign up" + error.getMessage());
		}
		return Optional.empty();
	}
}
