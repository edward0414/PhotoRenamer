package gui;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/*
 * A CheckBoxPanel
 */
public class CheckBoxPanel extends JPanel implements ActionListener {
	private ArrayList<JCheckBox> myboxes;
	private ArrayList<String> selectedTags;
	private JButton button;
	public CheckBoxPanel(JButton button){
		super();
		this.selectedTags =  new ArrayList<String>();
		this.myboxes = new ArrayList<JCheckBox>();
		this.button = button;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#add(java.awt.Component)
	 */
	@Override
	public Component add(Component comp) {
		// TODO Auto-generated method stub
		Component superRes = super.add(comp);
		myboxes.add((JCheckBox) comp);
		((JCheckBox) comp).addActionListener(this);
		return superRes;
	}

	public List<String> getSelected() {
		// Iterate over myboxes and return the selected ones.
		return null;
	}

	public void actionPerformed(ActionEvent e) {
		JCheckBox comp = (JCheckBox) e.getSource();
		if (comp.isSelected()){
			selectedTags.add(comp.getText());
			System.out.println("Wow I selected a " + comp.getText() + " box!");
			}
		if(button.getText().equals(TagButton.label)){
			 ((TagButton) button).setTags(selectedTags);
		}else if(button.getText().equals(UntagButton.label)){
			((UntagButton) button).setUntagList(selectedTags);
		}
	}
}
