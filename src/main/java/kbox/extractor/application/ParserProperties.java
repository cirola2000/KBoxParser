package kbox.extractor.application;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * General properties of the application. Most of them are 
 * set in the config file.
 * @author ciro
 *
 */
public class ParserProperties {

	/**
	 * Load properties from file
	 */
	public void loadProperties() {
		Properties prop = new Properties();
		InputStream inputStream = null;
		try {
			String propFileName = "resources/config.properties";

			inputStream = new FileInputStream(propFileName);

			prop.load(inputStream);

			// get the property value and print it out
			TMP_DOWNLOAD_PATH = prop.getProperty("TMP_DOWNLOAD_PATH");
			
			INDEX_PATH = prop.getProperty("INDEX_PATH");
						
			DBPEDIA_INDEX_PATH = prop.getProperty("DBPEDIA_INDEX_PATH");
			
			METADATA_DIR = prop.getProperty("METADATA_DIR");
			
			KNS_LIST_DIR = prop.getProperty("KNS_LIST_DIR");
			
			KBOX_JAR_DIR = prop.getProperty("KBOX_JAR_DIR");
			
			createDir(TMP_DOWNLOAD_PATH);
			createDir(INDEX_PATH);
			createDir(DBPEDIA_INDEX_PATH);
			createDir(METADATA_DIR);
			createDir(KNS_LIST_DIR);
			createDir(KBOX_JAR_DIR);


		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				inputStream.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	static String TMP_DOWNLOAD_PATH = null;
	static String INDEX_PATH = null;	
	static String DBPEDIA_INDEX_PATH = null;	
	static String METADATA_DIR = null;
	static String KNS_LIST_DIR = null;
	static String KBOX_JAR_DIR = null;
	
	public void createDir(String dir){
		if(! new File(dir).exists()){
			new File(dir).mkdirs();
		}
	}
	

}
