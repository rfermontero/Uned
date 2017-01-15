package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.server.UID;
import java.util.Optional;
import javax.swing.*;

import usecase.Login;
import usecase.SignUp;

public class ClientInitGui extends JFrame {

	public ClientInitGui() throws HeadlessException {
		super("Cliente");
		setSize(new Dimension(350, 350));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel pnPanel0 = new JPanel();
		GridBagLayout gbPanel0 = new GridBagLayout();
		GridBagConstraints gbcPanel0 = new GridBagConstraints();
		pnPanel0.setLayout(gbPanel0);

		JButton btLogin = new JButton("Login");
		btLogin.addActionListener((ActionEvent e) -> {
			DataInput.OnClick dataInputListener = (name, password, dialog) -> {
				Optional<UID> loginResult = new Login().execute(name, password);
				if (loginResult.isPresent()) {
					new LoguedClientGui().setVisible(true);
					setVisible(false);
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Datos incorrectos.");
				}
			};
			new DataInput(dataInputListener, ClientInitGui.this).setVisible(true);
		});

		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 5;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(btLogin, gbcPanel0);
		pnPanel0.add(btLogin);

		JButton btSignUp = new JButton("Registrar");
		btSignUp.addActionListener((ActionEvent e) -> {
			DataInput.OnClick dataInputListener = (name, password, dialog) -> {
				Optional<UID> loginResult = new SignUp().execute(name, password);
				if (loginResult.isPresent()) {
					JOptionPane.showMessageDialog(this,
							"Registro correcto. \nHaz login.");
					dialog.setVisible(false);
					dialog.dispose();
				} else {
					JOptionPane.showMessageDialog(this,
							"No se ha podido registrar con esos datos, intentalo de nuevo");
				}
			};
			new DataInput(dataInputListener, ClientInitGui.this).setVisible(true);
		});

		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 9;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(btSignUp, gbcPanel0);
		pnPanel0.add(btSignUp);

		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 13;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;

		JLabel lbLabel0 = new JLabel("Cliente");
		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 2;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.VERTICAL;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(lbLabel0, gbcPanel0);
		pnPanel0.add(lbLabel0);
		pnPanel0.setVisible(true);
		add(pnPanel0);
		setVisible(true);

	}
}
