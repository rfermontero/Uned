package usecase;

import java.rmi.RemoteException;
import java.rmi.server.UID;

import client.ClientData;
import factories.RemoteServicesFactory;
import interfaces.ServicioClOperadorInterface;
import interfaces.ServicioGestorInterface;

/**
 * Delete file usecase
 */
public class DeleteFile {
	public boolean execute(String file) {
		ServicioGestorInterface fileManager = RemoteServicesFactory.getFileManager();
		String remoteUrlService;
		try {
			remoteUrlService = fileManager.getRemoteSrOperatorServiceFor(ClientData.getInstance().getUid());
			if (remoteUrlService != null) {
				ServicioClOperadorInterface uploadService = RemoteServicesFactory.getClOpService(remoteUrlService);
				final UID clientUid = ClientData.getInstance().getUid();
				try {
					return uploadService.removeFile(clientUid, file);
				} catch (RemoteException e) {
					System.out.println("Error subiendo fichero " + e.getMessage());
				}
				return false;
			}
		} catch (RemoteException e) {
			System.out.println("Error subiendo fichero " + e.getMessage());
		}
		return false;
	}
}
