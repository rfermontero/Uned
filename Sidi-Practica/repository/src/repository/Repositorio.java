package repository;


import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import repository.gui.RepositoryInitGui;
import repository.repository.RepositoryData;
import repository.services.clop.ServicioClOperadorImpl;
import repository.services.srop.ServicioSrOperadorImpl;
import util.CodeBase;

/**
 * Main repository class
 * Set current codebase and init gui.
 */
public class Repositorio {

	public static void main(String[] args) {
		try {
			CodeBase.set(Repositorio.class);
			long internalIdentifier = RepositoryData.getInstance().getInternalIdentifier();
			CodeBase.registerService(ServicioSrOperadorImpl.getInstance(internalIdentifier));
			CodeBase.registerService(ServicioClOperadorImpl.getInstance(internalIdentifier));
			initGui();
		} catch (RemoteException | UnknownHostException | MalformedURLException e) {
			System.out.println("Error " + e.getMessage());
		}
	}

	private static void initGui() {
		new RepositoryInitGui();
	}
}
