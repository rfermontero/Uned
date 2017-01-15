package usecase;

import java.rmi.RemoteException;

import client.ClientData;
import factories.RemoteServicesFactory;

public class DownloadFile {

	public boolean execute(String path) {
		try {
			return RemoteServicesFactory.getFileManager().download(ClientData.getInstance().getUid(), path);
		} catch (RemoteException ignored) {
			System.out.println("Error while request download file");
		}
		return false;
	}
}
