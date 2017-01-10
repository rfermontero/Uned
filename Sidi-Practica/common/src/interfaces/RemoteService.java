package interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteService extends Remote, Serializable {
	String getName() throws RemoteException;
}
