package server.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import gui.List;
import server.usecases.GetClientRelationships;
import server.usecases.GetClients;
import server.usecases.GetRepositories;

public class ServidorGui extends JFrame {

	private JPanel pnPanel0;

	public ServidorGui() throws HeadlessException {
		super("Servidor");
		setSize(new Dimension(350, 350));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pnPanel0 = new JPanel();
		GridBagLayout gbPanel0 = new GridBagLayout();
		GridBagConstraints gbcPanel0 = new GridBagConstraints();
		pnPanel0.setLayout(gbPanel0);

		JButton btShowClientsButton = new JButton("Mostrar clientes");
		btShowClientsButton.addActionListener((ActionEvent e) -> {
			java.util.List<String> clients = new GetClients().execute();
			List clientsGui = new List(clients, "Lista de clientes", (comp) -> {
				ServidorGui.this.remove(comp);
				pnPanel0.setVisible(true);
			});
			clientsGui.setVisible(true);
			pnPanel0.setVisible(false);
			add(clientsGui);
		});

		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 5;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(btShowClientsButton, gbcPanel0);
		pnPanel0.add(btShowClientsButton);

		JButton btShowRepositoriesButton = new JButton("Mostrar repositorios");
		btShowRepositoriesButton.addActionListener((ActionEvent e) -> {
			java.util.List<String> relations = new GetRepositories().execute();
			List clientsGui = new List(relations, "Lista de repositorios", (comp) -> {
				ServidorGui.this.remove(comp);
				pnPanel0.setVisible(true);
			});
			clientsGui.setVisible(true);
			pnPanel0.setVisible(false);
			add(clientsGui);
		});
		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 9;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(btShowRepositoriesButton, gbcPanel0);
		pnPanel0.add(btShowRepositoriesButton);

		JButton btShowClientsRelations = new JButton("Mostrar relaccion cliente-repositorios");
		btShowClientsRelations.addActionListener((ActionEvent e) -> {
			java.util.List<String> relations = new GetClientRelationships().execute();
			List clientsGui = new List(relations, "Lista de relacciones", (comp) -> {
				ServidorGui.this.remove(comp);
				pnPanel0.setVisible(true);
			});
			clientsGui.setVisible(true);
			pnPanel0.setVisible(false);
			add(clientsGui);
		});
		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 13;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(btShowClientsRelations, gbcPanel0);
		pnPanel0.add(btShowClientsRelations);

		JLabel lbLabel0 = new JLabel("Servidor");
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
