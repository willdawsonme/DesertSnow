<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<% response.setStatus(404); %>
<section>
    <h1>404 Page Not Found</h1>
    <p>The page you were looking for was not found. <a href="${pageContext.servletContext.contextPath}">Go home &rarr;</a></p>
</section>