package gui;
import photo_renamer.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;


/*
 * Button that lets user choose directory to use PhotoRenamer on
 */
public class ChooseDirectoryButton extends JButton implements ActionListener {
	
	// Interesting topic about how hide / prevent default constructors
	private ChooseDirectoryButton() {
	};
	private InitialFrame frame;
	private PhotoRenamer photoRenamer;

	// The constructor we want to use
	ChooseDirectoryButton(String label,InitialFrame initialFrame,PhotoRenamer photoRenamer) {
		super(label); // Class: What will leaving this out do?
		this.addActionListener(this);
		this.frame = initialFrame;
		this.photoRenamer = photoRenamer;
	}

	public void actionPerformed(ActionEvent e) {
		 JFileChooser chooser = new JFileChooser();
		 chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         int returnVal = chooser.showOpenDialog(frame.getFrame());
         if (returnVal == JFileChooser.APPROVE_OPTION) {
        	 frame.setChosenDir(chooser.getSelectedFile());
        	 photoRenamer.buildListImageFrame();
         }
	}
}
