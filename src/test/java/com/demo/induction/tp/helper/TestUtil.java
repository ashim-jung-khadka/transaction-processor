package com.demo.induction.tp.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author ashimjk on 01/04/2019
 */
public class TestUtil {

	private TestUtil() {
	}

	public static MultiValueMap<String, Object> getResource(String fileName) {

		ClassPathResource resource = new ClassPathResource(fileName);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("file", resource);

		return map;
	}
}
