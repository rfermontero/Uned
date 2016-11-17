package com.uned.rfernandez.auth;

import java.rmi.RemoteException;
import java.rmi.server.UID;

import com.uned.rfernandez.auth.backend.model.SignUpException;


public interface ServicioAutenticacionInterface {

	UID singUp(String name, String password) throws RemoteException, SignUpException;

	boolean login(String user, String password) throws RemoteException;
}
