/**
 * 
 */
package kbox.extractor.framework.persistence;

import java.util.List;

/**
 * @author Ciro Baron Neto
 * 
 * This class aims to implement a persistence layer between KNS and a database, REST, file system, etc.
 * 
 * Nov 2, 2016
 */
public interface KBoxPersistence <T> {
	
	/**
	 * Read a list of KNS from somewhere
	 * @return
	 */
	public List<T> load();
	
	
	/**
	 * Save a list of KNS
	 * @param entries
	 * @return
	 */
	public boolean save(List<T> entries);

}
