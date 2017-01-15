package repository.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.server.UID;
import javax.swing.*;

import gui.List;
import gui.List.Listener;
import repository.usecases.GetClientWithUids;
import repository.usecases.GetClientWithUids.ClientUid;
import repository.usecases.GetClients;
import repository.usecases.GetFilesForUID;

class LoguedRepositorygui extends JFrame {

	private JPanel pnPanel0;

	LoguedRepositorygui() throws HeadlessException {
		super("Repositorio");
		setSize(new Dimension(350, 350));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pnPanel0 = new JPanel();
		GridBagLayout gbPanel0 = new GridBagLayout();
		GridBagConstraints gbcPanel0 = new GridBagConstraints();
		pnPanel0.setLayout(gbPanel0);

		JButton btShowClientsButton = new JButton("Listar clientes");
		btShowClientsButton.addActionListener((ActionEvent e) -> {
			java.util.List<String> clients = new GetClients().execute();
			List clientsGui = new List(clients, "Lista de clientes", (comp) -> {
				LoguedRepositorygui.this.remove(comp);
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

		JButton btShowRepositoriesButton = new JButton("Listar ficheros de cliente");
		btShowRepositoriesButton.addActionListener((ActionEvent e) -> {
			java.util.List<ClientUid> relations = new GetClientWithUids().execute();
			AbstractListModel<String> listModel = new AbstractListModel<String>() {
				@Override
				public int getSize() {
					return relations.size();
				}

				@Override
				public String getElementAt(int index) {
					return relations.get(index).getName();
				}
			};

			final Component[] component = new Component[1];
			Listener listener = (JList<String> list, int element) -> {
				UID uid = relations.get(element).getUid();
				LoguedRepositorygui.this.remove(list);
				java.util.List<String> files = new GetFilesForUID().execute(uid);
				List filesGui = new List(files, "Lista de ficheros de cliente", (JPanel comp) -> {
					LoguedRepositorygui.this.remove(comp);
					pnPanel0.setVisible(true);
				});
				pnPanel0.add(filesGui);
				filesGui.setVisible(true);
			};

			List clientsGui = new List(listModel, listener, "Lista de ficheros de cliente", panel -> {
				LoguedRepositorygui.this.remove(panel);
				pnPanel0.setVisible(true);
			});

			clientsGui.setVisible(true);
			pnPanel0.setVisible(false);
			component[0] = clientsGui;
			add(component[0]);
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

		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 13;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;

		JLabel lbLabel0 = new JLabel("Repositorio");
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
