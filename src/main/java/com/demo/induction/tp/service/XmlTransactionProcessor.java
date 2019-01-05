package com.demo.induction.tp.service;

import com.demo.induction.tp.model.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author ashimjk on 12/19/2018
 */
public class XmlTransactionProcessor implements TransactionProcessor {

	private Logger logger = Logger.getLogger(XmlTransactionProcessor.class.getName());

	private List<Transaction> transactions;

	public XmlTransactionProcessor() {
		this.transactions = new ArrayList<>();
	}

	@Override
	public void importTransactions(InputStream is) {
		if (is == null) {
			throw new IllegalArgumentException("transaction must be provided");
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			NodeList nList = doc.getElementsByTagName("Transaction");

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) nNode;

					Transaction transaction = new Transaction();
					transaction.setType(element.getAttribute("type"));
					transaction.setNarration(element.getAttribute("narration"));

					String amount = element.getAttribute("amount");
					transaction.setAmount(getAmount(amount));

					transactions.add(transaction);
				}
			}
		} catch (ParserConfigurationException | IOException | SAXException e) {
			logger.severe("error while parsing xml");
		}
	}

	private BigDecimal getAmount(String amount) {

		BigDecimal output = new BigDecimal(0);

		try {
			output = new BigDecimal(amount);
		} catch (NumberFormatException ex) {
			logger.severe("amount is not valid : {" + amount + "}");
		}

		return output;
	}

	@Override
	public List<Transaction> getImportedTransactions() {
		return transactions;
	}
}
