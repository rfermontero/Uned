package interfaces;

import java.rmi.RemoteException;

public interface ServicioDiscoClienteInterface extends RemoteService {
	String NAME = "DiscoCliente";

	boolean uploadFile(byte[] content, String name) throws RemoteException;
}
