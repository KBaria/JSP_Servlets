package com.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.OrderItemDAO;
import com.model.Order;
import com.model.OrderItem;
import com.model.User;

@WebServlet(name = "GetOrdersServlet", urlPatterns = {"/orders"})
public class GetOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetOrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		try {
			List<OrderItem> orders = OrderItemDAO.getAllOrders(user.getEmail());
			Map<String, List<OrderItem>> orderMap = orders.stream()
					.collect(Collectors.groupingBy(OrderItem::getOrderDate));
			
			List<Order> orderList = orderMap.entrySet().stream()
					.map(entry -> new Order(user.getEmail(), entry.getKey(), entry.getValue()))
					.collect(Collectors.toList());
			
			request.setAttribute("orderList", orderList);
			getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
