package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextFieldPanel extends JPanel implements ActionListener {
	private JTextField textField;
	private TagButton button;
	
	public TextFieldPanel(TagButton button){
		super();
		this.textField = new JTextField(20);
		textField.addActionListener(this);
		this.button = button;
		this.add(textField);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText();
		button.getTags().add(text);
	}

}
