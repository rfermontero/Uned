package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UID;

/**
 * Se encarga de registrar y de autenticar, cuando sea necesario, las otras entidades participantes en el sistema:
 * clientes y repositorios.
 * Ambas entidades se tienen que dar de alta en el sistema y recibir un identificador único para poder operar
 * y realizar almacenaje de ficheros. El registro se lleva a cabo cuando este servicio devuelve a la entidad
 * demandante (cliente o repositorio) el identificador único con el que tiene que autenticarse para cualquier
 * operación que lo requiera en el sistema
 */
public interface ServicioAutenticacionInterface extends RemoteService {

	String NAME = "AuthService";

	/**
	 * Register a new client
	 *
	 * @param name
	 * @param password
	 * @param internalId
	 * @return
	 * @throws RemoteException
	 */
	UID singUpClient(String name, String password, long internalId) throws RemoteException;

	/**
	 * Login a client
	 *
	 * @param name
	 * @param password
	 * @param internalId
	 * @return
	 * @throws RemoteException
	 */
	UID loginClient(String name, String password, long internalId) throws RemoteException;

	/**
	 * Register a new repository
	 *
	 * @param name
	 * @param password
	 * @param internalIdentifier
	 * @return
	 * @throws RemoteException
	 */
	UID singUpRepository(String name, String password, long internalIdentifier) throws RemoteException;

	/**
	 * Login a repository
	 *
	 * @param name
	 * @param password
	 * @param internalIdentifier
	 * @return
	 * @throws RemoteException
	 */
	UID loginRepository(String name, String password, long internalIdentifier) throws RemoteException;
}
