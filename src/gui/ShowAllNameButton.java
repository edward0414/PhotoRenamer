package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/*
 * When clicked, creates shows all the names img has had
 */
public class ShowAllNameButton extends JButton implements ActionListener {

	// Interesting topic about how hide / prevent default constructors
	private ShowAllNameButton() {
	};
	private RenamingFrame frame;
	public static final String label = "View Previous Names";

	// The constructor we want to use
	ShowAllNameButton(String label,RenamingFrame frame) {
		super(label); // Class: What will leaving this out do?
		this.addActionListener(this);
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String> prevNames = new ArrayList<String>();
		try {
			prevNames = frame.getRenamer().viewAllNames(frame.getImgNode().getFile());
		} catch (IOException e1) {
		}
		String[] temp = new String[prevNames.size()];
		JList<String> prevNameList = new JList<String>(prevNames.toArray(temp));
		JPanel prevNamePanel = new JPanel();
		prevNamePanel.add(prevNameList);
		
		frame.changeEastPanel(prevNamePanel);
	}
}
