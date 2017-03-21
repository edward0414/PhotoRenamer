package gui;

import java.awt.BorderLayout;
import photo_renamer.*;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import backEnd.*;
/*
 * Third Frame of the program, where the user can do things to the selected image file.
 */
public class RenamingFrame{
	private JFrame curFrame;
	private JFrame prevFrame;
	private PhotoRenamer photoRenamer;
	private JPanel  eastPanelHolder;
	private JPanel imagePanel;
	private ImageFileNode imgNode;
	
	public RenamingFrame(PhotoRenamer photoRenamer, File img){
		this.photoRenamer = photoRenamer;
		this.prevFrame = photoRenamer.getListImageFrame().getFrame();
		this.eastPanelHolder = new JPanel();
		this.imagePanel = new JPanel();
		try {
			this.imgNode = photoRenamer.getRenamer().getManager().findFromRecord(img);
			System.out.println(imgNode.getName());
		} catch (IOException e) {

		}
		
		buildFrame();
	}
	

	public void buildFrame(){
		curFrame = new JFrame();
		updateImagePanel();
		JPanel northPanel = buildNorthPanel(photoRenamer.getRenamer().getManager());
		JPanel functionsPanel = buildFunctionsPanel();
		
		curFrame.add(imagePanel, BorderLayout.CENTER);
		curFrame.add(northPanel, BorderLayout.NORTH);
		curFrame.add(functionsPanel, BorderLayout.SOUTH);
		curFrame.add(eastPanelHolder, BorderLayout.EAST);
		curFrame.pack();
	}
	
	
	/*
	 * Builds the call panel that host the buttons that call different functions
	 */
	public JPanel buildFunctionsPanel(){
		
		JPanel functionsPanel = new JPanel();
		CallTagButton callTagButton = new CallTagButton(CallTagButton.label,this);
		functionsPanel.add(callTagButton);
		CallUntagButton callUntagButton = new CallUntagButton(CallUntagButton.label,this);
		functionsPanel.add(callUntagButton);
		ShowAllNameButton callPrevNameButton = new ShowAllNameButton(ShowAllNameButton.label,this);
		functionsPanel.add(callPrevNameButton);
		RemoveAllTagsButton removeAllTagsButton = new RemoveAllTagsButton(RemoveAllTagsButton.label,this);
		functionsPanel.add(removeAllTagsButton);
		CallRevertButton callRevertButton = new CallRevertButton(CallRevertButton.label, this);
		functionsPanel.add(callRevertButton);
		
		return functionsPanel;
	}
	
	public void updateImagePanel(){
		
		imagePanel.removeAll();
		BufferedImage buffImg= null;
		try {
			buffImg = ImageIO.read(imgNode.getFile());
		} catch (IOException e) {
			System.out.println("no file");
		}
		
		String  name = imgNode.getName();
		Image newImg = buffImg.getScaledInstance(buffImg.getHeight() / 10,buffImg.getWidth() / 10
				, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newImg);
		
		JLabel imageLabel = new JLabel(name, icon, JLabel.CENTER);
		imageLabel.setHorizontalTextPosition(JLabel.CENTER);
		imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
		imagePanel.add(imageLabel, BorderLayout.CENTER);
		imagePanel.revalidate();
		
	}
	/*
	 * Changes the EastPanel of the JFrame to panel.
	 */
	public void changeEastPanel(JPanel panel){
		eastPanelHolder.removeAll();
		eastPanelHolder.add(panel);
		curFrame.revalidate();
		curFrame.pack();
	}
	
	public void buildTagPanel(){
		TagButton tagButton = new TagButton(TagButton.label,this);
		ArrayList<String> tags = photoRenamer.getRenamer().getManager().getAllAvaTags();
		JPanel tagPanel = new TagPanel(tags, tagButton).getPanel();
		changeEastPanel(tagPanel);
		
	}
	
	public void buildUnTagPanel() throws IOException{
		UntagButton untagButton = new UntagButton(UntagButton.label,this);
		ArrayList<String> untagList = photoRenamer.getRenamer().
				getManager().findFromRecord(imgNode.getFile()).getUsedTags();
		JPanel untagPanel = new UntagPanel(untagList, untagButton).getPanel();
		changeEastPanel(untagPanel);
	}
	/*
	 * Builds the north panel that hosts back and save button
	 */
	public JPanel buildNorthPanel(FileNodeManager fm){
		BackButton backButton = new BackButton("Back", curFrame, prevFrame);
		SaveButton saveButton = new SaveButton("Save",fm);
		JPanel northPanel = new JPanel(new BorderLayout());
		
		northPanel.add(backButton, BorderLayout.WEST);
		northPanel.add(saveButton, BorderLayout.EAST);
		return northPanel;
	}
	
	public void buildRevertPanel(JList prevNameList){
		RevertButton revertButton = new RevertButton(RevertButton.label, this);
		prevNameList.addListSelectionListener(revertButton);
		JPanel revertPanel = new RevertPanel(prevNameList, revertButton).getPanel();
		changeEastPanel(revertPanel);
	}
	
	public JFrame getCurFrame() {
		return curFrame;
	}


	public void setCurFrame(JFrame curFrame) {
		this.curFrame = curFrame;
	}


	public Renamer getRenamer() {
		return photoRenamer.getRenamer();
	}



	public ImageFileNode getImgNode() {
		return imgNode;
	}


	public void setImgNode(ImageFileNode imgNode) {
		this.imgNode = imgNode;
	}


	public static void main(String[] args) throws ClassNotFoundException, IOException{
		//File f1 = new File("/Users/gordonlin/Desktop/A2_Test_Folder/Goldman97.jpg");
		//File f2 = new File("/Users/gordonlin/Desktop/A2_Test_Folder");
		//Renamer rm = new Renamer(f2);
		//JFrame i = new JFrame();
		//RenamingFrame r = new RenamingFrame(f1,i,rm);
		ArrayList<String> a2 = new ArrayList<String>();
		a2.add("Jane");
		a2.add("Brit");
		//r.getCurFrame().setVisible(true);
	}


}
