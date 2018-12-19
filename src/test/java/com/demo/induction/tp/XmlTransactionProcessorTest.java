package com.demo.induction.tp;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class XmlTransactionProcessorTest {

	private TransactionProcessor transactionProcessor;

	@Before
	public void setUp() {
		transactionProcessor = new XmlTransactionProcessor();
	}

	// check if input stream is null or not
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentException_WhenInputStreamIsNull() {

		transactionProcessor.importTransactions(null);
	}

	// verify transaction list equals without violation
	@Test
	public void shouldEqualsTransactionList_WhenXmlIsImported() throws FileNotFoundException {
		this.loadTransaction("equalTransactionList.xml");

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
		this.loadTransaction("transactionTypeIsBlankOrIsInvalid.xml");

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
		this.loadTransaction("amountIsBlankOrIsZero.xml");

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
	public void shouldEqualBalance_WhenXmlIsImported() throws FileNotFoundException {
		this.loadTransaction("equalBalance.xml");

		assertTrue(transactionProcessor.isBalanced());

		List<Transaction> transactions = transactionProcessor.getImportedTransactions();
		assertEquals(5, transactions.size());

		List<Violation> violations = transactionProcessor.validate();
		assertEquals(0, violations.size());
	}

	// verify balance not match
	// verify transaction size
	// verify violation error
	@Test
	public void shouldNotEqualBalance_WhenXmlIsImported() throws FileNotFoundException {
		this.loadTransaction("notEqualBalance.xml");

		assertFalse(transactionProcessor.isBalanced());

		List<Transaction> transactions = transactionProcessor.getImportedTransactions();
		assertEquals(4, transactions.size());

		List<Violation> violations = transactionProcessor.validate();
		assertEquals(0, violations.size());
	}

	private void loadTransaction(String fileName) throws FileNotFoundException {
		String file = "src/test/resources/xml/" + fileName;
		InputStream is = new FileInputStream(file);
		transactionProcessor.importTransactions(is);
	}
}
