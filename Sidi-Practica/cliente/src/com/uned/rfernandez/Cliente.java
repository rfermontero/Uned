package com.uned.rfernandez;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UID;

public class Cliente {

	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.getRegistry();
		Server server = (Server) registry.lookup(Server.NAME);
		UID uid = server.singUpClient("colymore", "aa");
		System.out.println(server);
		boolean result = server.loginClient("colymore", "aa");
		System.out.println(result);
	}
}
