<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-04-28
  Time: 오후 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>목록 조회</title>
</head>
<body>
    <table border="1">
        <thead>
            <tr>
                <th>번호</th>
                <th>이름</th>
                <th>직업</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${!empty list}">
                <c:forEach items="${list}" var="member" varStatus="status">
                    <tr>
                        <td>
                            <c:out value="${member.id}"/>
                        </td>
                        <td>
                            <c:out value="${member.name}"/>
                        </td>
                        <td>
                            <c:out value="${member.job}"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</body>
</html>
