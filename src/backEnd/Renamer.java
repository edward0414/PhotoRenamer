package backEnd;

import java.io.File;


import java.io.IOException;
import java.util.ArrayList;

/*
 *  A photoRenamer that can show all the Image Files in a directory, tag/untag any Image Files.
 *  Can revert an Image File to a name it previously had.
 */
public class Renamer {
	
	// the manager associated with this PhotoRenamer
	private FileNodeManager manager;
	private File dir;

	/*
	 * Initiates a new PhotoRenamer with Directory dir and creates a FileNodeManger for that directory.
	 * @param dir
	 * 			directory for the PhotoRenamer to operate
	 */
	public Renamer(File dir) throws ClassNotFoundException, IOException{
		
		manager = new FileNodeManager(dir);
		manager.saveToFile(manager.getLogPath());
		this.dir = dir;
	}
	
	
	/*
	 *  Add tags to a given File f in this directory.
	 *  @param f
	 *  		File to tag
	 *  @param tags
	 *  		List of tags for the File
	 */
	public void tag(File f,ArrayList<String> tags) throws IOException{
		//adds tags to available tags if they aren't already there.
		for(String t:tags){
			if(! manager.getAllAvaTags().contains(t)){
				manager.getAllAvaTags().add(t);
				}
			}
		
		//find the ImageFileNode associated with f and tag it.
		manager.findFromRecord(f).addTag(tags);
		//save the changes 
		manager.saveToFile(manager.getLogPath());
	}
	
	/*
	 * Removes tags from a given File f.
	 * @param f
	 * 			File to remove tags
	 * @param tags
	 * 			tags to remove
	 */
	public void untag(File f, ArrayList<String> tags) throws IOException{
		
		//find the ImageFileNode associated with f and remove tags from it.
		manager.findFromRecord(f).removeTag(tags);
		//save the changes.
		manager.saveToFile(manager.getLogPath());
	}	
	
	/*
	 * Reverts File f back to a previous time.
	 * @param f
	 * 			File to revert.
	 * @param date
	 * 			time to revert to.
	 */
	public void revertToDate(File f, String date) throws IOException{
		
		manager.findFromRecord(f).revertToDate(date);
		manager.saveToFile(manager.getLogPath());
	}
	
	/*
	 * Returns a String of all the names File f has had. In the format of "time name".
	 * @param f
	 * 			File to view all names of.
	 */
	public ArrayList<String> viewAllNames(File f) throws IOException{
		
		ImageFileNode nodeToView = manager.findFromRecord(f);
		ArrayList<String> rArray = new ArrayList<String>();
		ArrayList<ArrayList<String>> allNamesArray = nodeToView.viewAllNames();
		// formatting into a String
		for(ArrayList<String> a: allNamesArray){
			rArray.add(a.get(1));
		}
		
		return rArray;
	}
	
	/*
	 * Returns the names of all ImageFileNodes contained in directory dir.
	 * @param dir
	 * 			directory to view name
	 */
	public ArrayList<File> getAllImageFiles(File dir){
		
		ArrayList<File> allImageFiles = new ArrayList<File>();
		File[] content = dir.listFiles();
		
		for(File f: content){
			if(manager.isImageFile(f)){
				allImageFiles.add(f);
			}else if(f.isDirectory()){
				allImageFiles.addAll(getAllImageFiles(f));
			}
		}
		return allImageFiles;
	}
	
	/*
	 * Removes all the tags from f
	 * @param f
	 * 			File to remove tags
	 */
	public void removeAllTags(File f) throws IOException{
		manager.findFromRecord(f).removeAllTags();
		manager.saveToFile(manager.getLogPath());
	}
	
	
	/*
	 * Getter method for private variable manager
	 */
	public FileNodeManager getManager() {
		return manager;
	}

	/*
	 * Setter method for private variable manager
	 */
	public void setManager(FileNodeManager manager) {
		this.manager = manager;
	}

	public File getDir() {
		return dir;
	}


	public void setDir(File dir) {
		this.dir = dir;
	}


	public static void main(String args[]) throws ClassNotFoundException, IOException{
		Renamer i = new Renamer(new File("/Users/gordonlin/Desktop/A2_Test_Folder"));
		for(File f : i.getAllImageFiles(new File("/Users/gordonlin/Desktop/A2_Test_Folder"))){
			System.out.println(f.getAbsolutePath());
		}
	}
}

