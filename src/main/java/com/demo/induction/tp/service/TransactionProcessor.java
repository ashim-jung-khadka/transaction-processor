package com.demo.induction.tp.service;

import com.demo.induction.tp.model.Transaction;
import com.demo.induction.tp.model.Violation;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.demo.induction.tp.util.ViolationEnum.EMPTY_TYPE;
import static com.demo.induction.tp.util.ViolationEnum.INVALID_TYPE;
import static com.demo.induction.tp.util.ViolationEnum.ZERO_AMOUNT;

public interface TransactionProcessor {

	/**
	 * read the input stream and parse its content
	 *
	 * @param is transaction list as an input stream
	 */
	void importTransactions(InputStream is);

	/**
	 * @return the imported transaction list
	 */
	List<Transaction> getImportedTransactions();

	/**
	 * validates imported transaction using transaction type, amount
	 *
	 * @return list of violation if present else empty
	 */
	default List<Violation> validate() {
		int index = 1;
		List<Violation> violations = new ArrayList<>();

		for (Transaction transaction : getImportedTransactions()) {
			if (StringUtils.isEmpty(transaction.getType())) {

				violations.add(new Violation(index++, EMPTY_TYPE.getProperty(), EMPTY_TYPE.getDesc()));

			} else if (!("D".equalsIgnoreCase(transaction.getType())
					|| "C".equalsIgnoreCase(transaction.getType()))) {

				violations.add(new Violation(index++, INVALID_TYPE.getProperty(), INVALID_TYPE.getDesc()));

			} else if (transaction.getAmount().compareTo(new BigDecimal(0)) <= 0) {

				violations.add(new Violation(index++, ZERO_AMOUNT.getProperty(), ZERO_AMOUNT.getDesc()));
			}
		}

		return violations;
	}

	/**
	 * check whether credit and debit balance are equal
	 *
	 * @return true if the amount sum of all the transactions are equal
	 */
	default boolean isBalanced() {
		double debitAmount = 0;
		double creditAmount = 0;

		for (Transaction transaction : getImportedTransactions()) {
			if ("D".equalsIgnoreCase(transaction.getType())) {
				debitAmount += transaction.getAmount().doubleValue();

			} else if ("C".equalsIgnoreCase(transaction.getType())) {
				creditAmount += transaction.getAmount().doubleValue();
			}
		}

		return debitAmount > 0 && debitAmount == creditAmount;
	}
}
