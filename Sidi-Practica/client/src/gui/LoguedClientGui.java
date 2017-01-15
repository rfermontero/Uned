package gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;

import gui.List.Listener;
import model.Metadata;
import usecase.DeleteFile;
import usecase.DownloadFile;
import usecase.GetFiles;
import usecase.UploadFile;

class LoguedClientGui extends JFrame {

	private JPanel pnPanel0;

	LoguedClientGui() {
		super("Cliente");
		setSize(new Dimension(350, 350));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pnPanel0 = new JPanel();
		GridBagLayout gbPanel0 = new GridBagLayout();
		GridBagConstraints gbcPanel0 = new GridBagConstraints();
		pnPanel0.setLayout(gbPanel0);

		JButton btShowFiles = new JButton("Mostrar ficheros");
		btShowFiles.addActionListener((ActionEvent e) -> {
			java.util.List<Metadata> files = new GetFiles().execute();
			AbstractListModel<String> listModel = new AbstractListModel<String>() {
				@Override
				public int getSize() {
					return files.size();
				}

				@Override
				public String getElementAt(int index) {
					return files.get(index).getName();
				}
			};
			List filesGui = new List(listModel, "Lista de ficheros", (JPanel comp) -> {
				files.clear();
				LoguedClientGui.this.remove(comp);
				LoguedClientGui.this.setSize(new Dimension(350, 350));
				pnPanel0.setVisible(true);
			});
			pnPanel0.setVisible(false);
			add(filesGui);
		});

		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 3;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(btShowFiles, gbcPanel0);
		pnPanel0.add(btShowFiles);

		JButton btUploadFile = new JButton("Subir fichero");
		btUploadFile.addActionListener(e -> {
			final JFileChooser fileChooser = new JFileChooser(new File("."));
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				boolean uploadFileResult = new UploadFile().execute(selectedFile);
				JOptionPane.showMessageDialog(this, uploadFileResult ? "Fichero subido" : "Error subiendo fichero");
				fileChooser.cancelSelection();
				System.out.println("Uploading file " + selectedFile.getAbsolutePath());
				pnPanel0.setVisible(true);
				LoguedClientGui.this.validate();
				LoguedClientGui.this.repaint();
			}
		});
		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 6;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(btUploadFile, gbcPanel0);
		pnPanel0.add(btUploadFile);

		JButton btDownloadFile = new JButton("Bajar fichero");
		btDownloadFile.addActionListener((ActionEvent e) -> {
			java.util.List<Metadata> files = new GetFiles().execute();
			AbstractListModel<String> listModel = new AbstractListModel<String>() {
				@Override
				public int getSize() {
					return files.size();
				}

				@Override
				public String getElementAt(int index) {
					return files.get(index).getName();
				}
			};
			Listener fileClickListener = (list, index) -> {
				boolean result = new DownloadFile().execute(files.get(index).getName());
				JOptionPane.showMessageDialog(this, result ? "Fichero descargado" : "Error descargando fichero");
				list.setVisible(false);
				LoguedClientGui.this.remove(list);
				LoguedClientGui.this.setSize(new Dimension(350, 350));
				pnPanel0.setVisible(true);
			};
			List filesGui = new List(listModel, fileClickListener, "Lista de ficheros", (JPanel comp) -> {
				LoguedClientGui.this.remove(comp);
				LoguedClientGui.this.setSize(new Dimension(350, 350));
				pnPanel0.setVisible(true);
			});
			pnPanel0.setVisible(false);
			add(filesGui);
		});
		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 9;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(btDownloadFile, gbcPanel0);
		pnPanel0.add(btDownloadFile);

		JButton btDeleteFile = new JButton("Borrar fichero");
		btDeleteFile.addActionListener((ActionEvent e) -> {
			java.util.List<Metadata> files = new GetFiles().execute();
			AbstractListModel<String> listModel = new AbstractListModel<String>() {
				@Override
				public int getSize() {
					return files.size();
				}

				@Override
				public String getElementAt(int index) {
					return files.get(index).getName();
				}
			};
			Listener fileClickListener = (list, index) -> {
				boolean result = new DeleteFile().execute(files.get(index).getName());
				JOptionPane.showMessageDialog(this, result ? "Fichero eliminado" : "Error eliminando fichero");
				list.setVisible(false);
				pnPanel0.setVisible(true);
				LoguedClientGui.this.remove(list);
				LoguedClientGui.this.setSize(new Dimension(350, 350));
				LoguedClientGui.this.validate();
				LoguedClientGui.this.repaint();
			};
			List filesGui = new List(listModel, fileClickListener, "Lista de ficheros", (JPanel comp) -> {
				LoguedClientGui.this.remove(comp);
				LoguedClientGui.this.setSize(new Dimension(350, 350));
				pnPanel0.setVisible(true);
			});
			pnPanel0.setVisible(false);
			add(filesGui);

		});
		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 13;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(btDeleteFile, gbcPanel0);
		pnPanel0.add(btDeleteFile);

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
