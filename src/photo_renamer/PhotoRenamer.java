package photo_renamer;
import gui.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import backEnd.*;

/*
 * The Main program that coordinates the front and back end
 */
public class PhotoRenamer{
	private InitialFrame initialFrame;
	private ListImageFrame listImageFrame;
	private RenamingFrame renamingFrame;
	private Renamer renamer;
	
	public PhotoRenamer(){
		buildInitialFrame();
		
	}
	public void buildInitialFrame(){
		this.initialFrame = new InitialFrame(this);

		initialFrame.getFrame().setVisible(true);
	}
	
	public void buildListImageFrame(){
		File selectedDir = initialFrame.getChosenDir();
		try {
			renamer = new Renamer(selectedDir);
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
		ArrayList<File> allImageFile = renamer.getAllImageFiles(selectedDir);
		listImageFrame = new ListImageFrame(allImageFile, initialFrame,this);
		listImageFrame.getFrame().setVisible(true);
		initialFrame.getFrame().setVisible(false);
		
	}
	public void buildRenamingFrame(){
		File imgSelected = listImageFrame.getSelectedImage();
		renamingFrame = new RenamingFrame(this, imgSelected);
		renamingFrame.getCurFrame().setVisible(true);
		listImageFrame.getFrame().setVisible(false);
	}
	public InitialFrame getInitialFrame() {
		return initialFrame;
	}
	public void setInitialFrame(InitialFrame initialFrame) {
		this.initialFrame = initialFrame;
	}
	public ListImageFrame getListImageFrame() {
		return listImageFrame;
	}
	public void setListImageFrame(ListImageFrame listImageFrame) {
		this.listImageFrame = listImageFrame;
	}
	public Renamer getRenamer() {
		return renamer;
	}
	public void setRenamer(Renamer renamer) {
		this.renamer = renamer;
	}
	public RenamingFrame getRenamingFrame() {
		return renamingFrame;
	}
	public void setRenamingFrame(RenamingFrame renamingFrame) {
		this.renamingFrame = renamingFrame;
	}
	public static void main(String[] args){
		PhotoRenamer photoRenmaer = new PhotoRenamer();
	}

}
