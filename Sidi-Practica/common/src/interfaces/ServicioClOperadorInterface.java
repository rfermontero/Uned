package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UID;

import util.Fichero;

public interface ServicioClOperadorInterface extends RemoteService {
	String NAME = "ClienteOp";

	boolean uploadFile(Fichero file, UID clientUid) throws RemoteException;

	boolean removeFile(UID clientUid, String folder) throws RemoteException;
}
