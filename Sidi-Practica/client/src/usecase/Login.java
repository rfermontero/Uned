package usecase;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.util.Optional;

import client.ClientData;
import factories.RemoteServicesFactory;

/**
 * Login use case
 */
public class Login {
	public Optional<UID> execute(String name, String password) {
		try {
			Optional<UID> loginResult =
					Optional.ofNullable(RemoteServicesFactory.getAuthService()
							.loginClient(name, password, ClientData.getInstance().getInternalId()));
			ClientData clientData = ClientData.getInstance();
			loginResult.ifPresent(uid -> {
				clientData.setUid(uid);
				clientData.setName(name);
			});
			return loginResult;
		} catch (RemoteException | MalformedURLException e) {
			System.out.println("Error while sign up " + e.getMessage());
		}
		return Optional.empty();
	}
}
