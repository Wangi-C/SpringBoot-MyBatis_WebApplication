<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <table border="1">
            <tr>
                <td>이름</td>
                <td><input type="text" id="memberName" name="name"></td>
            </tr>
            <tr>
                <td>직업</td>
                <td><input type="text" id="memberJob" name="job"></td>
            </tr>
            <input type="button" onclick="insertMember()" value="회원 등록하기">
        </table>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"/>
    <script type="text/javascript">
        function insertMember() {
            var member = {
                name : document.getElementById("memberName").value
                , job : document.getElementById("memberJob").value
            }

            console.log(member);
            var memberJson = JSON.stringify(member);

            $.ajax({
                url : "${pageContext.request.contextPath}/member/new"
                , type : "POST"
                , data : memberJson
                , headers : {"Content-Type" : "application/json;charset=UTF-8"}
            });
        }
    </script>
</body>
</html>
