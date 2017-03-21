package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TagPanel {
	private JPanel panel;
	private ArrayList<String> avaTags;
	private TagButton tagButton;
	
	public TagPanel(ArrayList<String> avaTags, TagButton tagButton){
		this.panel = new JPanel(new BorderLayout());
		this.avaTags = avaTags;
		this.tagButton = tagButton;
		buildPanel();
	}
	
	
	public void buildPanel(){
		JLabel newLabel = new JLabel("Avaliable Tags");
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.add(newLabel,BorderLayout.NORTH);
		CheckBoxPanel tagsCkBox = new CheckBoxPanel(tagButton);
		for(String s: avaTags){
			JCheckBox temp = new JCheckBox(s);
			tagsCkBox.add(temp);
		}
		TextFieldPanel textFieldPanel = new TextFieldPanel(tagButton);
		northPanel.add(tagsCkBox,BorderLayout.CENTER);
		panel.add(northPanel,BorderLayout.NORTH);
		panel.add(textFieldPanel, BorderLayout.CENTER);
		panel.add(tagButton, BorderLayout.SOUTH);
		
	}


	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	

}
