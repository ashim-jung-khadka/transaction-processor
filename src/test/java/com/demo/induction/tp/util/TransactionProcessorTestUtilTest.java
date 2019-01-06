package com.demo.induction.tp.util;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;

/**
 * @author ashimjk on 01/04/2019
 */
public class TransactionProcessorTestUtilTest {

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentException_WhenMultipartIsNull() {

		TransactionProcessorUtil.getMetadata(null);
	}

	@Test
	public void shouldReturnData_WhenValidMultipartIsGiven() {

		MockMultipartFile multipartFile = new MockMultipartFile("testFile.txt",
				"testFile.txt", "text/plan",
				"Multipart Test".getBytes());

		Map<String, String> metadata = TransactionProcessorUtil.getMetadata(multipartFile);

		assertThat(metadata, notNullValue());
		assertThat(metadata.size(), is(3));

		assertThat(metadata, hasEntry("fileName", "testFile.txt"));
		assertThat(metadata, hasEntry("contentType", "text/plan"));
		assertThat(metadata, hasEntry("size", "14"));

	}

}
