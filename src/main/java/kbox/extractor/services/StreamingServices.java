/**
 * 
 */
package kbox.extractor.services;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

/**
 * @author Ciro Baron Neto
 * 
 *         Nov 4, 2016
 */
public final class StreamingServices {

	public void saveUrl(final String filename, final String urlString, boolean isBz2)
			throws MalformedURLException, IOException {
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			if (isBz2)
				in = new BufferedInputStream(new BZip2CompressorInputStream(new URL(urlString).openStream()));
			else
				in = new BufferedInputStream(new URL(urlString).openStream());
			
			fout = new FileOutputStream(filename);

			final byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (fout != null) {
				fout.close();
			}
		}
	}
}
