package server;

import server.gui.ServidorGui;
import server.services.auth.ServicioAutenticacionImpl;
import server.services.data.ServicioDatosImpl;
import server.services.filemanager.ServicioGestorImpl;
import util.CodeBase;


/**
 * Main server class
 * Set current codebase and init gui.
 */
class Servidor {

	public static void main(String[] args) {
		try {
			CodeBase.set(Servidor.class);
			CodeBase.registerService(ServicioDatosImpl.getInstance());
			CodeBase.registerService(ServicioAutenticacionImpl.getInstance());
			CodeBase.registerService(ServicioGestorImpl.getInstance());
			initGui();
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
	}

	private static void initGui() {
		new ServidorGui();
	}
}
