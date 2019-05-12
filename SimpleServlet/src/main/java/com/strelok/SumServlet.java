package com.strelok;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SumServlet extends HttpServlet {

    public SumServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("sum.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getSum(request, response);

        doGet(request, response);
    }

    private void getSum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String valA = request.getParameter("value_a");
        String valB = request.getParameter("value_b");
        if(valA.length() == 0 || valB.length() == 0) return;

        int sum = Integer.parseInt(valA) + Integer.parseInt(valB);

        request.setAttribute("result",Integer.toString(sum));
    }

}
