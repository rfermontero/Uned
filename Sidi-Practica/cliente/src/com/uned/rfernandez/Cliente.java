package com.uned.rfernandez;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UID;

public class Cliente {

	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.getRegistry();
		Server server = (Server) registry.lookup(Server.NAME);
		UID uid = server.singUp("colymore", "21082108");
		System.out.println(server);
		boolean result = server.login("colymore", "21082108k");
		System.out.println(result);
	}
}
