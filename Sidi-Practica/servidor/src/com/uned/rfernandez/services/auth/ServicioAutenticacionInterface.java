package com.uned.rfernandez.services.auth;

import java.rmi.RemoteException;
import java.rmi.server.UID;

import com.uned.rfernandez.services.data.model.SignUpException;


public interface ServicioAutenticacionInterface {

	UID singUpClient(String name, String password) throws RemoteException, SignUpException;

	boolean loginClient(String name, String password) throws RemoteException;

	UID singUpRepository(String name, String password) throws RemoteException, SignUpException;

	boolean loginRepository(String name, String password) throws RemoteException;
}
