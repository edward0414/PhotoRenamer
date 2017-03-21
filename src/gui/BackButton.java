package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BackButton extends JButton implements ActionListener {

	// Interesting topic about how hide / prevent default constructors
	private BackButton() {
	};
	private JFrame curFrame;
	private JFrame prevFrame;
	// The constructor we want to use
	BackButton(String label,JFrame cur, JFrame prev) {
		super(label); // Class: What will leaving this out do?
		this.addActionListener(this);
		curFrame = cur;
		prevFrame = prev;
	}

	public void actionPerformed(ActionEvent e) {
		//curFrame.setVisible(false);
		curFrame.dispatchEvent(new WindowEvent(curFrame, WindowEvent.WINDOW_CLOSING));
		prevFrame.setVisible(true);
	}

}
