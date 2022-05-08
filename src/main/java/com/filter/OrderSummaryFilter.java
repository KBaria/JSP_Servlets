package com.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.OrderItem;

/**
 * Servlet Filter implementation class OrderSummaryFilter
 */
@WebFilter("/OrderSummaryFilter")
public class OrderSummaryFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

    public OrderSummaryFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		List<OrderItem> orders = (List<OrderItem>) session.getAttribute("orders");
		String orderDateTime = (String) session.getAttribute("orderDateTime");
		
		if(orders != null && orderDateTime != null) {
			chain.doFilter(request, response);
		}else {
			res.sendRedirect("home");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
