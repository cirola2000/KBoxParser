/**
 * 
 */
package kbox.extractor.services;

import kbox.extractor.framework.kibe.KBoxKIBE;
import kbox.extractor.framework.persistence.KBoxPersistence;
import kbox.extractor.framework.persistence.KnsMetadata;

/**
 * @author Ciro Baron Neto
 * 
 * Set of services that which can be used with the API
 * 
 * Nov 4, 2016
 * @param <T>
 */
public class ExtractorServices {
	
	KBoxPersistence<KnsMetadata> persistenceImpl = null;
	
	KBoxKIBE kibeImpl = null;
	
	
	/**
	 * Constructor for Class ExtractorServices 
	 */
	public ExtractorServices(KBoxPersistence<KnsMetadata> persistenceImpl, KBoxKIBE kibeImpl) {
		this.persistenceImpl = persistenceImpl;
		this.kibeImpl = kibeImpl;
	}
	
	/**
	 * Get service to load and save KNS
	 * @return
	 */
	public KBoxPersistence<KnsMetadata> persistence(){
		return persistenceImpl;
	}
	
	/**
	 * Services to handle with file streaming
	 * @return
	 */
	public StreamingServices streaming(){
		return new StreamingServices();
	}
	
	
	/**
	 * KBox KIBE services
	 * @return
	 */
	public KBoxKIBE kboxKIBEServices(){
		return kibeImpl;
	}

}
