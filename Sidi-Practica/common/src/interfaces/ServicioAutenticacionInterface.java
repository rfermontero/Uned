package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UID;
import java.util.Optional;

public interface ServicioAutenticacionInterface extends RemoteService {

	String NAME = "AuthService";

	Optional<UID> singUpClient(String name, String password) throws RemoteException;

	Optional<UID> loginClient(String name, String password) throws RemoteException;

	Optional<UID> singUpRepository(String name, String password) throws RemoteException;

	Optional<UID> loginRepository(String name, String password) throws RemoteException;
}
