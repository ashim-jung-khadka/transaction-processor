package com.demo.induction.tp.service;

import com.demo.induction.tp.model.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author ashimjk on 12/18/2018
 */
public class CsvTransactionProcessor implements TransactionProcessor {

	private Logger logger = Logger.getLogger(CsvTransactionProcessor.class.getName());

	private static final String COMMA = ",";
	private List<Transaction> transactions;

	public CsvTransactionProcessor() {
		this.transactions = new ArrayList<>();
	}

	@Override
	public void importTransactions(InputStream is) {
		if (is == null) {
			throw new IllegalArgumentException("transaction must be provided");
		}

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			transactions = br.lines()
					.map(line -> {

						String[] p = line.split(COMMA);
						Transaction transaction = new Transaction();
						transaction.setType(p[0]);

						try {
							transaction.setAmount(new BigDecimal(p[1]));
						} catch (NumberFormatException ex) {
							logger.severe("amount is not valid : {" + p[1] + "}");
							transaction.setAmount(new BigDecimal(0));
						}

						transaction.setNarration(p[2]);

						return transaction;
					})
					.collect(Collectors.toList());

			br.close();
		} catch (IOException e) {
			logger.severe("csv buffer reader closed early");
		}
	}

	@Override
	public List<Transaction> getImportedTransactions() {
		return transactions;
	}

}
