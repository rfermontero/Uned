package usecase;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.util.Optional;

import client.ClientData;
import factories.RemoteServicesFactory;

/**
 * Register use case
 */
public class SignUp {
	public Optional<UID> execute(String name, String password) {
		try {
			return Optional.ofNullable(RemoteServicesFactory.getAuthService()
					.singUpClient(name, password, ClientData.getInstance().getInternalId()));
		} catch (RemoteException | MalformedURLException e) {
			System.out.println("Error while sign up " + e.getMessage());
		}
		return Optional.empty();
	}
}
