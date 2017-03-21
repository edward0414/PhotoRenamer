package backEnd;


import java.io.File;
import java.io.Serializable;
/*
 * This is like the FileNode in A1 part 2, but in this the actually file is associated with
 * the node.
 * This is meant to be a superclass to the different FileNode of the different file types needed
 */
public abstract class FileNode implements Serializable {
	
	private static final long serialVersionUID = 2388872601945333566L;
	// pathname of the file
	private String name;
	// File associated with the FileNode
	private File file;
	// The @ charater need to be used for the names
	public final static String atChar = "@";
	
	/*
	 * Creates a new FileNode from File f
	 * @param f
	 * 			the file to create the node from
	 */
	public  FileNode(File f){
		this.name = f.getAbsolutePath();
		this.file = f;
	}
	
	/*
	 * Getter for private variable name
	 */
	public String getName() {
		return name;
	}
	/*
	 *  Setter for private variable name
	 */
	public void setName(String oldName, String newName) {
		this.name = newName;
	}
	/*
	 * Getter for private variable File
	 */
	public File getFile() {
		return file;
	}
	/*
	 *  Setter for private variable File
	 */
	public void setFile(File file) {
		this.file = file;
	}
	

}

