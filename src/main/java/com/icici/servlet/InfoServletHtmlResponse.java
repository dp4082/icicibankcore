package com.icici.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InfoServletHtmlResponse extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // Query parameters
        String name = req.getParameter("name");
        String role = req.getParameter("role");

        // Headers
        String userAgent = req.getHeader("User-Agent");
        String contentType = req.getHeader("Content-Type");

        // Request body → parse JSON manually
        StringBuilder bodyData = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                bodyData.append(line);
            }
        }

        String city = null;
        String age = null;
        if (contentType != null && contentType.contains("application/json")) {
        	ObjectMapper mapper = new ObjectMapper();
        	Map<String, Object> jsonBody = mapper.readValue(bodyData.toString(), Map.class);
            city = (String) jsonBody.get("city");
            age = (String) jsonBody.get("age");
        }

        // HTML output
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Request Information</h1>");
        out.println("<p>Name: " + name + "</p>");
        out.println("<p>Role: " + role + "</p>");
        out.println("<p>City: " + city + "</p>");
        out.println("<p>Age: " + age + "</p>");
        out.println("<p>User-Agent: " + userAgent + "</p>");
        out.println("<p>Content-Type: " + contentType + "</p>");
        out.println("</body></html>");
	}

}
