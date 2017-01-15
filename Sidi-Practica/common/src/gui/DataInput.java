package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class DataInput extends JDialog {

	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JButton btnLogin;
	private JButton btnCancel;

	public DataInput(OnClick onclickListener, Frame parent) {
		super(parent, "", true);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.HORIZONTAL;

		lbUsername = new JLabel("Nombre: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(lbUsername, cs);

		tfUsername = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(tfUsername, cs);

		lbPassword = new JLabel("Contraseña: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(lbPassword, cs);

		pfPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(pfPassword, cs);
		panel.setBorder(new LineBorder(Color.GRAY));

		btnLogin = new JButton("Aceptar");

		btnLogin.addActionListener(e -> onclickListener.onAccept(getUsername(), getPassword(), this));
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> dispose());
		JPanel bp = new JPanel();
		bp.add(btnLogin);
		bp.add(btnCancel);

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	private String getUsername() {
		return tfUsername.getText().trim();
	}

	private String getPassword() {
		return new String(pfPassword.getPassword());
	}

	public interface OnClick {
		void onAccept(String name, String password, JDialog dialog);
	}
}