<%@ page import="com.basejava.webapp.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <h3>Full Name</h3>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}" required></dd>
        </dl>
        <h3>Contacts:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt style="margin-bottom: 5px">${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <hr>
        <p>
            <c:forEach var="sectionType" items="<%=SectionType.values()%>">
                <jsp:useBean id="sectionType" type="com.basejava.webapp.model.SectionType"/>
            <c:choose>
            <c:when test="${sectionType == 'OBJECTIVE' || sectionType == 'PERSONAL'}">
        <h3> ${sectionType.title}</h3>
        <c:choose>
            <c:when test="<%=!resume.getSections().containsKey(sectionType)%>">
                        <textarea style="margin: 0 3px" cols="110" rows="10"
                                  name="${sectionType.name()}" id="content"></textarea>
            </c:when>
            <c:otherwise>
                        <textarea cols="110" rows="10"
                                  name="${sectionType.name()}"
                                  id="content"><%=((TextSection) resume.getSections().get(sectionType)).getText()%></textarea>
            </c:otherwise>
        </c:choose>
        </c:when>
        <c:when test="${sectionType == 'ACHIEVEMENT' || sectionType =='QUALIFICATION'}">
            <h3> ${sectionType.title}</h3>
            <c:choose>
                <c:when test="<%=!resume.getSections().containsKey(sectionType)%>">
                        <textarea style="margin: 0 3px" cols="110" rows="10"
                                  name="${sectionType.name()}" id="content"></textarea>
                </c:when>
                <c:otherwise>
                        <textarea cols="110" rows="10"
                                  name="${sectionType.name()}"><%=String.join("\n",
                                ((ListSection) resume.getSections().get(sectionType)).getContents())%></textarea>
                </c:otherwise>
            </c:choose>
        </c:when>
        </c:choose>
        </c:forEach>
        <button type="submit">Save</button>
        <button type="button" onclick="window.history.back()">Cancel</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
