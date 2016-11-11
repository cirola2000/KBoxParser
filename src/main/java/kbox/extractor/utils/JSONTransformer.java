/**
 * 
 */
package kbox.extractor.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import kbox.extractor.framework.persistence.KnsMetadata;
import kbox.extractor.framework.persistence.KnsTable;

/**
 * @author Ciro Baron Neto
 * 
 *         Transforms KNS into JSON and other way around
 * 
 *         Nov 2, 2016
 */
public class JSONTransformer<T> {

	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private Class<T> type;
	
	/**
	 * Constructor for Class JSONTransformer 
	 */
	public JSONTransformer() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor for Class JSONTransformer 
	 */
	public JSONTransformer(Class<T> type) {
		this.type = type;
	}

	/**
	 * Convert from JSON to KNS class
	 * 
	 * @param json
	 * @return
	 * @return the KNS instance
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<KnsMetadata> createNKSMetadata(String json) {
		Type listType = new TypeToken<ArrayList<KnsMetadata>>(){}.getType();
		return (ArrayList<KnsMetadata>) gson.fromJson(json, listType);
	}
	
	/**
	 * Convert from JSON to KNS class
	 * 
	 * @param json
	 * @return
	 * @return the KNS instance
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<KnsTable> createNKSTable(String json) {
		Type listType = new TypeToken<ArrayList<KnsTable>>(){}.getType();
		return (ArrayList<KnsTable>) gson.fromJson(json, listType);
	}

	/**
	 * Convert NKS entry to JSON String
	 * 
	 * @param kns
	 * @return a string with the JSON object
	 */
	public String createJSON(T kns) {
		return gson.toJson(kns);
	}

}
