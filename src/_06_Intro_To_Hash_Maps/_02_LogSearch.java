package _06_Intro_To_Hash_Maps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class _02_LogSearch implements ActionListener {

	// Crate a HashMap of Integers for the keys and Strings for the values.
	// Create a GUI with three buttons.
	HashMap<Integer, String> values = new HashMap<Integer, String>();
	// Button 1: Add Entry
	// When this button is clicked, use an input dialog to ask the user
	// to enter an ID number.
	// After an ID is entered, use another input dialog to ask the user
	// to enter a name. Add this information as a new entry to your
	// HashMap.
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton add = new JButton("Add Entry");
	JButton search = new JButton("Search by ID");
	JButton view = new JButton("View List");
	JButton remove = new JButton("Remove Entry");
	int found = 0;
	int removed = 0;
	int test;
	int count = 0;
	String[] name = new String[10];
	int[] id = new int[10];

	// Button 2: Search by ID
	// When this button is clicked, use an input dialog to ask the user
	// to enter an ID number.
	// If that ID exists, display that name to the user.
	// Otherwise, tell the user that that entry does not exist.
	//
	// Button 3: View List
	// When this button is clicked, display the entire list in a message
	// dialog in the following format:
	// ID: 123 Name: Harry Howard
	// ID: 245 Name: Polly Powers
	// ID: 433 Name: Oliver Ortega
	// etc...
	//
	// When this is complete, add a fourth button to your window.
	// Button 4: Remove Entry
	// When this button is clicked, prompt the user to enter an ID using
	// an input dialog.
	// If this ID exists in the HashMap, remove it. Otherwise, notify the
	// user that the ID is not in the list.
	void setup() {
		frame.setVisible(true);
		frame.setSize(400, 200);
		frame.add(panel);
		panel.add(add);
		panel.add(search);
		panel.add(view);
		panel.add(remove);

		add.addActionListener(this);
		search.addActionListener(this);
		view.addActionListener(this);
		remove.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getSource().equals(add)) {
			String id2 = JOptionPane.showInputDialog("Enter an ID");
			String name2 = JOptionPane.showInputDialog("Enter your name");
			test = Integer.parseInt(id2);
			id[count] = test;
			name[count] = name2;
			values.put(test, name2);
			count++;
		}

		if (arg0.getSource().equals(search)) {
			String s = JOptionPane.showInputDialog("Search an id");
			int e = Integer.parseInt(s);
			for (int i = 0; i < id.length; i++) {
				if (e == id[i]) {
					JOptionPane.showMessageDialog(null, "" + name[i]);
					found = 1;
				}
			}
			if (found == 0) {
				JOptionPane.showMessageDialog(null, "ID does not exist");
			}
		}

		if (arg0.getSource().equals(view)) {
			for (int i = 0; i < id.length; i++) {

				System.out.println("ID: " + id[i] + ", Name: " + name[i]);

			}
		}

		if (arg0.getSource().equals(remove)) {
			String f = JOptionPane.showInputDialog("Enter an id you want to remove");
			int x = Integer.parseInt(f);
			for (int i = 0; i < id.length; i++) {
				if (x == id[i]) {
					values.remove(id[i]);
					removed = 1;
					JOptionPane.showMessageDialog(null, "ID has been removed");
				}
			}
			if (removed == 0) {
				JOptionPane.showMessageDialog(null, "That ID does not exist");
			}

		}

	}

}
