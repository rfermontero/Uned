package usecase;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import client.ClientData;
import factories.RemoteServicesFactory;
import interfaces.ServicioDatosInterface;
import model.Metadata;

/**
 * Get files use case
 */
public class GetFiles {

	public List<Metadata> execute() {
		List<Metadata> result = new ArrayList<>();
		ServicioDatosInterface dataService = RemoteServicesFactory.getDataService();
		try {
			result.addAll(dataService.getClientFiles(ClientData.getInstance().getUid()));
		} catch (RemoteException e) {
			System.out.println("Error subiendo fichero " + e.getMessage());
		}
		return result;
	}
}
