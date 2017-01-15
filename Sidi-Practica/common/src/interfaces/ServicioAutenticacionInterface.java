package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UID;

public interface ServicioAutenticacionInterface extends RemoteService {

	String NAME = "AuthService";

	UID singUpClient(String name, String password, long internalId) throws RemoteException;

	UID loginClient(String name, String password, long internalId) throws RemoteException;

	UID singUpRepository(String name, String password, long internalIdentifier) throws RemoteException;

	UID loginRepository(String name, String password, long internalIdentifier) throws RemoteException;
}
