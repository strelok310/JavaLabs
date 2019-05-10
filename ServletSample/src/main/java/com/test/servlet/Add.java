package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FirstServlet
 */
/**
 * @author preetham
 *
 */
public class Add extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        System.out.println("Add Constructor called!");
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Add \"Init\" method called");
    }

    /**
     * @see Servlet#destroy()
     */
    public void destroy() {
        System.out.println("Add \"Destroy\" method called");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Add \"Service\" method(inherited) called");
        System.out.println("Add \"DoGet\" method called");

        sum(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Add \"Service\" method(inherited) called");
        System.out.println("Add \"DoPost\" method called");

    }

    private void sum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        int valA = Integer.parseInt(request.getParameter("value_a"));
        int valB = Integer.parseInt(request.getParameter("value_b"));
        int sum = valA + valB;

        System.out.println("Value A: " + valA + ", Value B: " + valB + ", their Sum: " + sum);

        out.write("<html><body><h1>Result</h1></body></html>");
        out.write("<html><body> " + valA + " + " + valB + " = " + sum + " </body></html>");
        out.write("<html><body><br/><br/><a href=\"add.jsp\">Try again</a></html>");
    }




}
