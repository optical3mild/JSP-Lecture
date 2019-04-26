package jspbook.ch14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	private static final Logger LOG = LoggerFactory.getLogger(LogTest.class);
	
	public static void main(String[] args) {
		String msg = "Hello Log!";
		LOG.info("test log");
		LOG.warn("test log : {}", msg);
		LOG.trace("trace level test");
		LOG.debug("debug level test");

	}

}
