package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UID;

/**
 * Este servicio se encarga de gestionar las operaciones de los clientes en relación a sus ficheros en la nube
 * (físicamente alojados en los repositorios). Usando este servicio el cliente podrá subir, bajar y borrar ficheros
 * en la nube, además de listar sus ficheros almacenados y compartir ficheros con otros clientes que estén registrados en el sistema.
 */
public interface ServicioGestorInterface extends RemoteService {
	String NAME = "ServicioGestor";


	/**
	 * Return a string to make a lookup in rmi registry in order to obtain remote repository service implementation.
	 *
	 * @param clientUid
	 * @return
	 * @throws RemoteException
	 */
	String getRemoteSrOperatorServiceFor(UID clientUid) throws RemoteException;


	/**
	 * Request a download to clientUid of specified file.
	 *
	 * @param clientUID
	 * @param file
	 * @return
	 * @throws RemoteException
	 */
	boolean download(UID clientUID, String file) throws RemoteException;
}
