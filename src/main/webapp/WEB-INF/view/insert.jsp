<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/member/new" method="post">
        <table border="1">
            <tr>
                <td>이름</td>
                <td><input type="text" id="memberName" name="name"></td>
            </tr>
            <tr>
                <td>직업</td>
                <td><input type="text" id="memberJob" name="job"></td>
            </tr>
        </table>
        <input type="submit" value="회원 등록하기">
    </form>
    <c:out value="${errorMsg}"/>
        <c:if test="${errorMsg != null}">
            <c:out value="${errorMsg}"/>
            ㅎㅎ
        </c:if>
</body>
</html>
