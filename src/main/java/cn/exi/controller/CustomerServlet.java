package cn.exi.controller;

import cn.exi.model.Customer;
import cn.exi.service.CustomerService;
import cn.exi.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by HuangHailiang on 2017/4/23.
 */
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            customerService = new CustomerServiceImpl();
            List<Customer> customerList = customerService.getCustomerList("cu");
            req.setAttribute("customerList", customerList);
            req.getRequestDispatcher("/WEB-INF/jsp/customer/customer.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
