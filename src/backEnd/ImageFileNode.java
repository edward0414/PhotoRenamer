package backEnd;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



/*
 * A FileNode that represent ImageFiles that can change it's name based on adding or removing a 
 * set of tags. Keeps track of all the name changes ever done.
 * Can revert name back to a previous time.
 */
public class ImageFileNode extends FileNode {
	
	
	//a list of all tags for this current ImageFileNode
	private ArrayList<String> usedTags;
	// the extension type of this ImageFileNode
	private String extension;
	// A recording of the previous name changes in the form of Map<time, prevName>
	private Map<String,String> allRecords;
	
	private static final long serialVersionUID = -3666380493313135283L;
	
	/*
	 * Creates a new ImageFileNode from File f.
	 * @param f
	 * 			File used to create this ImageFileNode
	 */
	public ImageFileNode(File f){
		super(f);
		this.usedTags = new ArrayList<String>();
		this.allRecords = new HashMap<String, String>();
		this.extension = "";
		
		//gets the extension for this ImageFileNode, assuming the extension type is
		//simple i.e("name.txt" as oppose to "name.txt.zar")
		int i = f.getName().lastIndexOf('.');
		if (i > 0) {
		    extension = "." + f.getName().substring(i+1);
		}
		// records the initial state of this ImageFileNodes
		record();
	}
	
	/*
	 * Returns the name of this ImageFileNode without the extension.
	 * @param filePath
	 * 			the path name to rid of the extension
	 */
	public String getNameWOExt(String filePath){
		String[] nameArray = filePath.split(extension);
		return nameArray[0];
	}

	
	/*
	 *Given a list a tags, changes the name of this ImageFileNode according to the tags.
	 *@param tagArray
	 *			the tags to add to the name of this ImageFileNode
	 */
	public void addTag(ArrayList<String> tagArray){
		this.usedTags.addAll(tagArray);
		String oldName = this.getName();
		//getting the name without extensions and adds the tags to it.
		String newName = getNameWOExt(oldName);
		for (String s: tagArray){
			newName += atChar + s;
		}
		newName += extension;
		//Changes the name of this ImageFileNode and the File associated with it.
		this.setName(oldName,newName);
		record();
	}
	
	/*
	 * Given a list of tags, removes those tags from the this ImageFile's name.
	 * @param tagArray
	 * 				list of tags to remove
	 */
	public void removeTag(ArrayList<String> tagArray){
		
		String oldName = this.getName();
		String nameWOExt = getNameWOExt(oldName);
		StringBuffer name = new  StringBuffer();
		
		String[] temp = nameWOExt.split(FileNode.atChar);
		for(String s : temp){
			if(s.equals(temp[0])){
				name.append (s);
			}else if(! tagArray.contains(s)){
				name.append(FileNode.atChar + s);
			}
		}
		name.append(extension);
		this.setName(oldName,name.toString());
		this.getUsedTags().removeAll(tagArray);
		record();
	}
	
	/*
	 * Removes all tags associated with this ImageFileNode.
	 */
	public void removeAllTags(){
		String oldName = this.getName();
		String newName = this.getName().split(FileNode.atChar)[0];
		
		newName += extension;
		this.setName(oldName,newName);
		this.setUsedTags(new ArrayList<String>());
		record();
	}
	
	/*
	 * Records the all the changes that occur.
	 * The timeStamp is measured in millisecond in order to capture changes that occur within
	 * short internals of each other.
	 */
    public void record(){
    	// obtain the time of change
    	long timeStamp = new Date().getTime();
    	String time = String.valueOf(timeStamp);
    	String name = this.getName();
    	this.getAllRecords().put(time,name);

    }
    
    /*
     * Reverts the name of this ImageFileNode to a previous time.
     * @param date
     * 			the time to revert back to given in miliseconds 
     */
    public void revertToDate(String date){
    	System.out.println(date);
    	String oldName = this.getName();
    	String revertName = this.getAllRecords().get(date);
    	ArrayList<String> tags = getTags(revertName);
    	
    	String name = getNameWOExt(revertName).split(FileNode.atChar)[0];
		for(String s: tags){
			name += FileNode.atChar + s;
		}
		name += extension;
		
		this.setUsedTags(tags);
		this.setName(oldName,name);
		record();
    }
    
    /*
     * Returns all the tags attached to this name.
     * @param name
     * 			the name to obtain the tags from 
     */
    public ArrayList<String> getTags(String name){
    	
    	ArrayList<String> tags = new ArrayList<String>();
    	//gets rid of the extension
    	String[] temp = name.split(extension);
    	// seperates the tags from each other
		String[] temp2 = temp[0].split(FileNode.atChar);
		for(String s: temp2){
			if(! s.equals(temp2[0])){
				tags.add(s);
			}
		}
		return tags;	
    }
    
    /*
     * Returns all the previous names this ImageFileNode has had, along with the timeStamp 
     * at that name.
     */
    public ArrayList<ArrayList<String>>viewAllNames(){
    	
		ArrayList<ArrayList<String>> rArray = new ArrayList<ArrayList<String>>();
		//gets a list of all the timeStamps
		Object[] a =  getAllRecords().keySet().toArray();
		for (Object s: a){
			ArrayList<String> temp = new ArrayList<String>();
			//adds time first then name
			temp.add((String) s);
			temp.add(getAllRecords().get(s));
			rArray.add(temp);
		}
		return rArray;
	}
    
    /*
     * Getter method for private variable UsedTags.
     */
	public ArrayList<String> getUsedTags() {
		return usedTags;
	}
	
    /*
     * Setter method for private variable UsedTags.
     */
	public void setUsedTags(ArrayList<String> usedTags) {
		this.usedTags = usedTags;
	}
	
	/*
	 * Getter method for private variable allRecords.
	 */
	public Map<String, String> getAllRecords() {
		return allRecords;
	}
	
	/*
	 * Setter method for private variable allRecords.
	 */
	public void setAllRecords(Map<String, String> allRecords) {
		this.allRecords = allRecords;
	}
	
	/*
	 * Setter method for private variable name.
	 * Changes the name of the File associated with this ImageFileNode as well as the 
	 * name of this ImageFileNode.
	 */
	@Override 
	public void setName(String oldName, String newName){
		super.setName(oldName,newName);
		File newF = new File(newName);
		this.getFile().renameTo(newF);
		this.setFile(newF);
	}
}