package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import photo_renamer.PhotoRenamer;

/*
 * Second frame of the program, where the all the images are shown
 * needs to have a JList of ImageFiles, that is scrollable and a back button and a choose image button.
 */
public class ListImageFrame {
	private JFrame frame;
	private ArrayList<File> allImageFiles;
	private InitialFrame prev;
	public JList<File> list;
	private File selectedImage;
	private PhotoRenamer photoRenamer;
	
	public ListImageFrame(ArrayList<File> allImageFiles, InitialFrame prev, PhotoRenamer photoRenamer){
		
		this.selectedImage = null;
		this.list = buildJList(allImageFiles);
		this.prev = prev;
		this.allImageFiles = allImageFiles;
		this.photoRenamer = photoRenamer;
		buildFrame();
	}
	
	public void buildFrame(){
		frame = new JFrame();
		JPanel northPanel = new JPanel();
		JLabel label = new JLabel("Choose an Image File");
		northPanel.add(label);
		frame.add(northPanel, BorderLayout.NORTH);
		
		JPanel imageListPanel = buildJlistPanel(list);
		frame.add(imageListPanel, BorderLayout.CENTER);
		
		JPanel buttonsPanel = buildButtonsPanel();
		frame.add(buttonsPanel,BorderLayout.SOUTH);
		
		frame.pack();
	}
	
	public JPanel buildJlistPanel(JList<File> list){
		
		JScrollPane listScroller = new JScrollPane(list);
        listScroller.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
        JPanel jlistPanel = new JPanel();
        jlistPanel.add(listScroller);
        return jlistPanel;
		
	}
	
	public JList<File> buildJList(ArrayList<File> a){
		File[] temp = new File[a.size()];
		JList<File> j = new JList<File>(a.toArray(temp));
		j.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		j.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		j.setVisibleRowCount(-1);
		return j;

	}
	public JPanel buildButtonsPanel(){
		JPanel buttonsPanel = new JPanel(new BorderLayout());
		
		ChooseImageButton chooseButton = new ChooseImageButton("Select File", this, photoRenamer);
		list.addListSelectionListener(chooseButton);
		buttonsPanel.add(chooseButton,  BorderLayout.EAST);
		
		BackButton backButton = new BackButton("Back",frame,prev.getFrame());
		buttonsPanel.add(backButton, BorderLayout.WEST);
		
		return buttonsPanel;
		
		
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public File getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(File selectedImage) {
		this.selectedImage = selectedImage;
	}

	public static void main(String[] args){
		File f1 = new File("/Users/gordonlin/Desktop/A2_Test_Folder/Goldman,95.jpg");
		File f2 = new File("/Users/gordonlin/Desktop/A2_Test_Folder/Goldman,96.jpg");
		ArrayList<File> a = new ArrayList();
		a.add(f1);
		a.add(f2);
		//InitialFrame i = new InitialFrame();
		//ListImageFrame l = new ListImageFrame(a, i);
		//l.getFrame().setVisible(true);
		
	}


}
