package backEnd;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;


/*
 * Reads and Writes Serialized FileNodes in to a .ser File
 */
public class FileNodeManager {
	
    private static final Logger logger =
            Logger.getLogger(FileNodeManager.class.getName());
    private static final Handler consoleHandler = new ConsoleHandler();
    // All ImageFileNodes created 
    private ArrayList<ImageFileNode> allFileNodes;
    // All avaliable tags 
    private ArrayList<String> allAvaTags;
    // Configuration files should end with this name 
    public final String logAddress = "/renamerLog.ser";
    private String logPath;
    
    /*
     * Creates a new empty FileNodeManager and given a filePath to a .ser file, can either read from 
     * that file or create a new file to be used for logging.
     * @param dir
     * 			directory to read from.
     */
    public FileNodeManager(File dir) throws ClassNotFoundException, IOException {
    	this.setAllFileNodes(new ArrayList<ImageFileNode>());
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        
        logPath = dir.getAbsolutePath() + logAddress;
        File file = new File(logPath);
        allAvaTags = new ArrayList<String>();
        
        if (file.exists()) {
           readFromFile(logPath);
        } else {
            file.createNewFile();
        }	
    }

	/*
	 * Checks if a given File is an Image File
	 * reference: http://stackoverflow.com/questions/9643228/test-if-file-is-an-image
	 * @param f
	 * 			File to check.
	 */
	public boolean isImageFile(File f){
		
		String mimetype= new MimetypesFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];
        if(type.equals("image")){
        	return true;
        }else{
        	return false;
        }
	}
	
	
    /*
     * Reads from the File denoted by String path
     * @param path 
     * 			the pathname of the File to be read for deserialization
     */
	@SuppressWarnings("unchecked")
	private void readFromFile(String path) throws ClassNotFoundException, IOException {

        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            this.setAllFileNodes((ArrayList<ImageFileNode>) input.readObject());
            this.setAllAvaTags((ArrayList<String>) input.readObject());

            input.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Cannot read from input.", ex);
        }    
    }
    
    /* Adds a new ImageFileNode to the records
     * @param record 
     * 			a FileNode to be added.
     * @throws IOException 
     */
    public void addFileNode(ImageFileNode toRecord) throws IOException {
    	this.getAllFileNodes().add(toRecord);
        // Log the addition of a new FileNode.
        logger.log(Level.FINE, "Added a new FileNode:" + toRecord.toString());
    }
    
    /* Saves all the changes to the log File.
     * @param filePath
     * 		path of the log File
     */
    public void saveToFile(String filePath) throws IOException{
    	OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        
        output.writeObject(this.getAllFileNodes());
        output.writeObject(this.getAllAvaTags());
        output.close();
    }
    
    /*
     * Returns the ImageFileNode associated with file F in this.allFileNode if it exists.
     * Else creates a new ImageFileNode from File f.
     * @param f
     * 			File to retrieve from records or to create new ImageFileNode
     */
    public ImageFileNode findFromRecord(File f) throws IOException{
 
    	for(ImageFileNode i: this.getAllFileNodes()){
			if(getNameWOtags(i.getFile()).equals(getNameWOtags(f))){
				return i;
			}
		}
    	
    	ImageFileNode nNode = new ImageFileNode(f);
    	addFileNode(nNode);
    	saveToFile(logPath);
    	return nNode;
    }
    
    /*
     * Returns the name of a File without tags.
     * @param f
     * 			File to get name without tags.
     */
    public String getNameWOtags(File f){
    	
    	String extension = "";
    	int i = f.getName().lastIndexOf('.');
		if (i > 0) {
		    extension = "." + f.getName().substring(i+1);
		}
		String nameWOtags = f.getAbsolutePath().split(extension)[0].split(FileNode.atChar)[0] 
				+ extension;
		return nameWOtags;	
    }

    /*
     * Getter method for private variable allFileNodes.
     */
	public ArrayList<ImageFileNode> getAllFileNodes() {
		return this.allFileNodes;
	}

    /*
     * Setter method for private variable allFileNodes.
     */
	public void setAllFileNodes(ArrayList<ImageFileNode> allFileNodes) {
		this.allFileNodes = allFileNodes;
	}

    /*
     * Getter method for private variable logPath.
     */
	public String getLogPath() {
		return logPath;
	}

    /*
     * Setter method for private variable logPath.
     */
	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

    /*
     * Getter method for private variable allAvaTags.
     */
	public ArrayList<String> getAllAvaTags() {
		return allAvaTags;
	}

    /*
     * Setter method for private variable allAvaTags.
     */
	public void setAllAvaTags(ArrayList<String> allAvaTags) {
		this.allAvaTags = allAvaTags;
	}

}