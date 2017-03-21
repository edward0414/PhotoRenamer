package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import photo_renamer.PhotoRenamer;



/*
 * Initial frame of program, where user chooses a directory
 */

/*
 * Manages the initial frame of the program, consists of a button to choose Directory.
 */
public class InitialFrame implements WindowListener{
	
	private File chosenDir;
	private JFrame frame;
	private PhotoRenamer photoRenamer;
	
	public InitialFrame(PhotoRenamer photoRenamer){
		this.chosenDir = null;
		this.photoRenamer = photoRenamer;
		buildFrame();
	}
	public void buildFrame(){
		frame = new JFrame();
		frame.addWindowListener(this);
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		JLabel directoryLabel = new JLabel("Select a directory");
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.add(directoryLabel,BorderLayout.CENTER);
		frame.add(centerPanel,BorderLayout.CENTER);
		
		ChooseDirectoryButton chooseButton = new ChooseDirectoryButton
				("Choose Directory", this, photoRenamer);
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(chooseButton,BorderLayout.SOUTH);
		frame.add(southPanel,BorderLayout.SOUTH);
		
		frame.pack();
	}
	
	public File getChosenDir() {
		return chosenDir;
	}
	public void setChosenDir(File chosenDir) {
		this.chosenDir = chosenDir;
	}
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public static void main(String args[]){
		//InitialFrame i = new InitialFrame();
		//i.getFrame().setVisible(true);
	}
	@Override
	public void windowOpened(WindowEvent e) {
	
	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		
		
	}
	@Override
	public void windowIconified(WindowEvent e) {

		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {

	}
	@Override
	public void windowActivated(WindowEvent e) {

		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {

		
	}
}
