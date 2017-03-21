package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * When clicked, creates an UntagPanel
 */
public class CallUntagButton extends JButton implements ActionListener {

	// Interesting topic about how hide / prevent default constructors
	private CallUntagButton() {
	};
	private RenamingFrame frame;
	public static final String label = "UnTag";

	// The constructor we want to use
	CallUntagButton(String label,RenamingFrame frame) {
		super(label); // Class: What will leaving this out do?
		this.addActionListener(this);
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (frame.getRenamer().getManager().findFromRecord(frame.getImgNode().getFile())
					.getUsedTags().size() == 0){
					JPanel temp = new JPanel();
					temp.add(new JLabel("Image Doesn't Have Any Tags Yet"));
					frame.changeEastPanel(temp);
				}else{
					try {
						frame.buildUnTagPanel();
					} catch (IOException e1) {
						
					}
				}
			
		} catch (IOException e1) {

		}
	}


}
