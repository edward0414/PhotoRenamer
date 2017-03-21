package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backEnd.Renamer;

/*
 * To be used in the untag panel
 * when clicked, untag file
 */
public class UntagButton extends JButton implements ActionListener {

	// Interesting topic about how hide / prevent default constructors
	private UntagButton() {
	};
	private RenamingFrame frame;
	private ArrayList<String> untagList;
	public static final String label = "UnTag Selected";

	// The constructor we want to use
	UntagButton(String label,RenamingFrame frame) {
		super(label); // Class: What will leaving this out do?
		this.addActionListener(this);
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			File img = frame.getImgNode().getFile();
			frame.getRenamer().untag(img,untagList);;
		} catch (IOException e1) {
			
		}
		frame.updateImagePanel();
		
		JPanel temp = new JPanel();
		temp.add(new JLabel("Image Doesn't Have Any Tags Yet"));
		frame.changeEastPanel(temp);


	}

	public ArrayList<String> getUntagList() {
		return untagList;
	}

	public void setUntagList(ArrayList<String> untagList) {
		this.untagList = untagList;
	}

}
