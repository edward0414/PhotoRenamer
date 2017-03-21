package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * Creates a new Tag Panel when clicked
 */
public class CallTagButton extends JButton implements ActionListener {

	// Interesting topic about how hide / prevent default constructors
	private CallTagButton() {
	};
	private RenamingFrame frame;
	public static final String label = "Tag";

	// The constructor we want to use
	CallTagButton(String label,RenamingFrame frame) {
		super(label); // Class: What will leaving this out do?
		this.addActionListener(this);
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		frame.buildTagPanel();
	}


}
