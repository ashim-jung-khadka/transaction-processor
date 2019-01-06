package com.demo.induction.tp.util;

import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ashimjk on 01/04/2019
 */
public class TransactionProcessorUtil {

	private TransactionProcessorUtil() {
	}

	/**
	 * Gives meta information of multipart
	 *
	 * @param multipartFile {@link MultipartFile} should not be null
	 * @return meta data wrapped in {@link Map}
	 */
	public static Map<String, String> getMetadata(MultipartFile multipartFile) {

		Assert.notNull(multipartFile, "file should not be null");

		Map<String, String> response = new HashMap<>();
		response.put("fileName", multipartFile.getOriginalFilename());
		response.put("contentType", multipartFile.getContentType());
		response.put("size", String.valueOf(multipartFile.getSize()));

		return response;

	}

}
