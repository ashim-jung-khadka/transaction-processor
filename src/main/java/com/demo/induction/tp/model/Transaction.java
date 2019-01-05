package com.demo.induction.tp.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {
	private String type;
	private BigDecimal amount;
	private String narration;

	public Transaction() {
	}

	public Transaction(String type, BigDecimal amount, String narration) {
		this.type = type;
		this.amount = amount;
		this.narration = narration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Transaction that = (Transaction) o;
		return type.equals(that.type) &&
				amount.equals(that.amount) &&
				narration.equals(that.narration);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, amount, narration);
	}
}
