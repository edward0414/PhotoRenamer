package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * to be used inside the revertpanel,
 * when clicked, revert to a previous date
 */
public class RevertButton extends JButton implements ActionListener, ListSelectionListener {

	// Interesting topic about how hide / prevent default constructors
	private RevertButton() {
	};
	private String selectedDate;
	private RenamingFrame frame;
	public final static String label = "Revert to Selected Date";

	// The constructor we want to use
	RevertButton(String label, RenamingFrame frame) {
		super(label); // Class: What will leaving this out do?
		this.addActionListener(this);
		this.frame = frame;
		this.selectedDate = "";
	}

	public void actionPerformed(ActionEvent e) {
		File img = frame.getImgNode().getFile();
		try {
			frame.getRenamer().revertToDate(img, selectedDate);
		} catch (IOException e1) {
		}
		frame.updateImagePanel();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList ls = (JList)e.getSource();
		String s = (String) ls.getSelectedValue();
		selectedDate = s.split(" : ")[0];
		
	}
	public static void main(String args[]){
		String s = "12345 : jojoj";
		String s1 = s.split(" : ")[0];
		//System.out.println(s1);
	}
}
