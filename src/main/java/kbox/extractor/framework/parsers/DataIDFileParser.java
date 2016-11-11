/**
 * 
 */
package kbox.extractor.framework.parsers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kbox.extractor.framework.parsers.helpers.DCATHelper;
import kbox.extractor.framework.parsers.helpers.DataIDHelper;
import kbox.extractor.framework.persistence.KnsMetadata;

/**
 * @author Ciro Baron Neto
 * 
 *         Sep 27, 2016
 */
public class DataIDFileParser implements KBoxParser {

	final static Logger logger = LoggerFactory.getLogger(DataIDFileParser.class);

	DataIDHelper dataidHelper = new DataIDHelper();

	String repositoryAddress;

	/**
	 * Constructor for Class DataIDFileParser2
	 */
	public DataIDFileParser(String dcatFile) {
		this.repositoryAddress = dcatFile;
	}

	private List<KnsMetadata> knslist = null;

	private void parse() {

		knslist = new ArrayList<>();
		DCATHelper dcatHelper = new DCATHelper(repositoryAddress, "ttl");
		for (String catalog : dcatHelper.getListOfCatalogs()) {
			for (String dcatDataset : dcatHelper.getDatasetsFromCatalog(catalog)) {
				if (!dataidHelper.loadDataIDFile(dcatDataset))
					logger.error("We couldn't load the DataID file.");

				else {
					String dataset = dataidHelper.getPrimaryTopic();
					iterateDatasets(dataset);
				}
			}
		}
	}

	int i = 0;
	HashSet<String> set = new HashSet<>();

	public void iterateDatasets(String dataset) {
		// get all subsets
		for (String subset : dataidHelper.getSubsets(dataset)) {
			iterateDatasets(subset);
		}

		List<String> distributions = dataidHelper.getDistributions(dataset);

		for (String distribution : distributions) {
			try {
				if (distribution.endsWith("ttl.bz2")) {
					if (!set.contains(distribution))
						knslist.add(new KnsMetadata(dataidHelper.getTitle(distribution),
								new URL(dataidHelper.getDownloadURL(distribution)), dataidHelper.getTitle(distribution),
								"KBOX TEAM", new Date()));
					set.add(distribution);
					
					if (i++ % 1000 == 0) {
						System.out.println(i);
					}
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kbox.extractor.parsers.KBoxParser#readList()
	 */
	@Override
	public List<KnsMetadata> readKNSList() {
		if (knslist == null)
			parse();
		return knslist;
	}

}
