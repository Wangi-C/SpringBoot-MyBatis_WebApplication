<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page isELIgnored="false" %>
<html>
<head>
    <title>목록 조회</title>
</head>
<body>
    <form action="/member" method="post">
        <input type="hidden" name="_method" value="delete">
        <table border="1">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>이름</th>
                    <th>직업</th>
                    <th>보기</th>
                    <th>수정</th>
                    <th>삭제</th>
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
                            <td>
                                <a href="/member/${member.id}">보기</a>
                            </td>
                            <td>
                                <a href="/member/form/${member.id}">수정</a>
                            </td>
                            <td>
                                <input type="hidden" name="id" value="${member.id}">
                                <input type="submit" name="deleteSub${member.id}" value="삭제">
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </form>
    <div>
        <a href="/member/form"><input type="button" id="button_add" name="button_add" value="등록"></a>
    </div>
</body>
</html>
