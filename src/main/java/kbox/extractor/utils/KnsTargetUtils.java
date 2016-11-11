/**
 * 
 */
package kbox.extractor.utils;

/**
 * @author Ciro Baron Neto
 * 
 * Nov 4, 2016
 */
public class KnsTargetUtils {
	
	/**
	 * Get the name of a file from an URL
	 * @param datasetUrl
	 * @return the filename
	 */
	public String createKNSTargetName(String datasetUrl){
		return datasetUrl.substring( datasetUrl.lastIndexOf('/')+1, datasetUrl.length()).replace(".ttl.bz2", "");
	}

}
