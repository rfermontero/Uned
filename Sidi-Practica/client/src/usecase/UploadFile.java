package usecase;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UID;

import client.ClientData;
import factories.RemoteServicesFactory;
import interfaces.ServicioClOperadorInterface;
import interfaces.ServicioGestorInterface;
import util.Fichero;

/**
 * Upload fle use case
 */
public class UploadFile {

	public boolean execute(File file) {
		ServicioGestorInterface fileManager = RemoteServicesFactory.getFileManager();
		String remoteUrlService;
		try {
			remoteUrlService = fileManager.getRemoteSrOperatorServiceFor(ClientData.getInstance().getUid());
			if (remoteUrlService != null) {
				ServicioClOperadorInterface uploadService = RemoteServicesFactory.getClOpService(remoteUrlService);
				final UID clientUid = ClientData.getInstance().getUid();
				Fichero fileToSend = new Fichero(file.getParentFile().getAbsolutePath(), file.getName(), clientUid.toString());
				try {
					return uploadService.uploadFile(fileToSend, clientUid);
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
