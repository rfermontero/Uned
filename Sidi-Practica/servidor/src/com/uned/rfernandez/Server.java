package com.uned.rfernandez;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UID;

import com.uned.rfernandez.auth.backend.model.SignUpException;

public interface Server extends Remote {
	String NAME = "Server";

	UID singUp(String name, String password) throws RemoteException, SignUpException;

	boolean login(String uniqueId, String password) throws RemoteException;
}
