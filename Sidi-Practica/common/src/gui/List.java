package gui;

import java.awt.*;
import java.util.Optional;
import javax.swing.*;

/**
 * Java swing class to show a list of elements
 */
public class List extends JPanel {

	protected final JList<String> list;
	private java.util.List<String> model;
	private final JLabel title;
	private final JButton btnBack;
	private final ExitCallback exitCallback;
	private final ListModel<String> listModel;
	private Optional<Listener> elementClick = Optional.empty();
	private final ListModel<String> defaultListModel = new AbstractListModel<java.lang.String>() {
		@Override
		public int getSize() {
			return model.size();
		}

		@Override
		public java.lang.String getElementAt(int i) {
			return model.get(i);
		}
	};

	public List(java.util.List<String> model, String title, ExitCallback exitCallback) {
		this.model = model;
		this.exitCallback = exitCallback;
		this.list = new JList<>();
		this.title = new JLabel();
		this.title.setText(title);
		this.btnBack = new JButton();
		this.listModel = defaultListModel;
		initComponents();
	}

	public List(ListModel<String> listModel, Listener listener, String title, ExitCallback exitCallback) {
		this.exitCallback = exitCallback;
		this.list = new JList<>();
		this.title = new JLabel();
		this.title.setText(title);
		this.btnBack = new JButton();
		this.listModel = listModel;
		this.elementClick = Optional.ofNullable(listener);
		initComponents();
	}

	public List(ListModel<String> listModel, String title, ExitCallback exitCallback) {
		this.exitCallback = exitCallback;
		this.list = new JList<>();
		this.title = new JLabel();
		this.title.setText(title);
		this.btnBack = new JButton();
		this.listModel = listModel;
		this.elementClick = Optional.empty();
		initComponents();
	}

	private void initComponents() {
		setLayout(new GridBagLayout());
		//---- list ----
		list.setModel(listModel);
		list.addListSelectionListener(element -> elementClick.ifPresent(listener -> listener.onClick(list, list.getSelectedIndex())));
		add(list, new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		//---- title ----
		title.setText("");
		add(title, new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));

		//---- btnBack ----
		btnBack.setText("Volver");

		add(btnBack, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		btnBack.addActionListener(e -> exitCallback.onExit(this));
	}

	public interface ExitCallback {
		void onExit(JPanel panel);
	}

	public interface Listener {
		void onClick(JList<String> list, int index);
	}
}
