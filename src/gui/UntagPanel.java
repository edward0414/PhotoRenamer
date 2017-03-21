package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * A panel consisting of a checkboxpanel of cur tags and an untagButton
 */
public class UntagPanel extends JPanel {
	private ArrayList<String> curTag;
	private UntagButton button;
	private JPanel panel;
	
	public UntagPanel(ArrayList<String> curTags, UntagButton untagButton){
		this.curTag = curTags;
		this.button = untagButton;
		panel = new JPanel(new BorderLayout());
		buildPanel();
		
	}
	public void buildPanel(){
		JLabel newLabel = new JLabel("Current Tags");
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.add(newLabel,BorderLayout.NORTH);
		CheckBoxPanel tagsCkBox = new CheckBoxPanel(button);
		for(String s: curTag){
				JCheckBox temp = new JCheckBox(s);
				tagsCkBox.add(temp);
			}
		
		northPanel.add(tagsCkBox,BorderLayout.CENTER);
		panel.add(northPanel, BorderLayout.NORTH);
		panel.add(button, BorderLayout.SOUTH);
		
	}

	
	public UntagButton getBotton() {
		return button;
	}
	public void setBotton(UntagButton botton) {
		this.button = botton;
	}
	public ArrayList<String> getCurTag() {
		return curTag;
	}
	public void setCurTag(ArrayList<String> curTag) {
		this.curTag = curTag;
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

}
