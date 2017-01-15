package interfaces;

import java.rmi.RemoteException;


/**
 * Utilizado por el servicio Servidor-Operador del repositorio para descargar al disco duro local del cliente el
 * fichero que este considere oportuno.
 */
public interface ServicioDiscoClienteInterface extends RemoteService {
	String NAME = "DiscoCliente";


	/**
	 * Receives content as byte[] and saves it in a file with specified name
	 *
	 * @param content
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	boolean uploadFile(byte[] content, String name) throws RemoteException;
}
