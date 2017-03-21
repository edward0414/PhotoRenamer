package gui;
import backEnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

/*
 * When clicked, save the changes to record.
 */
public class SaveButton extends JButton implements ActionListener {
	// Interesting topic about how hide / prevent default constructors
		private SaveButton() {
		};
		private FileNodeManager fm;

		// The constructor we want to use
		SaveButton(String label, FileNodeManager fm) {
			super(label); 
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			try {
				fm.saveToFile(fm.getLogPath());
			} catch (IOException e1) {
			
			}
		}
}
