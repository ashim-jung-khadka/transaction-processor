package com.demo.induction.tp.controller;

import com.demo.induction.tp.dto.ResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.Map;

import static com.demo.induction.tp.helper.TestUtil.getResource;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;

/**
 * @author ashimjk on 01/04/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XmlTransactionProcessorControllerTest {

	private static String XML_UPLOAD_URL = "/transaction-processor/upload-xml";

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldReturnHttpStatusOK_WhenXmlUploadIsCalled() {

		MultiValueMap<String, Object> map = getResource("xml/equalBalance.xml");
		ResponseEntity<?> response = this.testRestTemplate.postForEntity(XML_UPLOAD_URL, map, Map.class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));
	}

	@Test
	public void shouldUploadXmlFileSuccessfully() {

		MultiValueMap<String, Object> map = getResource("xml/equalBalance.xml");
		ResponseDTO response = this.testRestTemplate.postForEntity(XML_UPLOAD_URL, map, ResponseDTO.class).getBody();

		assertThat(response, notNullValue());
		assertThat(response.getMeta(), notNullValue());
		assertThat(response.getData(),
				contains(
						allOf(
								hasProperty("type", is("D")),
								hasProperty("amount", is(BigDecimal.valueOf(463.36))),
								hasProperty("narration", is("Payment sent to x"))
						),
						allOf(
								hasProperty("type", is("C")),
								hasProperty("amount", is(BigDecimal.valueOf(463.36))),
								hasProperty("narration", is("Car rental"))
						)
				)
		);

	}

	@Test
	public void shouldReturnInvalidRequest() {

		MultiValueMap<String, Object> map = getResource("other/invalid_request.txt");
		ResponseEntity<?> response = testRestTemplate.postForEntity(XML_UPLOAD_URL, map, Map.class);
		Map<String, String> data = (Map<String, String>) response.getBody();

		assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
		assertThat(data, notNullValue());
		assertThat(data.size(), is(1));
		assertThat(data.get("message"), is("provide valid content type : text/xml or application/xml, not a text/plain"));

	}

}
