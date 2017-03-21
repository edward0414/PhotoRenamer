package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import backEnd.Renamer;

/*
 * to be used inside the tag panel
 * when clicked, tag the file 
 */
public class TagButton extends JButton implements ActionListener {

	// Interesting topic about how hide / prevent default constructors
	private TagButton() {
	};
	private Renamer renamer;
	private ArrayList<String> tags;
	private File img;
	private RenamingFrame frame;
	public static final String label = "Tag Selected";
	// The constructor we want to use
	TagButton(String label,RenamingFrame frame) {
		super(label); // Class: What will leaving this out do?
		this.addActionListener(this);
		this.renamer = frame.getRenamer();
		this.tags = new ArrayList<String>();
		this.img = frame.getImgNode().getFile();
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(tags.toString());
		try {
			renamer.tag(img, tags);
		} catch (IOException e1) {
			
		}
		frame.updateImagePanel();
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

}
