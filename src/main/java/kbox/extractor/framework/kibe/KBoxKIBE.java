/**
 * 
 */
package kbox.extractor.framework.kibe;

/**
 * @author Ciro Baron Neto
 * 
 * Nov 5, 2016
 */
public interface KBoxKIBE {

	/**
	 * Call KBox KIBE service in order to create an index
	 * @param indexName
	 * @param sourceFoder
	 * @param destFolder
	 */
	public void createIndex(String indexName, String sourceFoder, String destFolder, String tmpFolder);
}
