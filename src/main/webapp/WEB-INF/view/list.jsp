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
            <tbody id="memberRow">
            </tbody>
        </table>
    </form>
    <div>
        <a href="/member/form"><input type="button" id="button_add" name="button_add" value="등록"></a>
    </div>

    <script type="text/javascript">
        searchList();

        function searchList() {
            var xmlHttp = new XMLHttpRequest();
            var inputJson = document.getElementById("memberRow");

            xmlHttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    var showData = xmlHttp.response;

                    var script = "";

                    for (var i = 0; i < showData.length; i++) {
                        script += "<tr>";
                        script += "    <td>" + showData[i].id + "</td>";
                        script += "    <td>" + showData[i].name +"</td>";
                        script += "    <td>" + showData[i].job + "</td>";
                        script += "    <td><input type=\"button\" onclick=\"viewMember(" + showData[i].id + ")\" value=\"보기\"></td>";
                        script += "    <td><a href=\"/member/form/" + showData[i].name +"\">수정</a></td>";
                        script += "    <td><input type=\"button\" onclick=\"deleteMember(" + showData[i].id + ")\" value=\"삭제\"></td>";
                        script += "    <tr id=\"viewMemberRow" + showData[i].id + "\"></tr>";
                        script += "</tr>";
                    }

                    inputJson.innerHTML = script;
                }
            }

            xmlHttp.open('POST', '${pageContext.request.contextPath}/members');
            xmlHttp.responseType = 'json';
            xmlHttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            xmlHttp.send();
        }

        function deleteMember(memberId) {
            var xmlHttp = new XMLHttpRequest();
            var member = {
                id : memberId
            };
            var parseMember = JSON.stringify(member);

            var inputJson = document.getElementById("memberRow");

            xmlHttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    var showData = xmlHttp.response;

                    var script = "";

                    for (var i = 0; i < showData.length; i++) {
                        script += "<tr>";
                        script += "    <td>" + showData[i].id + "</td>";
                        script += "    <td>" + showData[i].name +"</td>";
                        script += "    <td>" + showData[i].job + "</td>";
                        script += "    <td><input type=\"button\" onclick=\"viewMember(" + showData[i].id + ")\" value=\"보기\"></td>";
                        script += "    <td><a href=\"/member/form/" + showData[i].name +"\">수정</a></td>";
                        script += "    <td><input type=\"button\" onclick=\"deleteMember(" + showData[i].id + ")\" value=\"삭제\"></td>";
                        script += "    <tr id=\"viewMemberRow" + showData[i].id + "\"></tr>";
                        script += "</tr>";
                    }

                    inputJson.innerHTML = script;
                }
            }

            xmlHttp.open('DELETE', '${pageContext.request.contextPath}/member');
            xmlHttp.responseType='json';
            xmlHttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            xmlHttp.send(parseMember);
        }

        function viewMember(memberId) {
            var xmlHttp = new XMLHttpRequest();
            var member = {
                id : memberId
            };
            var inputJson = document.getElementById("viewMemberRow"+memberId);
            var script = "";

            xmlHttp.onreadystatechange = function () {
                if (this.readyState = 4 && this.status == 200) {
                    var viewMember = xmlHttp.response;

                    console.log(viewMember);
                    script += "        <td colspan=\"2\">" + viewMember.name + "</td>";
                    script += "        <td colspan=\"4\">" + viewMember.job + "</td>";

                    inputJson.innerHTML = script;
                }
            }

            xmlHttp.open('POST', '${pageContext.request.contextPath}/member');
            xmlHttp.responseType = 'json';
            xmlHttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            xmlHttp.send(JSON.stringify(member));

            <%--$.ajax({--%>
            <%--    url: "${pageContext.request.contextPath}/member"--%>
            <%--    , type: "POST"--%>
            <%--    , data: JSON.stringify(member)--%>
            <%--    , headers: {--%>
            <%--        "Content-Type" : "application/json;charset=UTF-8"--%>
            <%--        , success: function (row) {--%>
            <%--            console.log(row)--%>
            <%--            script += "    <tr>";--%>
            <%--            script += "        <td colspan=\"2\">" + row.name + "</td>";--%>
            <%--            script += "        <td colspan=\"4\">" + row.job + "</td>";--%>
            <%--            script += "    </tr>";--%>

            <%--            inputJson.innerHTML = script;--%>
            <%--        }--%>
            <%--    }--%>
            <%--});--%>
        }
    </script>
</body>
</html>
