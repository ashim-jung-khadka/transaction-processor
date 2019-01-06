package com.demo.induction.tp.dto;

import com.demo.induction.tp.model.Transaction;

import java.util.List;
import java.util.Map;

/**
 * @author ashimjk on 01/04/2019
 */
public class ResponseDTO {

	private List<Transaction> data;
	private Map<String, String> meta;

	public List<Transaction> getData() {
		return data;
	}

	public void setData(List<Transaction> data) {
		this.data = data;
	}

	public Map<String, String> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}

	@Override
	public String toString() {
		return "ResponseDTO{" +
				"data=" + data +
				", meta=" + meta +
				'}';
	}
}
