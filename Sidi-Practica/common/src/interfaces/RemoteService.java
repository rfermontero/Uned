package interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Base remote service interface. All other remote services should extends this interface.
 */
public interface RemoteService extends Remote, Serializable {
	/**
	 * Returns the name of the service in order to register or lookup it.
	 *
	 * @return
	 * @throws RemoteException
	 */
	String getName() throws RemoteException;
}
