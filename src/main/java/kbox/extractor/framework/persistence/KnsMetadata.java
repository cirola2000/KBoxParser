/**
 * 
 */
package kbox.extractor.framework.persistence;

import java.net.URL;
import java.util.Date;

/**
 * @author Ciro Baron Neto
 * 
 * Class which holds metadata for each KNS entry
 * 
 * Nov 2, 2016
 */
public class KnsMetadata {
	
	private String title; 
	
	private URL downloadURL;
	
	private String desc;
	
	private String publisher; 
	
	private Date last_update;
	
	private String localKNSPath;
		
	
	/**
	 * Constructor for Class KNS 
	 */
	public KnsMetadata() {
	}
	
	/**
	 * @param localKNSPath 
	 * Set the localKNSPath value.
	 */
	public void setLocalKNSPath(String localKNSPath) {
		this.localKNSPath = localKNSPath;
	}
	
	/**
	 * @return the localKNSPath
	 */
	public String getLocalKNSPath() {
		return localKNSPath;
	}
	
	/**
	 * Constructor for Class KNS 
	 */
	public KnsMetadata(String title, URL downloadURL, String desc, String publisher, Date last_update) {
		this.title = title;
		this.downloadURL = downloadURL;
		this.desc = desc;
		this.publisher = publisher;
		this.last_update = last_update;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	

	/**
	 * @param title 
	 * Set the name value.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the downloadURL
	 */
	public URL getDownloadURL() {
		return downloadURL;
	}
	
	/**
	 * @param downloadURL 
	 * Set the target value.
	 */
	public void setDownloadURL(URL downloadURL) {
		this.downloadURL = downloadURL;
	}
	
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * @param desc 
	 * Set the desc value.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * @param publisher 
	 * Set the publisher value.
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * @return the last_update
	 */
	public Date getLast_update() {
		return last_update;
	}
	
	/**
	 * @param last_update 
	 * Set the last_update value.
	 */
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	
	

}
