package util;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.RemoteService;

/**
 * Codebase helper class
 */
public class CodeBase {
	private static final String CODEBASE = "java.rmi.server.codebase";
	private static Registry registry;

	static {
		try {
			System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException ignored) {
		}
	}

	/**
	 * Add the clazz parameter to current classpath
	 *
	 * @param clazz Class to add to Classpath
	 */
	public static void set(Class<?> clazz) {
		String root = clazz.getProtectionDomain().getCodeSource().getLocation().toString();
		String path = System.getProperty(CODEBASE);
		if (path == null) {
			path = CodeBase.class.getProtectionDomain().getCodeSource().getLocation().toString();
		}
		if (path != null && !path.isEmpty()) {
			root = path + " " + root;
		}
		System.setProperty(CODEBASE, root);
	}


	/**
	 * Register a RemoteService class into current java RMI registry
	 *
	 * @param service to register
	 * @throws RemoteException
	 * @throws UnknownHostException
	 * @throws MalformedURLException
	 */
	public static void registerService(RemoteService service) throws RemoteException, UnknownHostException, MalformedURLException {
		if (registry == null) {
			registry = LocateRegistry.getRegistry();
		}
		if (registry == null) {
			registry = LocateRegistry.createRegistry(1099);
		}
		String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":1099/" + service.getName();
		Naming.rebind(url, service);
	}
}
