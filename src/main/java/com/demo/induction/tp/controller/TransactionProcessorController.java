package com.demo.induction.tp.controller;

import com.demo.induction.tp.dto.ResponseDTO;
import com.demo.induction.tp.service.TransactionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.demo.induction.tp.util.TransactionProcessorUtil.getMetadata;

/**
 * @author ashimjk on 01/04/2019
 */
@RestController
@RequestMapping("/transaction-processor")
public class TransactionProcessorController {

	@Autowired
	@Qualifier("csv-processor")
	private TransactionProcessor csvProcessor;

	@Autowired
	@Qualifier("xml-processor")
	private TransactionProcessor xmlProcessor;

	@PostMapping("/upload-csv")
	public ResponseDTO uploadCSV(@RequestParam("file") MultipartFile file) throws IOException {

		String contentType = file.getContentType();
		Assert.isTrue("text/csv".equalsIgnoreCase(contentType), "provide valid content type : text/csv, not a " + contentType);

		csvProcessor.importTransactions(file.getInputStream());

		return getResponseDTO(file, csvProcessor);

	}

	@PostMapping("/upload-xml")
	public ResponseDTO uploadXML(@RequestParam("file") MultipartFile file) throws IOException {

		String contentType = file.getContentType();
		Assert.isTrue("text/xml".equalsIgnoreCase(contentType) || "application/xml".equalsIgnoreCase(contentType),
				"provide valid content type : text/xml or application/xml, not a " + contentType);

		xmlProcessor.importTransactions(file.getInputStream());

		return getResponseDTO(file, xmlProcessor);

	}

	private ResponseDTO getResponseDTO(MultipartFile file, TransactionProcessor transactionProcessor) {
		ResponseDTO response = new ResponseDTO();
		response.setData(transactionProcessor.getImportedTransactions());
		response.setMeta(getMetadata(file));

		return response;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleContentTypeNotSupported(IllegalArgumentException ex) {

		Map<String, String> response = new HashMap<>();
		response.put("message", ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}
