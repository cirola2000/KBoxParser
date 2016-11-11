/**
 * 
 */
package kbox.extractor.application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import kbox.extractor.application.implementation.kibe.KIBEJarImpl;
import kbox.extractor.application.implementation.persistence.PersistenceFSImpl;
import kbox.extractor.framework.parsers.DataIDFileParser;
import kbox.extractor.framework.parsers.KBoxParser;
import kbox.extractor.framework.persistence.KBoxPersistence;
import kbox.extractor.framework.persistence.KnsMetadata;
import kbox.extractor.framework.persistence.KnsTable;
import kbox.extractor.services.ExtractorServices;
import kbox.extractor.utils.KnsTargetUtils;

/**
 * @author Ciro Baron Neto
 * 
 *         Nov 2, 2016
 */
public class Provider {

	final static Logger logger = Logger.getLogger(Provider.class);

	static String TMP_DOWNLOAD_PATH = "/tmp/kbox/dbpedia/";
	static String TMP_INDEX_FOLDER = "/home/ciro/temp/kbox/tmp/";
	static String INDEX_PATH = "/home/ciro/temp/kbox/indexes/";
	static String DBPEDIA_IDEX_PATH = INDEX_PATH + "dbpedia/";
	
	static String METADATA_DIR = "/home/ciro/temp/kbox/";
	static String KNS_LIST_DIR = "/home/ciro/temp/kbox/publish/";
	static String KBOX_JAR_DIR = "/home/ciro/temp/kbox/";
	
	static String DBPEDIA_DATAID_ADDRESS = "http://downloads.dbpedia.org/2015-10/2015-10_dataid_catalog.ttl";

	public static void main(String[] args) {
		
		createDirectories();
		
		KBoxPersistence<KnsMetadata> metadataPersistence = new PersistenceFSImpl<>(METADATA_DIR); 
		KBoxPersistence<KnsTable> knsPersistence = new PersistenceFSImpl<>(KNS_LIST_DIR);
		
			
		ExtractorServices extractorServices = new ExtractorServices(metadataPersistence, new KIBEJarImpl(KBOX_JAR_DIR));

//		// load dbpedia files from latest dbpedia dataID
//		KBoxParser parser = new DataIDFileParser(DBPEDIA_DATAID_ADDRESS);

//		List<KnsMetadata> metadataList = parser.readKNSList();

//		extractorServices.persistence().save(metadataList);
		 List<KnsMetadata> metadataList = extractorServices.persistence().load(); 

		List<KnsTable> knsTable = new ArrayList<>();

		int i = 0;
		// update the KNSTarget name
		for (KnsMetadata k : metadataList) {
			try {
				// set appropriate kns name
				String knsTargetName = new KnsTargetUtils().createKNSTargetName(k.getDownloadURL().toString());
				logger.info("Setting NKS name to: " + knsTargetName);
				k.setLocalKNSPath(knsTargetName);

				// stream dataset
				logger.info("Streaming dataset: " + k.getDownloadURL().toString());
				extractorServices.streaming().saveUrl(TMP_DOWNLOAD_PATH + "/" + knsTargetName,
						k.getDownloadURL().toString(), true);

				// create index
				logger.info("Creating KBox index for: " + knsTargetName);
				extractorServices.kboxKIBEServices().createIndex(knsTargetName, TMP_DOWNLOAD_PATH, DBPEDIA_IDEX_PATH, TMP_INDEX_FOLDER);

				logger.info("Removing dataset file.");
				new File(DBPEDIA_IDEX_PATH + "/" + knsTargetName).delete();

				knsTable.add(new KnsTable(k.getTitle(),
						"http://vmdbpedia.informatik.uni-leipzig.de:3031/dbpedia/" + k.getLocalKNSPath()+".kb", k.getDesc(),
						k.getPublisher()));

				logger.info("Processing for " + knsTargetName + " is done. ");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i++ == 3)
				break;
		}

		extractorServices.persistence().save(metadataList);
		knsPersistence.save(knsTable);
		logger.info("Done!");

	}

	public static void createDirectories() {
		if (!new File(KNS_LIST_DIR).exists())
			new File(KNS_LIST_DIR).mkdirs();

		if (!new File(DBPEDIA_IDEX_PATH).exists())
			new File(DBPEDIA_IDEX_PATH).mkdirs();

		
		if (!new File(TMP_DOWNLOAD_PATH).exists())
			new File(TMP_DOWNLOAD_PATH).mkdirs();
		
		if (!new File(TMP_INDEX_FOLDER).exists())
			new File(TMP_INDEX_FOLDER).mkdirs();
	}

}
