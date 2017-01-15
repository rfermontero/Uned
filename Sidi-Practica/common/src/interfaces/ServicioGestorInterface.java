package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UID;

public interface ServicioGestorInterface extends RemoteService {
	String NAME = "ServicioGestor";

	String getRemoteSrOperatorServiceFor(UID clientUid) throws RemoteException;

	boolean download(UID clientUID, String path) throws RemoteException;
}
