package interfaces;

import java.rmi.RemoteException;

import model.Client;


/**
 * Este servicio tiene un doble objetivo. Por un lado, suministra los métodos necesarios para que el servidor gestione
 * los lugares de almacenamiento para cada cliente, y por otro lado se encarga de la operación de bajada de ficheros
 * desde el repositorio al cliente, es decir, cuando un cliente quiere bajar un fichero se lo pide al servidor
 * mediante el servicio Gestor. Una vez que el servidor averigua que repositorio aloja el fichero requerido por el
 * cliente, éste llama a un método del Servicio Servidor-Operador y le pasa la URL del cliente para que pueda llamar
 * al método de descarga del servicio DiscoCliente que es el que realmente se encarga de la descarga
 */
public interface ServicioSrOperadorInterface extends RemoteService {

	String NAME = "SrOperador";

	/**
	 * Create a new folder for a client.
	 *
	 * @param client
	 * @return
	 * @throws RemoteException
	 */
	boolean createFolderFor(Client client) throws RemoteException;

	/**
	 * Receives a request to sent a specified file content to client disk.
	 *
	 * @param remoteDisk
	 * @param file
	 * @return
	 * @throws RemoteException
	 */
	boolean downloadFileTo(String remoteDisk, String file) throws RemoteException;
}
