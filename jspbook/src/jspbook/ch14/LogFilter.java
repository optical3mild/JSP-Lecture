package jspbook.ch14;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.*;

@WebFilter("/ch14/*")
public class LogFilter implements Filter {
	Logger log;
	
  
	public void destroy() {
		log.info("LogFilter stop");
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		log.debug("LogFilter run"+arg0.getRemoteAddr());
		arg2.doFilter(arg0, arg1);
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		log = LoggerFactory.getLogger(this.getClass());
		log.info("LogFilter start");
	}

}
