package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/*
 * Button that lets user exit the program
 */
public class ExitButton extends JButton implements ActionListener {

	// Interesting topic about how hide / prevent default constructors
	private ExitButton() {
	};

	// The constructor we want to use
	ExitButton(String label) {
		super(label); // Class: What will leaving this out do?
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Exiting using button!");
		System.exit(0);
	}

}
