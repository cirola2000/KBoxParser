/**
 * 
 */
package kbox.extractor.framework.persistence;

/**
 * @author Ciro Baron Neto
 * 
 * Nov 4, 2016
 */
public class KnsTable {
	
	
	String name;
	
	String target;
	
	String description;
	
	String publisher;
	
	long size;
	
	
	
	public KnsTable(String name, String target, String description, String publisher,long size) {
		super();
		this.name = name;
		this.target = target;
		this.description = description;
		this.publisher = publisher;
		this.size= size;
	}
	
	
	/**
	 * @param name 
	 * Set the name value.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param target 
	 * Set the target value.
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}
	/**
	 * @param description 
	 * Set the description value.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param publisher 
	 * Set the publisher value.
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}
	
	/**
	 * @param size 
	 * Set the size value.
	 */
	public void setSize(long size) {
		this.size = size;
	}
	
	
	

}
