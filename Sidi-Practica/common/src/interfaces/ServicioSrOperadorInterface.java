package interfaces;

import java.rmi.RemoteException;

import model.Client;


public interface ServicioSrOperadorInterface extends RemoteService {

	String NAME = "SrOperador";

	boolean createFolderFor(Client client) throws RemoteException;
}
