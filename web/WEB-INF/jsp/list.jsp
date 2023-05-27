<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.basejava.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <title>List of all resumes</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table>
        <tr>
            <th>NAME</th>
            <th>EMAIL</th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume"/>
            <tr>
                <td>
                    <a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a>
                </td>
                <td>
                    <%=ContactType.MAIL.toHtml(resume.getContact(ContactType.MAIL))%>
                </td>
                <td>
                    <a href="resume?uuid=${resume.uuid}&action=edit">Edit</a>
                </td>
                <td>
                    <a href="resume?uuid=${resume.uuid}&action=delete">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="resume?uuid=${resume.uuid}&action=add">
        <button type="submit">Add</button>
    </a>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
