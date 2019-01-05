package com.demo.induction.tp.model;

import java.util.Objects;

public class Violation {
	private int order;
	private String property;
	private String description;

	public Violation(int order, String property, String description) {
		this.order = order;
		this.property = property;
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Violation violation = (Violation) o;
		return order == violation.order &&
				property.equals(violation.property) &&
				description.equals(violation.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, property, description);
	}
}
