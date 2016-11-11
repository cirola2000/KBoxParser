/**
 * 
 */
package kbox.extractor.application.implementation.kibe;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import kbox.extractor.framework.kibe.KBoxKIBE;

/**
 * @author Ciro Baron Neto
 * 
 *         Class used for calling KBox .jar. It is used while KBox KIBE is not released
 *         Nov 4, 2016
 */
public class KIBEJarImpl implements KBoxKIBE {

	final static Logger logger = Logger.getLogger(KIBEJarImpl.class);

	String BASE_PATH = null;

	/**
	 * Constructor for Class KBoxKIBEServices
	 */
	public KIBEJarImpl(String baseJarPath) {
		this.BASE_PATH = baseJarPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kbox.extractor.framework.kibe.KBoxKIBE#createIndex(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void createIndex(String indexName, String sourceFoder, String destFolder, String tmpFolder) {
		String command = "java -Djava.io.tmpdir="+tmpFolder+ " -jar " + BASE_PATH
				+ "kbox-v0.0.1-alpha.jar -createIndex " + sourceFoder;
		logger.debug(executeCommand(command));
		command = "mv "+BASE_PATH+"kbox.kb " + destFolder + indexName + ".kb";

		logger.debug(executeCommand(command));
	}

	/**
	 * Just execute a command 
	 * @param command
	 * @return
	 */
	private String executeCommand(String command) {
		logger.debug("Executing command: " + command);
		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

}
