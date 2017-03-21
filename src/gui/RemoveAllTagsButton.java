package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

/*
 * to be used inside the revertpanel,
 * when clicked, revert to a previous date
 */
public class RemoveAllTagsButton extends JButton implements ActionListener {

	// Interesting topic about how hide / prevent default constructors
	private RemoveAllTagsButton() {
	};
	public static String label = "Remove All Tags";
	private RenamingFrame frame;

	// The constructor we want to use
	RemoveAllTagsButton(String label,RenamingFrame frame) {
		super(label); // Class: What will leaving this out do?
		this.addActionListener(this);
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		
		File img = frame.getImgNode().getFile();
		try {
			frame.getRenamer().removeAllTags(img);
		} catch (IOException e1) {

		}
		frame.updateImagePanel();

	}

}
