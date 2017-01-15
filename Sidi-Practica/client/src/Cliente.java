import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import gui.ClientInitGui;
import services.ServicioDiscoClienteImpl;
import util.CodeBase;

public class Cliente {
	public static void main(String[] args) {
		try {
			CodeBase.set(Cliente.class);
			CodeBase.registerService(ServicioDiscoClienteImpl.getInstance());
			initGui();
		} catch (RemoteException | UnknownHostException | MalformedURLException e) {
			System.out.println("Error " + e.getMessage());
		}
	}

	private static void initGui() {
		new ClientInitGui();
	}
}
