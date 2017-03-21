package gui;

/*
 * When clicked, creates a RevertPanel on WEST part of Frame
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

public class CallRevertButton extends JButton implements ActionListener {

	// Interesting topic about how hide / prevent default constructors
	private CallRevertButton() {
	};
	private RenamingFrame frame;
	public static final String label = "Revert to Previous Version";

	// The constructor we want to use
	CallRevertButton(String label,RenamingFrame frame) {
		super(label); // Class: What will leaving this out do?
		this.addActionListener(this);
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		ArrayList<ArrayList<String>> temp = frame.getImgNode().viewAllNames();
		ArrayList<String> prevArrayList = new ArrayList<String>();
		String[] holder = new String[prevArrayList.size()];
		for(ArrayList<String> a: temp){
			String s = a.get(0) + " : " +  a.get(1);
			prevArrayList.add(s);
		}
		JList prevNamesList = new JList(prevArrayList.toArray(holder));
		frame.buildRevertPanel(prevNamesList);
	}


}
