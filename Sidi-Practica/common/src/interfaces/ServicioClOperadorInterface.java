package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UID;

import util.Fichero;


/**
 * Este servicio se encarga de las operaciones de subida de ficheros al repositorio y borrado de los mismos.
 * El servicio Gestor del servidor responde a la petición del cliente
 * enviándole la URL de este servicio para que pueda completar su operación.
 */
public interface ServicioClOperadorInterface extends RemoteService {
	String NAME = "ClienteOp";

	/**
	 * Upload a new Fichero to clientUid folder.
	 *
	 * @param file
	 * @param clientUid
	 * @return
	 * @throws RemoteException
	 */
	boolean uploadFile(Fichero file, UID clientUid) throws RemoteException;

	/**
	 * Remove a file stored in clientUid folder
	 *
	 * @param clientUid
	 * @param file
	 * @return
	 * @throws RemoteException
	 */
	boolean removeFile(UID clientUid, String file) throws RemoteException;
}
