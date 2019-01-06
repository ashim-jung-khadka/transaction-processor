package com.demo.induction.tp.service;

import com.demo.induction.tp.helper.ExpectedValues;
import com.demo.induction.tp.model.Transaction;
import com.demo.induction.tp.model.Violation;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CsvTransactionProcessorTest {

	private TransactionProcessor transactionProcessor;

	@Before
	public void setUp() {
		transactionProcessor = new CsvTransactionProcessor();
	}

	// check if input stream is null or not
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentException_WhenInputStreamIsNull() {

		transactionProcessor.importTransactions(null);
	}

	// verify transaction list equals without violation
	@Test
	public void shouldEqualTransactionList_WhenCSVIsImported() throws FileNotFoundException {
		this.loadTransaction("equalTransactionList.csv");

		List<Transaction> expected = ExpectedValues.getEqualsTransactionList();
		List<Transaction> actual = transactionProcessor.getImportedTransactions();

		assertNotNull(actual);
		assertEquals(expected.size(), actual.size());
		assertEquals(expected, actual);
	}

	// verify property : type error
	// verify violation list equals
	// verify order of error
	@Test
	public void shouldReturnError_WhenTransactionTypeIsBlankOrIsInvalid() throws FileNotFoundException {
		this.loadTransaction("transactionTypeIsBlankOrIsInvalid.csv");

		List<Violation> expected = ExpectedValues.getTransactionTypeIsBlankOrIsInvalid();
		List<Violation> actual = transactionProcessor.validate();

		assertNotNull(actual);
		assertEquals(expected.size(), actual.size());
		assertEquals(expected, actual);
	}

	// verify property : amount error
	// verify violation list equals
	// verify order of error
	@Test
	public void shouldReturnError_WhenAmountIsBlankOrIsZero() throws FileNotFoundException {
		this.loadTransaction("amountIsBlankOrIsZero.csv");

		List<Violation> expected = ExpectedValues.getAmountIsBlankOrIsZero();
		List<Violation> actual = transactionProcessor.validate();

		assertNotNull(actual);
		assertEquals(expected.size(), actual.size());
		assertEquals(expected, actual);
	}

	// verify balance equals
	// verify transaction size
	// verify violation error
	@Test
	public void shouldEqualBalance_WhenCsvIsImported() throws FileNotFoundException {
		this.loadTransaction("equalBalance.csv");

		assertTrue(transactionProcessor.isBalanced());

		List<Transaction> transactions = transactionProcessor.getImportedTransactions();
		assertEquals(2, transactions.size());

		List<Violation> violations = transactionProcessor.validate();
		assertEquals(0, violations.size());
	}

	// verify balance not match
	// verify transaction size
	// verify violation error
	@Test
	public void shouldNotEqualBalance_WhenCsvIsImported() throws FileNotFoundException {
		this.loadTransaction("notEqualBalance.csv");

		assertFalse(transactionProcessor.isBalanced());

		List<Transaction> transactions = transactionProcessor.getImportedTransactions();
		assertEquals(4, transactions.size());

		List<Violation> violations = transactionProcessor.validate();
		assertEquals(0, violations.size());
	}

	private void loadTransaction(String fileName) throws FileNotFoundException {
		String file = "src/test/resources/csv/" + fileName;
		InputStream is = new FileInputStream(file);
		transactionProcessor.importTransactions(is);
	}
}
