package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import photo_renamer.PhotoRenamer;

/*
 * Button that lets User select an Image
 */
public class ChooseImageButton extends JButton implements ActionListener, ListSelectionListener {

	// Interesting topic about how hide / prevent default constructors
	private ChooseImageButton() {
	};
	private ListImageFrame curFrame;
	private PhotoRenamer photoRenamer;

	// The constructor we want to use
	ChooseImageButton(String label, ListImageFrame curFrame,PhotoRenamer photoRenamer) {
		super(label); // Class: What will leaving this out do?
		this.curFrame = curFrame;
		this.addActionListener(this);
		this.photoRenamer = photoRenamer;
	}

	public void actionPerformed(ActionEvent e) {
		photoRenamer.buildRenamingFrame();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<File> ls = (JList<File>)e.getSource();
		curFrame.setSelectedImage((File) ls.getSelectedValue());
	}

}
