/**
 * 
 */
package kbox.extractor.framework.parsers;

import java.util.List;

import kbox.extractor.framework.persistence.KnsMetadata;

/**
 * @author Ciro Baron Neto
 * 
 * Interface which should be implemented for KBox parsers. 
 * 
 * Nov 4, 2016
 */
public interface KBoxParser {
	
	/**
	 * Read the list of NKS from the parser
	 * @return
	 */
	public List<KnsMetadata> readKNSList();
	
}
