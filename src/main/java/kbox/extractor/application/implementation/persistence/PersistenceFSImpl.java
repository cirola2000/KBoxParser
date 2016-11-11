/**
 * 
 */
package kbox.extractor.application.implementation.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import kbox.extractor.framework.persistence.KBoxPersistence;
import kbox.extractor.utils.JSONTransformer;

/**
 * @author Ciro Baron Neto
 * 
 *         This class implements the KBoxPersistence interface and saves/read
 *         KNS on the file system.
 * 
 *         Nov 2, 2016
 */
public class PersistenceFSImpl <T> implements KBoxPersistence<T> {

	public String directory = "/tmp/";
	public String fileName = "knslist.json";
	 
	/**
	 * Constructor for Class PersistenceFSImpl 
	 */
	public PersistenceFSImpl(String persistenceDirectory) {
		this.directory = persistenceDirectory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kbox.extractor.persistence.PersistenceInterface#read()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List load() {
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(directory + fileName));
//			return new JSONTransformer<ArrayList<T>>( new ArrayList<T> ).createNKS(new String(encoded, Charset.defaultCharset()));
			return (List) new JSONTransformer<T>().
					createNKSMetadata(new String(encoded, Charset.defaultCharset()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kbox.extractor.persistence.PersistenceInterface#save(java.util.List)
	 */
	@Override
	public boolean save(List entries) {

		File file = new File(directory + fileName);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(new JSONTransformer().createJSON(entries));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param directory 
	 * Set the directory value.
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	/**
	 * @return the directory
	 */
	public String getDirectory() {
		return directory;
	}
	
	/**
	 * @param fileName 
	 * Set the fileName value.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	

}
