package repository.gui;

import gui.List;

public class SelectableList extends List {

	public SelectableList(java.util.List<String> model, String title, ExitCallback exitCallback) {
		super(model, title, exitCallback);
	}
}
