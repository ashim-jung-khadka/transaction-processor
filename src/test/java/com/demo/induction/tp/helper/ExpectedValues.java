package com.demo.induction.tp.helper;

import com.demo.induction.tp.model.Transaction;
import com.demo.induction.tp.model.Violation;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.demo.induction.tp.util.ViolationEnum.EMPTY_TYPE;
import static com.demo.induction.tp.util.ViolationEnum.INVALID_TYPE;
import static com.demo.induction.tp.util.ViolationEnum.ZERO_AMOUNT;

/**
 * @author ashimjk on 12/18/2018
 */
public class ExpectedValues {

	private ExpectedValues() {
	}

	public static List<Transaction> getEqualsTransactionList() {

		return Collections.unmodifiableList(Arrays.asList(
				new Transaction("D", new BigDecimal("61.22"), "Electricity bill"),
				new Transaction("D", new BigDecimal("52.14"), "Social security payment"),
				new Transaction("D", new BigDecimal("200.00"), "Payment sent to x"),
				new Transaction("C", new BigDecimal("1920.00"), "Salary"),
				new Transaction("D", new BigDecimal("150.00"), "Car rental")
		));
	}

	public static List<Violation> getTransactionTypeIsBlankOrIsInvalid() {
		return Collections.unmodifiableList(Arrays.asList(
				new Violation(1, EMPTY_TYPE.getProperty(), EMPTY_TYPE.getDesc()),
				new Violation(2, INVALID_TYPE.getProperty(), INVALID_TYPE.getDesc())
		));
	}

	public static List<Violation> getAmountIsBlankOrIsZero() {
		return Collections.unmodifiableList(Arrays.asList(
				new Violation(1, ZERO_AMOUNT.getProperty(), ZERO_AMOUNT.getDesc()),
				new Violation(2, ZERO_AMOUNT.getProperty(), ZERO_AMOUNT.getDesc()),
				new Violation(3, ZERO_AMOUNT.getProperty(), ZERO_AMOUNT.getDesc()),
				new Violation(4, ZERO_AMOUNT.getProperty(), ZERO_AMOUNT.getDesc())
		));
	}
}
