package server.gui;

import java.awt.*;
import javax.swing.*;

public class Gui extends JFrame {
	public Gui() throws HeadlessException {
		super("Servidor");
		setSize(new Dimension(400, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
