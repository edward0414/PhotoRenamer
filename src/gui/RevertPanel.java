package gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * A panel that contains a checkboxpanel with all previous names and a revertButton
 */
public class RevertPanel {
	private JPanel panel;
	private JList listOfPrev;
	private RevertButton button;
	
	public RevertPanel(JList listOfPrev, RevertButton button){
		
		this.panel = new JPanel(new BorderLayout());
		this.listOfPrev = listOfPrev;
		this.button = button;
		buildPanel();
	}
	
	public void buildPanel(){
		JLabel newLabel = new JLabel("Previous Versions");
		panel.add(newLabel,BorderLayout.NORTH);
		JPanel listPanel = buildJlistPanel(listOfPrev);
		panel.add(listPanel, BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
	}
	
	public JPanel buildJlistPanel(JList list){
		
		JScrollPane listScroller = new JScrollPane(list);
        listScroller.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
        JPanel jlistPanel = new JPanel();
        jlistPanel.add(listScroller);
        return jlistPanel;
		
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
